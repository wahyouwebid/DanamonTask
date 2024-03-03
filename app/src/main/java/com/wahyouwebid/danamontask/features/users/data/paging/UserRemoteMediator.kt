package com.wahyouwebid.danamontask.features.users.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.wahyouwebid.danamontask.core.database.RoomDB
import com.wahyouwebid.danamontask.core.entity.PhotoEntity
import com.wahyouwebid.danamontask.core.mapper.DataMapper.mapPhotoResponseToPhotoEntity
import com.wahyouwebid.danamontask.features.users.data.remote.UserApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator @Inject constructor(
    private val roomDb: RoomDB,
    private val apiService: UserApiService
): RemoteMediator<Int, PhotoEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotoEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.id!! / state.config.pageSize) + 1
                    }
                }
            }

            val photosFromRemote = apiService.getPhotos(
                page = loadKey,
                limit = state.config.pageSize
            ).subscribeOn(Schedulers.io())

            photosFromRemote
                .flatMap { photos ->
                    Single.fromCallable {
                        if (loadType == LoadType.REFRESH) {
                            roomDb.userDao().clearAll()
                        }
                        roomDb.userDao().insertAll(photos.map { it.mapPhotoResponseToPhotoEntity() })
                            .observeOn(Schedulers.io())
                            .subscribe()
                        photos
                    }
                }
                .map<MediatorResult> { photos ->
                    MediatorResult.Success(
                        endOfPaginationReached = photos.isEmpty()
                    )
                }
                .onErrorReturn { error ->
                    MediatorResult.Error(error)
                }
                .blockingGet()
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}
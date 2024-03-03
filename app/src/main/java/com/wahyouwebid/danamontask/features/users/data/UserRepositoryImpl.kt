package com.wahyouwebid.danamontask.features.users.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.wahyouwebid.danamontask.core.entity.PhotoEntity
import com.wahyouwebid.danamontask.features.users.data.local.UserDao
import com.wahyouwebid.danamontask.features.users.data.paging.UserRemoteMediator
import com.wahyouwebid.danamontask.features.users.domain.UserRepository
import javax.inject.Inject

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/

@OptIn(ExperimentalPagingApi::class)
class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao,
    private val remoteMediator: UserRemoteMediator,
    private val pagingConfig: PagingConfig
): UserRepository {

    override fun getPhotos(): Pager<Int, PhotoEntity> {
        return Pager(
            config = pagingConfig,
            remoteMediator = remoteMediator,
            pagingSourceFactory = { dao.getAll() }
        )
    }

}
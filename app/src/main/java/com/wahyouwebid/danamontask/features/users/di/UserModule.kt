package com.wahyouwebid.danamontask.features.users.di

import androidx.paging.PagingConfig
import com.wahyouwebid.danamontask.core.database.RoomDB
import com.wahyouwebid.danamontask.features.users.data.local.UserDao
import com.wahyouwebid.danamontask.features.users.data.UserRepositoryImpl
import com.wahyouwebid.danamontask.features.users.data.paging.UserRemoteMediator
import com.wahyouwebid.danamontask.features.users.data.remote.UserApiService
import com.wahyouwebid.danamontask.features.users.domain.UserInteractor
import com.wahyouwebid.danamontask.features.users.domain.UserRepository
import com.wahyouwebid.danamontask.features.users.domain.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.disposables.CompositeDisposable
import retrofit2.Retrofit
import javax.inject.Singleton

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/


@InstallIn(SingletonComponent::class)
@Module
class UserModule {

    @Provides
    fun provideAdminDao(database: RoomDB): UserDao = database.userDao()


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }

    @Provides
    fun provideDatasource(
        dao: UserDao,
        pagingConfig: PagingConfig,
        remoteMediator: UserRemoteMediator
    ): UserRepository {
        return UserRepositoryImpl(dao, remoteMediator, pagingConfig)
    }


    @Provides
    fun provideInteractor(
        repository: UserRepository,
        disposable: CompositeDisposable
    ): UserUseCase {
        return UserInteractor(repository, disposable)
    }

}
package com.wahyouwebid.danamontask.features.admin.di

import androidx.paging.PagingConfig
import com.wahyouwebid.danamontask.core.database.RoomDB
import com.wahyouwebid.danamontask.core.session.Sessions
import com.wahyouwebid.danamontask.features.admin.data.AdminDao
import com.wahyouwebid.danamontask.features.admin.data.AdminRepositoryImpl
import com.wahyouwebid.danamontask.features.admin.domain.AdminInteractor
import com.wahyouwebid.danamontask.features.admin.domain.AdminRepository
import com.wahyouwebid.danamontask.features.admin.domain.AdminUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.disposables.CompositeDisposable

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/


@InstallIn(SingletonComponent::class)
@Module
class AdminModule {

    @Provides
    fun provideAdminDao(database: RoomDB): AdminDao = database.adminDao()


    @Provides
    fun provideDatasource(
        dao: AdminDao,
        pagingConfig: PagingConfig,
        sessions: Sessions,
        disposable: CompositeDisposable,
    ): AdminRepository {
        return AdminRepositoryImpl(dao, pagingConfig, sessions, disposable)
    }


    @Provides
    fun provideInteractor(
        repository: AdminRepository,
        disposable: CompositeDisposable
    ): AdminUseCase {
        return AdminInteractor(repository, disposable)
    }

}
package com.wahyouwebid.danamontask.features.auth.di

import com.wahyouwebid.danamontask.core.database.RoomDB
import com.wahyouwebid.danamontask.core.session.Sessions
import com.wahyouwebid.danamontask.features.auth.data.AuthRepositoryImpl
import com.wahyouwebid.danamontask.features.auth.domain.AuthInteractor
import com.wahyouwebid.danamontask.features.auth.domain.AuthRepository
import com.wahyouwebid.danamontask.features.auth.domain.AuthUseCase
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
class AuthModule {

    @Provides
    fun provideDatasource(
        db: RoomDB,
        sessions: Sessions,
        disposable: CompositeDisposable
    ): AuthRepository {
        return AuthRepositoryImpl(db, sessions, disposable)
    }


    @Provides
    fun provideInteractor(
        repository: AuthRepository
    ): AuthUseCase {
        return AuthInteractor(repository)
    }

}
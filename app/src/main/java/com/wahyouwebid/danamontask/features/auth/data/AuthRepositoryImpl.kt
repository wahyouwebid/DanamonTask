package com.wahyouwebid.danamontask.features.auth.data

import com.wahyouwebid.danamontask.core.database.RoomDB
import com.wahyouwebid.danamontask.core.session.Sessions
import com.wahyouwebid.danamontask.features.auth.data.mapper.DataMapper.mapUserEntityToUser
import com.wahyouwebid.danamontask.features.auth.data.mapper.DataMapper.mapUserToUserEntity
import com.wahyouwebid.danamontask.features.auth.domain.AuthRepository
import com.wahyouwebid.danamontask.features.auth.domain.model.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/


class AuthRepositoryImpl @Inject constructor(
    private val db: RoomDB,
    private val sessions: Sessions,
    private val disposable: CompositeDisposable
): AuthRepository {

    override fun login(
        email: String,
        password: String,
        isSuccess: (Boolean) -> Unit
    ) {
        db.userDao().login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { userEntity -> userEntity.mapUserEntityToUser() != null }
            .subscribe(
                { userExists ->
                    if (userExists) {
                        sessions.putBoolean(Sessions.isLogin, true)
                        isSuccess(true)
                    } else {
                        isSuccess(false)
                    }
                },
                {
                    isSuccess(false)
                }
            )
            .let(disposable::add)
    }

    override fun register(
        data: User,
        isSuccess: (Boolean) -> Unit
    ) {
        db.userDao().register(data.mapUserToUserEntity())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isSuccess.invoke(true) }
            .let(disposable::add)
    }
}

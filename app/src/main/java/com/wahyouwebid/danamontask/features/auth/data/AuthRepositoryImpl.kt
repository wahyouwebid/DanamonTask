package com.wahyouwebid.danamontask.features.auth.data

import com.wahyouwebid.danamontask.core.database.RoomDB
import com.wahyouwebid.danamontask.core.mapper.DataMapper.mapUserEntityToUser
import com.wahyouwebid.danamontask.core.mapper.DataMapper.mapUserToUserEntity
import com.wahyouwebid.danamontask.core.model.User
import com.wahyouwebid.danamontask.core.session.Sessions
import com.wahyouwebid.danamontask.features.auth.domain.AuthRepository
import com.wahyouwebid.danamontask.features.auth.domain.model.LoginResult
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
        result: (LoginResult?) -> Unit
    ) {
        db.authDao().login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    val userMapper = response.mapUserEntityToUser()
                    if (userMapper != null) {
                        sessions.putBoolean(Sessions.isLogin, true)
                        sessions.putInteger(Sessions.role, userMapper.role ?: 0)
                        sessions.putInteger(Sessions.userId, userMapper.id)
                        result.invoke(LoginResult(true, userMapper))
                    } else {
                        result.invoke(LoginResult(false))
                    }
                },
                {
                    result.invoke(LoginResult(false))
                }
            )
            .let(disposable::add)
    }

    override fun register(
        data: User,
        isSuccess: (Boolean) -> Unit
    ) {
        db.authDao().register(data.mapUserToUserEntity())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isSuccess.invoke(true) }
            .let(disposable::add)
    }

    override fun logout() {
        sessions.putBoolean(Sessions.isLogin, false)
    }
}

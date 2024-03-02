package com.wahyouwebid.danamontask.features.admin.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.wahyouwebid.danamontask.common.utils.logError
import com.wahyouwebid.danamontask.core.dao.UserDao
import com.wahyouwebid.danamontask.core.entity.UserEntity
import com.wahyouwebid.danamontask.features.admin.domain.AdminRepository
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


class AdminRepositoryImpl @Inject constructor(
    private val dao : UserDao,
    private val pagingConfig: PagingConfig,
    private val disposable: CompositeDisposable,
): AdminRepository {

    override fun getUsers(): Pager<Int, UserEntity> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { dao.getAll() }
        )
    }

    override fun getUsersById(id: Int, callback: (UserEntity) -> Unit) {
        dao.getById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ callback.invoke(it) }, { it.logError() })
            .let(disposable::add)
    }

    override fun updateUser(data: UserEntity) {
        dao.update(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .let(disposable::add)
    }

    override fun deleteUser(id: Int) {
        dao.delete(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .let(disposable::add)
    }
}
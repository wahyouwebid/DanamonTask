package com.wahyouwebid.danamontask.features.admin.domain

import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.flowable
import com.wahyouwebid.danamontask.core.entity.UserEntity
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/

@OptIn(ExperimentalCoroutinesApi::class)
class AdminInteractor @Inject constructor(
    private val repository: AdminRepository,
    private val disposable: CompositeDisposable
): AdminUseCase {

    override fun getUsers(scope: CoroutineScope, callback: (PagingData<UserEntity>) -> Unit) {
        repository.getUsers().flowable
            .cachedIn(scope)
            .subscribe(callback)
            .let { return@let disposable::add}
    }

    override fun getUserById(id: Int, callback: (UserEntity) -> Unit) {
        repository.getUsersById(id, callback)
    }

    override fun getMyProfile(callback: (UserEntity) -> Unit) {
        repository.getMyProfile(callback)
    }

    override fun updateUser(data: UserEntity) {
        repository.updateUser(data)
    }

    override fun deleteUser(id: Int) {
        repository.deleteUser(id)
    }
}
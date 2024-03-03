package com.wahyouwebid.danamontask.features.users.domain

import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.flowable
import com.wahyouwebid.danamontask.core.entity.PhotoEntity
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
class UserInteractor @Inject constructor(
    private val repository: UserRepository,
    private val disposable: CompositeDisposable,
): UserUseCase {

    override fun getPhotos(scope: CoroutineScope, callback: (PagingData<PhotoEntity>) -> Unit) {
        repository.getPhotos().flowable
            .cachedIn(scope)
            .subscribe(callback)
            .let { return@let disposable::add}
    }

}
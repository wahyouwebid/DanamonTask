package com.wahyouwebid.danamontask.features.users.domain

import androidx.paging.Pager
import com.wahyouwebid.danamontask.core.entity.PhotoEntity

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/


interface UserRepository {

    fun getPhotos(): Pager<Int, PhotoEntity>

}
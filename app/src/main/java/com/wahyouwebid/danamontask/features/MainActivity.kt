package com.wahyouwebid.danamontask.features

import android.os.Bundle
import com.wahyouwebid.danamontask.common.base.BaseActivity
import com.wahyouwebid.danamontask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun setupView(savedInstanceState: Bundle?) = Unit

}
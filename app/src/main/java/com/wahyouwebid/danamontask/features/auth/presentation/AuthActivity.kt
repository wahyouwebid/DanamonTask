package com.wahyouwebid.danamontask.features.auth.presentation

import android.os.Bundle
import com.wahyouwebid.danamontask.common.base.BaseActivity
import com.wahyouwebid.danamontask.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>(ActivityAuthBinding::inflate) {

    override fun setupView(savedInstanceState: Bundle?) {}

}
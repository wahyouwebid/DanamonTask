package com.wahyouwebid.danamontask.features.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.wahyouwebid.danamontask.common.base.BaseActivity
import com.wahyouwebid.danamontask.core.session.Sessions
import com.wahyouwebid.danamontask.databinding.ActivitySplashBinding
import com.wahyouwebid.danamontask.features.auth.presentation.AuthActivity
import com.wahyouwebid.danamontask.features.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    @Inject
    lateinit var sessions: Sessions

    override fun setupView(savedInstanceState: Bundle?) {
        Handler(Looper.getMainLooper()).postDelayed({
            val isLogin = sessions.getBoolean(Sessions.isLogin)
            if (isLogin) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }

        }, 2000)

    }
}
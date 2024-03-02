package com.wahyouwebid.danamontask.features

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.wahyouwebid.danamontask.R
import com.wahyouwebid.danamontask.common.base.BaseFragment
import com.wahyouwebid.danamontask.common.utils.UserRole
import com.wahyouwebid.danamontask.core.session.Sessions
import com.wahyouwebid.danamontask.databinding.FragmentSplashBinding
import com.wahyouwebid.danamontask.navigation.NavigationAction
import com.wahyouwebid.danamontask.navigation.navigate
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/

@AndroidEntryPoint
class SplashFragment: BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    @Inject
    lateinit var sessions: Sessions

    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        val isLogin = sessions.getBoolean(Sessions.isLogin)
        val role = sessions.getInteger(Sessions.role)

        Handler(Looper.getMainLooper()).postDelayed({
            if (isLogin) {
                if (role == UserRole.USER.value) {
                    navigation?.navigate(NavigationAction.SplashToUser)
                } else {
                    navigation?.navigate(NavigationAction.SplashToAdmin)
                }
            } else {
                navigation?.navigate(NavigationAction.SplashToLogin)
            }
        }, 2000)

    }

    override fun setupViewModel() = Unit

}
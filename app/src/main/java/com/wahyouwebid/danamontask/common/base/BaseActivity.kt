
package com.wahyouwebid.danamontask.common.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/

abstract class BaseActivity<T : ViewBinding>(private val bindingInflater: (layoutInflater: LayoutInflater) -> T) : AppCompatActivity() {

    private lateinit var _binding: T

    protected val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)
        setupView(savedInstanceState)
    }

    abstract fun setupView(savedInstanceState: Bundle?)
}
package com.thanht.weather

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.thanht.weather.R
import com.thanht.weather.base.BaseActivity
import com.thanht.weather.base.BaseViewModel
import com.thanht.weather.databinding.ActivityMainBinding
import com.thanht.weather.navigation.NavigationDispatcher
import com.thanht.weather.navigation.NavigationDispatcherImpl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override val navController by lazy { findNavController(R.id.navHostFragment) }

    override val navDispatcher by lazy { NavigationDispatcherImpl(navController) }

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val mViewModel: MainViewModel by viewModels { factory }
    override val viewModel: BaseViewModel
        get() = mViewModel

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
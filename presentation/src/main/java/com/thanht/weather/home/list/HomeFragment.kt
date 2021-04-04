package com.thanht.weather.home.list

import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.SimpleItemAnimator
import com.thanht.data.BuildConfig
import com.thanht.domain.base.BaseResult
import com.thanht.weather.R
import com.thanht.weather.base.BaseFragment
import com.thanht.weather.common.SimpleDialog
import com.thanht.weather.databinding.FragmentHomeBinding
import com.thanht.weather.home.list.adapter.WeatherItemDecorator
import com.thanht.weather.home.list.adapter.WeatherListAdapter
import com.thanht.weather.util.*
import com.thanht.weather.util.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
        R.layout.fragment_home,
        HomeViewModel::class
) {

    private lateinit var adapter: WeatherListAdapter
    private lateinit var textWatcher: TextWatcher
    private var simpleDialog: SimpleDialog? = null

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun FragmentHomeBinding.initViews() {
        viewModel ?: run {
            viewModel = mViewModel
            initUI()
            initEvents()
            activity.changeStatusBar(
                    fullLayout = false,
                    color = getColor(R.color.colorPrimaryDark)
            )
        }
        observable()
    }

    override fun onResume() {
        super.onResume()
        mBinding.edtWeather.apply {
            addTextChangedListener(textWatcher)
            requestFocus()
            showSoftKeyboard()
        }
    }

    override fun onPause() {
        mBinding.edtWeather.apply {
            removeTextChangedListener(textWatcher)
            clearFocus()
            hideSoftKeyboard()
        }
        super.onPause()
    }

    override fun onDestroy() {
        simpleDialog?.dismiss()
        super.onDestroy()
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun observable() = mViewModel.run {
        weatherForecastResultLive.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResult.Loading -> {
                    Toast.makeText(context, getString(R.string.searching), Toast.LENGTH_SHORT).show()
                }

                is BaseResult.Success -> {
                    it.data?.let { weatherModels ->
                        currentSearchList[queryStringLive.value] = weatherModels
                        adapter.submitList(weatherModels.toWeatherInfoList())
                        updateEmptyView(isHideEmptyView = true)
                    }
                    Toast.makeText(context, getString(R.string.search_complete), Toast.LENGTH_SHORT).show()
                }
                is BaseResult.Failed -> {
                    handleCheckInFailed(
                            it.status, it.exception
                    )
                    adapter.submitList(emptyList())
                    updateEmptyView(isHideEmptyView = false)
                }
                is BaseResult.Error -> {
                    handleCommonError(it.error)
                    adapter.submitList(emptyList())
                    updateEmptyView(isHideEmptyView = false)
                }
                else -> {
                }
            }
        }

        mViewModel.progressDialogEvent.observe(viewLifecycleOwner) {
            showProgressDialog(it, false)
        }
    }

    private fun updateEmptyView(isHideEmptyView: Boolean) = mBinding.run {
        tvEmpty.isInvisible = isHideEmptyView
        rvWeather.isInvisible = isHideEmptyView.not()
    }

    private fun initEvents() = mViewModel.run {
        textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                queryWeather(s)
            }
        }
        mBinding.btnGetWeather.setSafeOnClickListener {
            queryWeather(mBinding.edtWeather.text)
        }
    }

    private fun initUI() {
        adapter = WeatherListAdapter()
        mBinding.rvWeather.apply {
            adapter = this@HomeFragment.adapter
            addItemDecoration(WeatherItemDecorator(context))
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
    }

    private fun handleCheckInFailed(status: Int, exception: String? = null) {
        exception ?: return
        showWarningDialog("", exception, -1)
    }

    private fun showWarningDialog(title: String = "", content: String, image: Int = -1) {
        simpleDialog = SimpleDialog.create(requireContext(), viewLifecycleOwner)
                .setTitle(title)
                .setContent(content)
                .setImageResource(image)
                .setPositiveLabel(getString(R.string.title_close))
                .also {
                    it.show()
                }
    }

    private fun handleCommonError(error: Throwable) {
        if (error.message?.contains("404") == true) {
            Toast.makeText(context, getString(R.string.city_not_found), Toast.LENGTH_SHORT).show()
            return
        }
        val errorMsg = if (BuildConfig.DEBUG) error.message.orEmpty()
        else getString(R.string.content_unexpected_error)
        showWarningDialog(title = getString(R.string.title_unexpected_error), content = errorMsg)
    }
}
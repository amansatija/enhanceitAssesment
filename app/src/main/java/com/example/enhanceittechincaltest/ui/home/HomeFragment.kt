package com.example.enhanceittechincaltest.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.enhanceittechincaltest.base.FragmentBase
import com.example.enhanceittechincaltest.base.app.AppID
import com.example.enhanceittechincaltest.data.domain.tvshow.model.getNoOfDaysSincePriemere
import com.example.enhanceittechincaltest.databinding.FragmentHomeBinding
import com.example.enhanceittechincaltest.utils.api.picasso.PicassoCache
import com.example.enhanceittechincaltest.utils.core.extensions.observeFlow
import com.example.enhanceittechincaltest.utils.core.extensions.observeFlowStart
import com.example.enhanceittechincaltest.utils.core.extensions.observeLiveData
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KFunction1

@AndroidEntryPoint
class HomeFragment : FragmentBase() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val viewModel:HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
//        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
////            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doAfterOnCreateView(view)
//        binding.viewmodel = viewModel
//        _binding.lifecycleOwner = viewLifecycleOwner
        observeUi()
        viewModel.onTriggerEvent(HomePageContract.Event.SearchTvShow(binding.fragHomeEtSearchBox.text.toString()))
    }

    override fun setListeners(v: View?) {
        super.setListeners(v)
        binding.fragHomeEtSearchBox.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                viewModel.onTriggerEvent(HomePageContract.Event.SearchTvShow(
                    binding.fragHomeEtSearchBox.text.toString())
                )
            }

        })
    }
    open protected fun observeUi() {
        observeProgress()
        observeError()
        observeFlow(viewModel.stateFlow, ::renderViewState)

    }


    private fun observeProgress() {
        observeFlow(viewModel.progress) { state ->
            state?.let {
                if (it) {
                    showLoader()
//                    showProgress()
                } else {
//                    hideProgress()
                    hideLoader()
                }
            }
        }
    }

    private fun observeError() {
        viewModel.error.observe(viewLifecycleOwner) {
            showError(it.message);
        }
    }

    override fun showError(argStrMsg: String?) {
        super.showError(argStrMsg)
        hideUi()
    }

    fun hideUi(){
        binding.fragHomeLIvImg.visibility = View.GONE
        binding.fragHomeLTvName.visibility = View.GONE
        binding.fragHomeLTvDateOfPremiere.visibility = View.GONE
    }
    fun showUi(){
        binding.fragHomeLIvImg.visibility = View.VISIBLE
        binding.fragHomeLTvName.visibility = View.VISIBLE
        binding.fragHomeLTvDateOfPremiere.visibility = View.VISIBLE
    }






    fun renderViewState(viewState: HomePageContract.State) {
        showUi()
        when (viewState) {
            is HomePageContract.State.HomePageData -> {
                val detail = viewState.detail
                PicassoCache.getPicassoInstance(activity)
                    .load(detail.image.medium).into(binding.fragHomeLIvImg)

//                binding.fragHomeLIvImg.load(detail.image.orEmpty()) {
//                    transformations(RoundedCornersTransformation(25f))
//                }
                binding.fragHomeLTvName.text = detail.name
//                binding.fragHomeLTvDateOfPremiere.text =
//                    detail.premiered.toString()
                binding.fragHomeLTvDateOfPremiere.text =
                    detail.getNoOfDaysSincePriemere()+" since premiere"
            }
//            is DetailContract.State.Detail -> {
//                adapter.setItems(viewState.list)
//            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
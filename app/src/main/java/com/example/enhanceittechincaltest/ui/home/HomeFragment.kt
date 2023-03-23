package com.example.enhanceittechincaltest.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.enhanceittechincaltest.R
import com.example.enhanceittechincaltest.base.FragmentBase
import com.example.enhanceittechincaltest.data.domain.tvshow.model.ModelTvShow
import com.example.enhanceittechincaltest.data.domain.tvshow.model.getNoOfDaysSincePriemere
import com.example.enhanceittechincaltest.databinding.FragmentHomeBinding
import com.example.enhanceittechincaltest.utils.api.picasso.PicassoCache
import com.example.enhanceittechincaltest.utils.core.extensions.observeFlow
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

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

                loadMovieImage(detail)
//                binding.fragHomeLIvImg.load(detail.image.orEmpty()) {
//                    transformations(RoundedCornersTransformation(25f))
//                }
                binding.fragHomeLTvName.text = detail.name
//                binding.fragHomeLTvDateOfPremiere.text =
//                    detail.premiered.toString()
                binding.fragHomeLTvDateOfPremiere.text =
                    ""+detail.getNoOfDaysSincePriemere()+" Days since premiere"
            }
//            is DetailContract.State.Detail -> {
//                adapter.setItems(viewState.list)
//            }
        }
    }

    private fun loadMovieImage(tvShowDetail:ModelTvShow){
        val piccasoReq = PicassoCache.getPicassoInstance(activity)
            .load(tvShowDetail.image.medium)
        activity?.let {
            piccasoReq.placeholder(ContextCompat.getDrawable(requireActivity(), R.drawable.ic_png_placeholder_img_24dp)!!)
        }
        piccasoReq.into(binding.fragHomeLIvImg,object:Callback{
            override fun onSuccess() {
                PicassoCache.getPicassoInstance(activity)
                    .load(tvShowDetail.image.original).placeholder(binding.fragHomeLIvImg.drawable).into(binding.fragHomeLIvImg)
            }
            override fun onError(e: Exception?) {
                e?.printStackTrace()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
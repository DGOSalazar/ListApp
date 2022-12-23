package com.example.listexercise.loadActivity.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listexercise.R
import com.example.listexercise.databinding.FragmentMainBinding
import com.example.listexercise.loadActivity.domain.NetworkState
import com.example.listexercise.loadActivity.domain.model.GobModel
import com.example.listexercise.loadActivity.ui.view.adapter.FragmentAdapter
import com.example.listexercise.loadActivity.ui.viewModel.MainViewModel

class MainFragment : Fragment(R.layout.fragment_main), View.OnClickListener {
    private lateinit var  mBinding : FragmentMainBinding
    private lateinit var mAdapter : FragmentAdapter
    private val mainViewMain : MainViewModel by activityViewModels()

    private lateinit var alertBuilder : AlertDialog.Builder
    private var list: List<GobModel> = listOf()
    private var listPagination: List<GobModel> = listOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        mBinding = FragmentMainBinding.inflate(inflater,container,false)
        return mBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alertBuilder = AlertDialog.Builder(mBinding.root.context)
        val networkState = NetworkState(mBinding.root.context)
        networkState.observe(viewLifecycleOwner) { it ->
            if(it){
                //ViewModel
                toastResponse(getString(R.string.welcome))
                with(mainViewMain) {
                    onGetData()
                    onPagination()
                    isLoading.observe(viewLifecycleOwner) { loading ->
                        mBinding.indeterminateLinearIndicator.isVisible = loading
                    }
                    gobModel.observe(viewLifecycleOwner) { listFull ->
                        list = listFull
                        if (list[0].statusError.isNotEmpty()) {toastResponse(getString(R.string.apiErrorMessage))}
                    }
                    gobModelPagination.observe(viewLifecycleOwner) { listPage->
                        listPagination = listPage
                        mAdapter.setList(listPage)
                    }
                }
                //Clicks
                mBinding.search.addTextChangedListener { slug ->
                    val listFilter = list.filter {list-> list.slug.contains(slug.toString().lowercase()) }
                    mAdapter.setList(listFilter)
                }
                mBinding.btNext.setOnClickListener(this)
                mBinding.btBack.setOnClickListener(this)
                launchAdapter()
            }
            else{ alertOne()
                mainViewMain.onLoading()
                mainViewMain.isLoading.observe(viewLifecycleOwner){
                    mBinding.indeterminateLinearIndicator.isVisible = it
                    hideUi()
                }}
        }
    }
    private fun hideUi(){
        with(mBinding) {
            btBack.isInvisible=true
            btNext.isInvisible=true
            search.isInvisible=true
        }
    }
    private fun launchAdapter(){
        mAdapter= FragmentAdapter(listPagination) {click()}
        mBinding.recyclerView.layoutManager = LinearLayoutManager(mBinding.root.context)
        mBinding.recyclerView.adapter= mAdapter
    }
    private fun toastResponse(text: String){
        Toast.makeText(mBinding.root.context, text, Toast.LENGTH_SHORT).show()
    }
    private fun click(){
        mainViewMain.onItemSelect(mAdapter.listResult)
        findNavController().navigate(R.id.action_mainFragment_to_listFragment2)
    }
    private fun alertOne(){
        alertBuilder.setTitle(getString(R.string.messageError1_title))
            .setMessage(getString(R.string.messageError1_message))
            .setPositiveButton(getString(R.string.messageError1_bt_positive)){ _, _ ->
                val intent = activity?.intent
                activity?.finish()
                startActivity(intent)
            }
            .setNegativeButton(getString(R.string.messageError1_bt_negative)){ _, _ ->
                activity?.finish()
            }.show()
    }
    override fun onClick(v: View?) {
        v?.let {
            when(it){
                mBinding.btNext-> {
                    mainViewMain.onNextPage()
                    mainViewMain.onPagination()
                }
                mBinding.btBack-> {
                    mainViewMain.onPreviousPage()
                    mainViewMain.onPagination()
                }
            }
        }
    }
}
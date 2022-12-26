package com.example.listexercise.loadActivity.ui.view.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.listexercise.R
import com.example.listexercise.databinding.FragmentListBinding
import com.example.listexercise.loadActivity.domain.model.GobModel
import com.example.listexercise.loadActivity.ui.viewModel.MainViewModel

class ListFragment : Fragment(R.layout.fragment_list), View.OnClickListener {
    private lateinit var mBinding: FragmentListBinding
    private val mainViewModel : MainViewModel by activityViewModels()
    private var gobData = GobModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        mBinding = FragmentListBinding.inflate(inflater,container,false)
        return mBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.gobData.observe(viewLifecycleOwner){
            printData(it)
            gobData=it
        }
        mBinding.btSend.setOnClickListener(this)
        mBinding.ibBack.setOnClickListener(this)
    }
    @SuppressLint("SetTextI18n")
    private fun printData(result: GobModel){
        with(mBinding){
            tvId.text = ("${getString(R.string.id)}${result.id}")
            tvDateInsert.text = ("${getString(R.string.date_insert)}${result.date_insert}")
            tvSlug.text = ("${getString(R.string.slug)}${result.slug}")
            tvColumns.text = ("${getString(R.string.columns)}${result.columns}")
            tvFact.text = ("${getString(R.string.fact)}${result.fact}")
            tvOrganization.text = ("${getString(R.string.organization)}${result.organization}")
            tvResource.text = ("${getString(R.string.resource)}${result.resource}")
            tvUrl.text = ("${getString(R.string.url)}${result.url}")
            tvOperations.text = ("${getString(R.string.operations)}${result.operations}")
            tvDataset.text = ("${getString(R.string.data_set)}${result.dataset}")
            tvCreated.text = ("${getString(R.string.created_at)}${result.created_at}")
        }
    }
    private fun sendMessage(message:String){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.setPackage("com.whatsapp")
        intent.putExtra(Intent.EXTRA_TEXT, message)
        // Starting Whatsapp
        startActivity(intent)
    }
    override fun onClick(v:View?){
        v?.let {
            when(it){
                mBinding.ibBack -> activity?.onBackPressed()
                mBinding.btSend -> sendMessage(gobData.toString())
                else-> print(getString(R.string.toastPermission_denied))
            }
        }
    }
}
package com.example.listexercise.loadActivity.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listexercise.R
import com.example.listexercise.databinding.FragmentListBinding
import com.example.listexercise.loadActivity.data.model.ListResult
import com.example.listexercise.loadActivity.domain.model.GobModel
import com.example.listexercise.loadActivity.ui.view.adapter.FragmentAdapter
import com.example.listexercise.loadActivity.ui.viewModel.MainViewModel

class ListFragment : Fragment(R.layout.fragment_list) {
    private lateinit var mBinding: FragmentListBinding
    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentListBinding.inflate(inflater,container,false)
        return mBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var data = GobModel()
        mainViewModel.model.observe(viewLifecycleOwner, Observer {
            printData(it)
            data=it
        })
        mBinding.btSend.setOnClickListener{
            sendMessage(data.toString())
        }
    }
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
    private fun toastResponse(text: String){
        Toast.makeText(mBinding.root.context, text, Toast.LENGTH_SHORT).show()
    }
    override fun onDestroy(){
        super.onDestroy()
    }
}
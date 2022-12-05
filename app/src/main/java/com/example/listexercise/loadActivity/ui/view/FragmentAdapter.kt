package com.example.listexercise.loadActivity.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listexercise.R
import com.example.listexercise.databinding.ListGobBinding
import com.example.listexercise.loadActivity.data.model.ListResult

class FragmentAdapter(private var gobRest: List<ListResult>,
                      private var page: Int):
RecyclerView.Adapter<FragmentAdapter.ViewHolder>(){
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val mBinding = ListGobBinding.bind(view)

        fun bindView(gobRest: ListResult){
            with(mBinding) {
                tvId.text = ("Id:${gobRest.id}")
                tvFact.text = ("Fact: ${gobRest.fact}")
                tvOrganization.text = ("Organization:${gobRest.organization}")
                tvUrl.text = ("Url: ${gobRest.url}")
            }
        }
    }
    fun setStores(gobRest: List<ListResult>) {
        this.gobRest = gobRest
        notifyDataSetChanged()
    }
    fun pagination(page: Int){
        this.page = page
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context).inflate(R.layout.list_gob,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gobRestfull = gobRest[position+page]
        with(holder){
            mBinding.tvResult.text=((position+page)+1).toString()
            bindView(gobRestfull)
        }
    }
    override fun getItemCount(): Int = gobRest.size-90
}
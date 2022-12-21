package com.example.listexercise.loadActivity.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listexercise.R
import com.example.listexercise.databinding.ListGobBinding
import com.example.listexercise.loadActivity.domain.model.GobModel

class FragmentAdapter(private var gobRest: List<GobModel>,
                      private var click : (GobModel)->Unit):
RecyclerView.Adapter<FragmentAdapter.ViewHolder>(){
    var listResult = GobModel()
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val mBinding = ListGobBinding.bind(view)

        fun bindView(gobRest: GobModel){
            with(mBinding) {
                tvId.text = ("Id:${gobRest.id}")
                tvFact.text = ("Fact: ${gobRest.fact}")
                tvOrganization.text = ("Organization:${gobRest.organization}")
                tvUrl.text = ("Url: ${gobRest.url}")
            }
        }
        fun getGob(click:(GobModel)->Unit){
            mBinding.root.setOnClickListener {
                click(gobRest[adapterPosition])
            }
        }
    }
    fun setList(list: List<GobModel>) {
        this.gobRest=list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context).inflate(R.layout.list_gob,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gobRestfull = gobRest[position]
        with(holder){
            mBinding.tvResult.text=((position)+1).toString()
            bindView(gobRestfull)
            getGob(click)
            listResult=gobRestfull
        }
    }
    override fun getItemCount(): Int = gobRest.size


}
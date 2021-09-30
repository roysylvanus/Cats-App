package com.malinikali.cats.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.malinikali.cats.adapter.BreedsAdapter.Companion.diffUtilCallback
import com.malinikali.cats.databinding.FactLayoutBinding
import com.malinikali.cats.models.FactItem

class FactsAdapter: PagingDataAdapter<FactItem, FactsAdapter.FactViewHolder>(diffUtilCallback)  {

    companion object{

        val diffUtilCallback = object : DiffUtil.ItemCallback<FactItem>(){
            override fun areItemsTheSame(oldItem: FactItem, newItem: FactItem): Boolean {
               return oldItem.fact == newItem.fact
            }

            override fun areContentsTheSame(oldItem: FactItem, newItem: FactItem): Boolean {
               return newItem == oldItem
            }
        }
    }

    inner class FactViewHolder(val binding: FactLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        val factItem = getItem(position)

        holder.binding.tvLength.text = "${factItem?.length}"
        holder.binding.tvFact.text = "${factItem?.fact}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        return FactViewHolder(FactLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

}
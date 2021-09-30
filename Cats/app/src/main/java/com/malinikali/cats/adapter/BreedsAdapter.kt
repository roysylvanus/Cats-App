package com.malinikali.cats.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.malinikali.cats.databinding.BreedLayoutBinding
import com.malinikali.cats.models.BreedItem

class BreedsAdapter: PagingDataAdapter<BreedItem,BreedsAdapter.BreedsViewHolder>(diffUtilCallback) {

    inner class BreedsViewHolder(val binding: BreedLayoutBinding):RecyclerView.ViewHolder(binding.root){}

   companion object {
       val diffUtilCallback = object : DiffUtil.ItemCallback<BreedItem>(){
           override fun areItemsTheSame(oldItem: BreedItem, newItem: BreedItem): Boolean {
               return oldItem.breed == newItem.breed
           }

           override fun areContentsTheSame(oldItem: BreedItem, newItem: BreedItem): Boolean {
               return newItem == oldItem
           }
       }
   }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsViewHolder {
        return BreedsViewHolder(BreedLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: BreedsViewHolder, position: Int) {
        val breedItem =  getItem(position)

        holder.binding.tvBreed.text = "${breedItem?.breed}"
        holder.binding.tvCoat.text = "${breedItem?.coat}"
        holder.binding.tvCountry.text = "${breedItem?.country}"
        holder.binding.tvOrigin.text = "${breedItem?.origin}"
        holder.binding.tvPattern.text = "${breedItem?.pattern}"
    }



}
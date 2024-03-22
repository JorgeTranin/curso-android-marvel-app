package com.example.marvelapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.databinding.ItemCharacterLoadingMoreStateBinding

class CharactersLoadMoreStateViewHolder (
    itenBinding: ItemCharacterLoadingMoreStateBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(itenBinding.root){

    private val binding = ItemCharacterLoadingMoreStateBinding.bind(itemView)
    private val progressBarLoad = binding.progressLoadMore
    private val textTryAgain = binding.txtTryAgain.also {
        it.setOnClickListener {
            retry()
        }
    }

        fun bind(loadState: LoadState){
            progressBarLoad.isVisible = loadState is LoadState.Loading
            textTryAgain.isVisible = loadState is LoadState.Error
        }

    companion object{
        fun create(parent: ViewGroup, retry: () -> Unit): CharactersLoadMoreStateViewHolder{
            val itemBinding = ItemCharacterLoadingMoreStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CharactersLoadMoreStateViewHolder(itemBinding, retry)
        }
    }

}
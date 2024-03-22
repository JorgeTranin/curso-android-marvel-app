package com.example.marvelapp.presentation.home

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class CharactersLoadMoreStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<CharactersLoadMoreStateViewHolder>(){
    override fun onBindViewHolder(holder: CharactersLoadMoreStateViewHolder, loadState: LoadState) = holder.bind(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CharactersLoadMoreStateViewHolder = CharactersLoadMoreStateViewHolder.create(parent, retry)
}
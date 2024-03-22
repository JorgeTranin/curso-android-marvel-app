package com.example.marvelapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.example.marvelapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapterCharacters()
        observerInitalState()
        initData()
    }



    private fun initData() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                homeViewModel.getCharacterPaginData("").collectLatest { pagingData ->
                    charactersAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun initAdapterCharacters() {
        charactersAdapter = CharactersAdapter()
        binding.recyclerCharacters.run {
            scrollToPosition(0)
            setHasFixedSize(true)
            adapter = charactersAdapter.withLoadStateFooter(CharactersLoadMoreStateAdapter{
                charactersAdapter.retry()
            })

        }
    }

    private fun observerInitalState() {
        lifecycleScope.launch {
            charactersAdapter.loadStateFlow.collectLatest { state ->
                binding.flipperCharacters.displayedChild = when (state.refresh) {
                    is LoadState.Loading -> {
                        setShimmerVisibility(true)
                        FLIPER_LOADING
                    }

                    is LoadState.NotLoading -> {
                        setShimmerVisibility(false)
                        FLIPPER_SUCCESS
                    }

                    is LoadState.Error -> {
                        binding.includeViewCharacterErrorState.btnRetry.setOnClickListener {
                            charactersAdapter.retry()
                        }
                        FLIPER_ERROR
                    }
                }

            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setShimmerVisibility(visibility: Boolean) {
        binding.includeViewCharacterLoadingState.shimmerCharacter.run {
            isVisible = visibility
            if (visibility) {
                startShimmer()
            } else {
                stopShimmer()
            }
        }

    }

    companion object {
        private const val FLIPER_LOADING = 0
        private const val FLIPPER_SUCCESS = 1
        private const val FLIPER_ERROR = 2
    }
}
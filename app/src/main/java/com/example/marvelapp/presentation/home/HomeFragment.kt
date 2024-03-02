package com.example.marvelapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marvelapp.databinding.FragmentHomeBinding
import com.jorge.core.domain.models.Character
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val charactersAdapter = CharactersAdapter()

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
        charactersAdapter.submitList(
            listOf(
                Character(
                    "Jorge",
                    "https://cdn.pixabay.com/photo/2023/07/24/09/14/coccinellidae-8146623_1280.jpg"
                ),
                Character(
                    "Pedro",
                    "https://cdn.pixabay.com/photo/2023/07/24/09/14/coccinellidae-8146623_1280.jpg"
                ),
                Character(
                    "Miguel",
                    "https://cdn.pixabay.com/photo/2023/07/24/09/14/coccinellidae-8146623_1280.jpg"
                ),
                Character(
                    "tranin",
                    "https://cdn.pixabay.com/photo/2023/07/24/09/14/coccinellidae-8146623_1280.jpg"
                )
            )
        )

    }

    private fun initAdapterCharacters() {
        binding.recyclerCharacters.run {
            setHasFixedSize(true)
            adapter = charactersAdapter


        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
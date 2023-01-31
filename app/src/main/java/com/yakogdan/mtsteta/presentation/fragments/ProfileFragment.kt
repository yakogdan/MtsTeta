package com.yakogdan.mtsteta.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain
import com.yakogdan.mtsteta.databinding.FragmentProfileBinding
import com.yakogdan.mtsteta.presentation.adapters.MovieGenresAdapter
import com.yakogdan.mtsteta.presentation.itemdecoration.HorizontalItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieGenresAdapter: MovieGenresAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        movieGenresAdapter.setData(
            listOf(
                MovieGenreDomain(id = 0, title = "боевик"),
                MovieGenreDomain(id = 1, title = "комедия")
            )
        )
        with(binding) {
            tvUsername.text = "Константин"
            val emailAddressSample = "konst.89@mail.com"
            tvUserEmailAddress.text = emailAddressSample
        }


    }

    private fun initAdapter() {

        movieGenresAdapter = MovieGenresAdapter(
            onItemClickListener = { movieGenres ->
                Toast.makeText(
                    context,
                    movieGenres.title,
                    Toast.LENGTH_SHORT
                ).show()
            }
        )

        binding.rvFavoriteGenres.apply {
            adapter = movieGenresAdapter
            addItemDecoration(
                HorizontalItemDecoration(
                    startFirst = 54,
                    endAll = 25
                )
            )
        }
    }
}
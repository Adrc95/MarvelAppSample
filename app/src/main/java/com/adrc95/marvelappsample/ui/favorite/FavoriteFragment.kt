package com.adrc95.marvelappsample.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.adrc95.marvelappsample.databinding.FragmentFavoriteBinding
import com.adrc95.marvelappsample.ui.common.BaseFragment
import com.adrc95.marvelappsample.ui.common.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    override val viewModel: FavoriteViewModel by viewModels()

    private val adapter = FavoriteCharactersAdapter { viewModel.onCharacterDelete(it) }

    override val bindView: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoriteBinding
        get() = FragmentFavoriteBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvCharacters.adapter = adapter
            tryAgain = viewModel::onTryAgainClicked
        }

        viewLifecycleOwner.launchAndCollect(viewModel.uiState) {
            manageUiState(it)
        }
    }

    private fun manageUiState(state : FavoriteViewModel.FavoriteUiState) = with(binding){
        loading = state.loading
        characters = state.characters
        emptyFavorites = state.emptyFavorites
        serverError = state.serverError
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

}

package com.adrc95.marvelappsample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.adrc95.marvelappsample.databinding.FragmentMainBinding
import com.adrc95.marvelappsample.ui.common.BaseFragment
import com.adrc95.marvelappsample.ui.common.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    override val viewModel: MainViewModel by viewModels()

    private val mainEventState by lazy { buildMainState() }

    private val adapter = CharactersAdapter { mainEventState.onCharacterClicked(it) }

    override val bindView: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvCharacters.adapter = adapter
            tryAgain = viewModel::onTryAgainClicked
            onSearchTextChanged = viewModel::onSearchTextChanged
        }

        viewLifecycleOwner.launchAndCollect(viewModel.uiState) {
            manageUiState(it)
        }
    }

    private fun manageUiState(state : MainViewModel.MainUiState) = with(binding) {
            characters = state.characters
            enabledSearch = state.enabledSearch
            serverError = state.serverError
            loading = state.loading
            filterQuery = state.filterQuery
    }
}
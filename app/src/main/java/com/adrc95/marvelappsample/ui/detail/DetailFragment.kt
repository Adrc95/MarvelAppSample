package com.adrc95.marvelappsample.ui.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import com.adrc95.marvelappsample.ui.navhost.NavHostActivity
import com.adrc95.marvelappsample.R
import com.adrc95.marvelappsample.databinding.FragmentDetailBinding
import com.adrc95.marvelappsample.databinding.MenuActionFavoriteBinding
import com.adrc95.marvelappsample.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val favoriteMenuObservable by lazy { FavoriteMenuObservable() }

    override val viewModel: DetailViewModel by viewModels()

    override val bindView: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailBinding
        get() = FragmentDetailBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.apply {
            observable = favoriteMenuObservable
        }
        (activity as NavHostActivity).hideBottomBar()
    }

    override fun onDestroyView() {
        (activity as NavHostActivity).showBottomBar()
        super.onDestroyView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        val menuItemFavorite = menu.findItem(R.id.action_favorite)
        val binding: MenuActionFavoriteBinding = MenuActionFavoriteBinding.inflate(layoutInflater)
        binding.apply {
            data = favoriteMenuObservable
            viewmodel = viewModel
        }
        menuItemFavorite?.apply {
            actionView = binding.root
            setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

}
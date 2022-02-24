package com.adrc95.marvelappsample.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adrc95.domain.Character
import com.adrc95.marvelappsample.ui.common.addOnScrolledToEnd
import com.adrc95.marvelappsample.ui.favorite.FavoriteCharactersAdapter

@BindingAdapter(value = ["items", "isMoreItemsLoad"], requireAll = false)
fun RecyclerView.setItems(characters: List<Character>?, isMoreItemLoad: Boolean?) {

    adapter?.let {
        when (it) {
            is CharactersAdapter -> {
                val adapter = it
                characters?.let {
                    if (!adapter.isApplicatedFilter) {
                        when {
                            adapter.characters.isNullOrEmpty() -> {
                                adapter.characters = characters
                                adapter.submitList(adapter.characters)
                            }
                            isMoreItemLoad == true -> {
                                adapter.characters = adapter.characters!! + characters
                                adapter.submitList(adapter.characters)
                            }
                            else -> {
                                //Nothing
                            }
                        }
                    }
                    adapter.isApplicatedFilter = false
                }
            }
            is FavoriteCharactersAdapter -> {
                val adapter = it
                characters?.let {
                    adapter.submitList(characters)
                }
            }
            else -> {
                //Nothing
            }
        }
    }
}

@BindingAdapter("filter")
fun RecyclerView.setFilter(text: String?) {
    val adapter = (adapter as? CharactersAdapter)
    adapter?.let {
        text?.let {
            adapter.isApplicatedFilter = true
            adapter.filter.filter(text)
        }
    }
}

@BindingAdapter("onMoreItems")
fun RecyclerView.onMoreItems(onLoadMoreListener: OnLoadMoreListener?) {
    val adapter = (adapter as? CharactersAdapter)
    adapter?.let {
        addOnScrolledToEnd { totalItemCount ->
            if (!it.isApplicatedFilter) {
                adapter.isLoadMoreCharacters = true
                //In progress
                //onLoadMoreListener?.onLoadMore(totalItemCount)
            }
        }
    }
}

interface OnLoadMoreListener {
    fun onLoadMore(totalItemCount: Int)
}
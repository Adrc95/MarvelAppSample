package com.adrc95.marvelappsample.ui.favorite

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adrc95.domain.Character
import com.adrc95.marvelappsample.R
import com.adrc95.marvelappsample.databinding.ViewFavoriteCharacterBinding
import com.adrc95.marvelappsample.ui.common.basicDiffUtil
import com.adrc95.marvelappsample.ui.common.inflate

class FavoriteCharactersAdapter(private val listener: (Long) -> Unit) :
    ListAdapter<Character, FavoriteCharactersAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_favorite_character, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    class ViewHolder(view: View, private val listener: (Long) -> Unit) : RecyclerView.ViewHolder(view) {
        private val binding = ViewFavoriteCharacterBinding.bind(view)
        fun bind(character: Character) {
            binding.character = character
            binding.action = listener
        }
    }
}

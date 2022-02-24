package com.adrc95.marvelappsample.ui.main

import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adrc95.domain.Character
import com.adrc95.marvelappsample.R
import com.adrc95.marvelappsample.databinding.ViewCharacterBinding
import com.adrc95.marvelappsample.ui.common.basicDiffUtil
import com.adrc95.marvelappsample.ui.common.inflate

class CharactersAdapter(private val listener: (Character) -> Unit) :
    ListAdapter<Character, CharactersAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }),
    Filterable {

    var characters: List<Character>? = null

    var filterCharacters: List<Character>? = null

    var isApplicatedFilter: Boolean = false

    var isLoadMoreCharacters: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_character, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
        holder.itemView.setOnClickListener { listener(character) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewCharacterBinding.bind(view)
        fun bind(character: Character) {
            binding.character = character
        }
    }
    override fun getFilter(): Filter = customFilter

    private val customFilter = object: Filter() {
        override fun performFiltering(charSequence: CharSequence?): FilterResults {

            val charString = charSequence.toString()

            if (charString.isEmpty()){
                filterCharacters = characters
            }else {
                characters?.let {
                    val filteredList = mutableListOf<Character>()
                    val filterPattern = charSequence.toString().lowercase().trim { char -> char <= ' ' }
                    it.forEach { character ->
                        if (character.name.lowercase().contains(filterPattern) ||
                            character.description.lowercase().contains(filterPattern)) {
                            filteredList.add(character)
                        }
                    }
                   filterCharacters = filteredList
                }
            }
            val results = FilterResults()
            results.values = filterCharacters
            return results
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            filterCharacters = filterResults?.values as List<Character>
            submitList(filterCharacters)
        }

    }
}
package com.adrc95.marvelappsample.ui.main

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.adrc95.domain.Character

fun Fragment.buildMainEventState(
    navController: NavController = findNavController(),
) = MainEventState(navController)

class MainEventState(private val navController: NavController) {

    fun onCharacterClicked(character: Character) {
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(character.id, character.name)
        navController.navigate(action)
    }

}

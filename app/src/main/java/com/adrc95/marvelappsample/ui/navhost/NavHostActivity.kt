package com.adrc95.marvelappsample.ui.navhost

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import arrow.core.some
import com.adrc95.marvelappsample.BR
import com.adrc95.marvelappsample.R
import com.adrc95.marvelappsample.databinding.ActivityNavHostBinding
import com.adrc95.marvelappsample.databinding.MenuActionDarkmodeBinding
import com.adrc95.marvelappsample.ui.common.launchAndCollect
import com.adrc95.marvelappsample.ui.common.setVisible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NavHostActivity : AppCompatActivity() {

    private val binding by lazy { ActivityNavHostBinding.inflate(layoutInflater) }

    private val menuObservable by lazy { NavHostMenuObservable() }

    private val viewModel: NavHostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding) {
            observable = menuObservable

            val navController = findNavController(R.id.nav_host_fragment)
            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_main,
                    R.id.navigation_favorite
                )
            )
            toolbar.title = getString(R.string.characters)
            setSupportActionBar(toolbar)
            toolbar.setupWithNavController(navController, appBarConfiguration)
            bottomNavigation.setupWithNavController(navController)

            launchAndCollect(viewModel.uiState){
                modeType = it.mode
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_host_menu, menu)
        val menuDarkMode = menu.findItem(R.id.action_darkmode)
        val binding: MenuActionDarkmodeBinding = MenuActionDarkmodeBinding.inflate(layoutInflater)
        binding.apply {
            menuObservable.darkmode
            onChangeTheme = {
                it.toString()
            }
            menuDarkMode?.apply {
                actionView = root
                setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
            }
        }
        return super.onCreateOptionsMenu(menu)
    }


    fun showBottomBar() = with(binding) {
        bottomNavigation.setVisible(true)
    }

    fun hideBottomBar() = with(binding) {
        bottomNavigation.setVisible(false)
    }
}
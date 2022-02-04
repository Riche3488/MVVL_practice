package com.udacity.shoestore

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Timber.plant(Timber.DebugTree())

//        val navController: NavController = findNavController(R.id.nav_host_fragment)
//        val appBarConfiguration = AppBarConfiguration(navController.graph)
//        toolbar.setupWithNavController(navController,appBarConfiguration)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        //val navView: NavigationView = findViewById(R.id.nav_graph)
        val navController = findNavController(R.id.nav_host_fragment)

//        appBarConfiguration = AppBarConfiguration(setOf(
//            ,R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
//        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
            if (nd.id == R.id.shoeListFragment) {

                supportActionBar?.show()
            } else {
                supportActionBar?.hide()

            }
        }
        appBarConfiguration = AppBarConfiguration(setOf(R.id.instructionFragment))
        //setupActionBarWithNavController(navController, appBarConfiguration)
        toolbar.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.log_out -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_shoeListFragment_to_loginFragment)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}

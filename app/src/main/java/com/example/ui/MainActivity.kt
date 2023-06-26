package com.example.ui

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import android.widget.ViewAnimator
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var imageAdapter: ImageAdapter

    private val viewModel: MainActivityViewModel by viewModels {
        MainActivityViewModel.Factory(
            application, imageRepository = ImageRepository()
        )
    }


    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserve()
        initAction()


        drawerLayout = binding.drawer
        val navView: NavigationView = binding.navigationView
        val toggleButton: ImageView = binding.navBarImageView
        var isDashboardDisplayed = false

        val forwardArrow: ImageView = navView.getHeaderView(0).findViewById(R.id.forwardArrow)
        val contentAnimator: ViewAnimator = binding.contentAnimator
        val drawerLayout: DrawerLayout = binding.drawer

        forwardArrow.setOnClickListener {
            isDashboardDisplayed = !isDashboardDisplayed

            if (isDashboardDisplayed) {
                contentAnimator.displayedChild = 1
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            } else {
                contentAnimator.displayedChild = 0
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.open,
            R.string.close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        toggleButton.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.firstItem -> Toast.makeText(
                    applicationContext,
                    "Clicked Home",
                    Toast.LENGTH_SHORT
                ).show()

                R.id.secondItem -> Toast.makeText(
                    applicationContext,
                    "Clicked Message",
                    Toast.LENGTH_SHORT
                ).show()

                R.id.thirdItem -> Toast.makeText(
                    applicationContext,
                    "Clicked Sync",
                    Toast.LENGTH_SHORT
                ).show()
            }
            true
        }

        binding.apply {
            val layoutManager = GridLayoutManager(this@MainActivity, 2)
            recyclerView.layoutManager = layoutManager
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initObserve() {
        viewModel.list.observe(this) { fruits ->
            fruits?.let{
                binding.recyclerView.adapter = imageAdapter
                imageAdapter.submitList(fruits)

            }

        }
    }
    private fun initAction(){
        viewModel.setImageList()
        imageAdapter = ImageAdapter{
        }
    }
}



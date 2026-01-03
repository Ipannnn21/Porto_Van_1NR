package com.portfolio.app

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.navigation.NavigationView
import com.portfolio.app.databinding.ActivityMainBinding
import com.portfolio.app.util.ThemeManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var themeManager: ThemeManager
    private lateinit var btnThemeToggle: ImageButton
    private lateinit var btnMenu: ImageButton
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        themeManager = ThemeManager(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnThemeToggle = findViewById(R.id.btn_theme_toggle)
        btnMenu = findViewById(R.id.btn_menu)
        drawerLayout = findViewById(R.id.drawer_layout)

        lifecycleScope.launch {
            val isDarkMode = themeManager.isDarkMode.first()
            applyThemeMode(isDarkMode)
            updateThemeIcon(isDarkMode)
        }

        setupNavigation()
        setupThemeToggle()
        setupMenuButton()
        setupCloseButton()
        setupProfileImage()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val navView: NavigationView = binding.navView
        navView.setNavigationItemSelectedListener { menuItem ->
            // Close the navigation drawer
            drawerLayout.closeDrawer(GravityCompat.END)

            // Navigate to the selected destination
            NavigationUI.onNavDestinationSelected(menuItem, navController)

            true
        }
    }

    private fun setupThemeToggle() {
        btnThemeToggle.setOnClickListener {
            lifecycleScope.launch {
                val currentMode = themeManager.isDarkMode.first()
                val newMode = !currentMode
                themeManager.setDarkMode(newMode)
                updateThemeIcon(newMode)
                applyThemeMode(newMode)
            }
        }
    }

    private fun setupMenuButton() {
        btnMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.END)
        }
    }

    private fun setupCloseButton() {
        val headerView = binding.navView.getHeaderView(0)
        val btnCloseNav = headerView.findViewById<ImageButton>(R.id.btn_close_nav)
        btnCloseNav.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.END)
        }
    }

    private fun setupProfileImage() {
        val headerView = binding.navView.getHeaderView(0)
        val profileImage = headerView.findViewById<ShapeableImageView>(R.id.profile_image)
        profileImage.setImageResource(R.drawable.poto_profil)
    }

    private fun applyThemeMode(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END)
        } else {
            super.onBackPressed()
        }
    }

    private fun updateThemeIcon(isDarkMode: Boolean) {
        if (isDarkMode) {
            btnThemeToggle.setImageResource(R.drawable.ic_light_mode)
        } else {
            btnThemeToggle.setImageResource(R.drawable.ic_dark_mode)
        }
    }
}

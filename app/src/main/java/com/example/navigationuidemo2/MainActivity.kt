package com.example.navigationuidemo2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout

import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toolbar: MaterialToolbar
    private val viewModel: TenantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        Log.d("LIFECYCLE", "MainActivity - onCreate")

        drawerLayout = findViewById(R.id.drawer_layout)
            navView = findViewById(R.id.nav_view)
            toolbar = findViewById(R.id.topAppBar)

            // Open drawer when ☰ icon is clicked
            toolbar.setNavigationOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            // Handle navigation item selection
            navView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_dashboard -> {
                        Toast.makeText(this, "Dashboard clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.nav_add_tenant -> {
                        val intent = Intent(this, AddTenant::class.java)
                        startActivityForResult(intent, 1)
                        true
                    }
                    R.id.nav_logout -> {
                        Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }.also {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
            }




        // Initialize dashboard data
        setupDashboard()
        observeViewModel()

        val btnAddTenantNav = findViewById<Button>(R.id.btnAddTenants)

        btnAddTenantNav.setOnClickListener {
            val intent = Intent(this, AddTenant::class.java)
            startActivity(intent)
        }
    }

    private fun setupDashboard() {
        // Dashboard will be updated through ViewModel observers
    }

    private fun observeViewModel() {
        viewModel.tenantCount.observe(this) { count ->
            findViewById<TextView>(R.id.tvTotalRegisteredTenants).text = count.toString()
        }

        viewModel.tenantName.observe(this) { name ->
            if (name.isNotEmpty()) {
                findViewById<TextView>(R.id.tvTotalRentExpected).text = "Latest: $name"
                updateTenantDisplay()
            } else {
                findViewById<TextView>(R.id.tvTotalRentExpected).text = "No tenants yet"
            }
        }
    }

    private fun updateTenantDisplay() {
        // Simple tenant display in the existing layout
        val name = viewModel.tenantName.value ?: ""
        val unit = viewModel.unitNumber.value ?: ""
        val rent = viewModel.monthlyRent.value ?: ""
        
        if (name.isNotEmpty()) {
            // Update the tenant info display
            val tenantInfo = "👤 $name - Unit: $unit - Rent: $$rent"
            findViewById<TextView>(R.id.tvEmptyState).apply {
                text = tenantInfo
                visibility = android.view.View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "MainActivity - onResume")
        // Refresh dashboard data when returning from AddTenant
        updateTenantDisplay()
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "MainActivity - onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "MainActivity - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "MainActivity - onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("LIFECYCLE", "MainActivity - onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "MainActivity - onDestroy")
    }
}



//
//| Lifecycle Method | When it's Called                         | Common Use                   |
//| ---------------- | ---------------------------------------- | ---------------------------- |
//| `onCreate()`     | When activity is first created           | Initialize things, set up UI |
//| `onStart()`      | Right before it becomes visible          | Prepare UI updates           |
//| `onResume()`     | Activity is now in foreground            | Resume animations, sensors   |
//| `onPause()`      | Activity is partially obscured           | Pause heavy tasks            |
//| `onStop()`       | Activity no longer visible               | Release resources            |
//| `onDestroy()`    | Activity is finishing or being destroyed | Cleanup                      |
//| `onRestart()`    | After `onStop()`, before `onStart()`     | Re-initialize resources      |



//Drawables Logic


//private lateinit var drawerLayout: DrawerLayout
//private lateinit var navView: NavigationView
//private lateinit var toolbar: MaterialToolbar


//drawerLayout = findViewById(R.id.drawer_layout)
//navView = findViewById(R.id.nav_view)
//toolbar = findViewById(R.id.topAppBar)
//
//// Open drawer when ☰ icon is clicked
//toolbar.setNavigationOnClickListener {
//    drawerLayout.openDrawer(GravityCompat.START)
//}
//// Handle navigation item selection
//navView.setNavigationItemSelectedListener { menuItem ->
//    when (menuItem.itemId) {
//        R.id.nav_dashboard -> {
//            Toast.makeText(this, "Dashboard clicked", Toast.LENGTH_SHORT).show()
//            true
//        }
//        R.id.nav_add_tenant -> {
//            val intent = Intent(this, AddTenant::class.java)
//            startActivityForResult(intent, 1)
//            true
//        }
//        R.id.nav_logout -> {
//            Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show()
//            true
//        }
//        else -> false
//    }.also {
//        drawerLayout.closeDrawer(GravityCompat.START)
//    }
//}
//

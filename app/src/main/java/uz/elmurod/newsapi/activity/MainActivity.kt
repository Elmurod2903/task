package uz.elmurod.newsapi.activity

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.elmurod.core.base.BaseActivity
import uz.elmurod.navigation.Direction
import uz.elmurod.navigation.Navigate
import uz.elmurod.navigation.Navigator
import uz.elmurod.newsapi.R
import uz.elmurod.newsapi.checkinternet.NetworkChangeListener
import uz.elmurod.newsapi.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView


@AndroidEntryPoint
class MainActivity : BaseActivity(), Navigator {
    private lateinit var binding: ActivityMainBinding
    private val navigator = Navigate()
    private lateinit var navController: NavController
    private var broadcastReceiver: BroadcastReceiver? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        broadcastReceiver = NetworkChangeListener(this, this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(uz.elmurod.newsapi.R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        navigator.navController = navController
        binding.bottomNavigationView.setupWithNavController(navController)

        binding.bottomNavigationView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            when (it.itemId) {
                R.id.main -> {
                    navigate(Direction.Main)
                }
                R.id.search -> {
                    navigate(Direction.Search)
                }
                R.id.saved -> {
                    navigate(Direction.Saved)
                }
            }
            return@OnItemSelectedListener true
        })

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.mainFragment -> showBottomNav()
                R.id.searchFragment -> showBottomNav()
                R.id.savedFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.bottomNavigationView.visibility = View.GONE

    }

    override fun navigate(direction: Direction, args: Any?) {
        navigator.navigate(direction, args)
    }

    override fun onResume() {
        super.onResume()
        this.registerReceiver(
            this.broadcastReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)

        )
    }

    override fun onStart() {
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(broadcastReceiver, intentFilter)
        super.onStart()
    }

    override fun onStop() {
        unregisterReceiver(broadcastReceiver)
        super.onStop()
    }

    override fun onBackPressed() {
        val selected = binding.bottomNavigationView.selectedItemId
        if (R.id.main == selected) {
            finish()
        } else {
            binding.bottomNavigationView.selectedItemId = R.id.main
        }
    }

}
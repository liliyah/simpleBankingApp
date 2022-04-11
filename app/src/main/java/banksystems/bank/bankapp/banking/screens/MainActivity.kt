package banksystems.bank.bankapp.banking.screens

import android.animation.ObjectAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import banksystems.bank.bankapp.banking.R
import androidx.core.splashscreen.SplashScreen
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Banking)

        setContentView(R.layout.activity_main)
//
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
//navController=navHostFragment.findNavController()
//        setSupportActionBar(findViewById(R.id.toolbar))
//        setupActionBarWithNavController(navController)
//    }

//    override fun onSupportNavigateUp(): Boolean {
//return navController.navigateUp() || super.onSupportNavigateUp()
//
//    }
    }
}
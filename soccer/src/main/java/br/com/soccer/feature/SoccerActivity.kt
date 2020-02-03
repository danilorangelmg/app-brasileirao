package br.com.soccer.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import br.com.soccer.common.extensions.gone
import br.com.soccer.common.extensions.visible
import br.com.soccer.common.whenClicking
import kotlinx.android.synthetic.main.activity_soccer.*
import loadKoinFeatures
import unloadKoinFeatures
import kotlin.system.exitProcess

class SoccerActivity: AppCompatActivity() {

    val toolbarTitleLiveData = MutableLiveData<String>()
    val showBackButtonToolbarLiveData = MutableLiveData<Boolean>()
    val gameButtonToolbarLiveData = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinFeatures()
        setContentView(R.layout.activity_soccer)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setupToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinFeatures()
    }

    private fun setupToolbar() {
        toolbarTitleLiveData.observe(this, Observer {
            tvToolbarTitle.text = it
        })

        gameButtonToolbarLiveData.observe(this, Observer {
            if (it) ivGames.visible() else ivGames.gone()
        })

        showBackButtonToolbarLiveData.observe(this, Observer {
            supportActionBar?.setDisplayHomeAsUpEnabled(it)
        })

        ivGames.setOnClickListener {
            getNavController().navigate(R.id.fragmentGame)
        }

//          whenClicking(ivGames){ println("Before >>>>>") } then { println("After") }
    }

    private fun getNavController(): NavController {
        return Navigation.findNavController(this, R.id.nav_host)
    }

    override fun onSupportNavigateUp(): Boolean {
        return getNavController().navigateUp()
    }

    override fun onBackPressed() {
        if (getNavController().currentDestination?.label == "Home") {
            exitProcess(0)
        } else {
            super.onBackPressed()
        }

    }
}
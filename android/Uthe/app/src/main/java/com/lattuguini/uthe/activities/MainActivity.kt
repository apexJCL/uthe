package com.lattuguini.uthe.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.lattuguini.uthe.R
import com.lattuguini.uthe.fragments.PipingFragment
import com.lattuguini.uthe.fragments.SettingsFragment
import com.lattuguini.uthe.fragments.StatisticsFragment
import com.lattuguini.uthe.shared.Constants
import com.lattuguini.uthe.shared.Delegates

class MainActivity: AppCompatActivity() {
	
	companion object {
		val PIPING_TAG = "piping"
		val STATISTICS_TAG = "statistics"
		val SETTINGS_TAG = "settings"
		
		private val LOGIN_RESULT = 0x01
	}
	
	private var piping: PipingFragment by Delegates.lazy { PipingFragment() }
	private var statistics: StatisticsFragment by Delegates.lazy { StatisticsFragment() }
	private var settings: SettingsFragment by Delegates.lazy { SettingsFragment() }
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		init()
		if (!islogged())
			showLogin()
		else
			obtainId()
	}
	
	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (resultCode != Activity.RESULT_OK) {
			finish()
			return
		}
	}
	
	private fun init() {
		(findViewById(R.id.navigator) as BottomNavigationView).setOnNavigationItemSelectedListener {
			var fragment: Fragment? = null
			var tag = ""
			when (it.itemId) {
				R.id.piping_item     -> {
					fragment = piping
					tag = PIPING_TAG
				}
				R.id.statistics_item -> {
					fragment = statistics
					tag = STATISTICS_TAG
				}
				R.id.settings_item   -> {
					fragment = settings
					
					tag = SETTINGS_TAG
				}
			}
			
			changeFragment(fragment!!, tag)
			true
		}
		changeFragment(piping, PIPING_TAG)
	}
	
	/**
	 * Changes the actual fragment if it's not visible
	 *
	 * @fragment    New fragment to show
	 * @tag         New fragment's tag
	 */
	private fun changeFragment(fragment: Fragment,
	                           tag: String) {
		val manager = supportFragmentManager
		if (manager.findFragmentByTag(tag) != null)
			return
		
		manager.beginTransaction()
				.replace(R.id.content, fragment, tag)
				.commit()
	}
	
	private fun islogged(): Boolean
			= getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE).getBoolean(Constants.IS_LOGGED, false)
	
	private fun obtainId() {
		Constants.ID = getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE).getInt(Constants.USER_ID, -1)
	}
	
	private fun showLogin() {
		startActivityForResult(Intent(this@MainActivity, LoginActivity::class.java), LOGIN_RESULT)
	}
	
}
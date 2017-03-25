package com.lattuguini.uthe.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.lattuguini.uthe.R
import com.lattuguini.uthe.activities.MainActivity

/**
 * Created by lattuguini on 24/03/17.
 */
class SettingsFragment : Fragment() {
	
	override fun onCreateView(inflater: LayoutInflater?,
	                          container: ViewGroup?,
	                          savedInstanceState: Bundle?): View? {
		setHasOptionsMenu(true)
		return inflater!!.inflate(R.layout.fragment_settings, container, false)
	}
	
	override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
		super.onCreateOptionsMenu(menu, inflater)
		inflater!!.inflate(R.menu.settings_menu, menu)
	}
	
	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		when(item!!.itemId) {
			R.id.logout_item -> {
				(activity as MainActivity).logout()
			}
		}
		return super.onOptionsItemSelected(item)
	}
	
	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val toolbar = view!!.findViewById(R.id.toolbar) as Toolbar
		val activity = activity as AppCompatActivity
		activity.setSupportActionBar(toolbar)
		activity.supportActionBar!!.title = resources.getString(R.string.app_name)
	}
	
}
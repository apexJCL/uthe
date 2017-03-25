package com.lattuguini.uthe.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.lattuguini.uthe.R
import com.lattuguini.uthe.activities.LoginActivity
import com.lattuguini.uthe.net.Service
import com.lattuguini.uthe.shared.Delegates

/**
 * Created by lattuguini on 24/03/17.
 */
class SignupFragment: Fragment() {
	
	private var service: Service by Delegates.lazy {
		Service()
	}
	private var loginActivity: LoginActivity by Delegates.lazy {
		activity as LoginActivity
	}
	
	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		setHasOptionsMenu(true)
		return inflater!!.inflate(R.layout.fragment_signup, container, false)
	}
	
	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val toolbar = view!!.findViewById(R.id.toolbar) as Toolbar
		val activity = activity as AppCompatActivity
		activity.setSupportActionBar(toolbar)
		activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
		activity.supportActionBar!!.title =  resources.getString(R.string.signup)
	}
	
	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		if (item!!.itemId == android.R.id.home)
			loginActivity.returnFromSignup()
		
		return super.onOptionsItemSelected(item)
	}
	
}
package com.lattuguini.uthe.fragments

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lattuguini.uthe.R
import com.lattuguini.uthe.activities.LoginActivity
import com.lattuguini.uthe.net.Service
import com.lattuguini.uthe.shared.Delegates

/**
 * Created by lattuguini on 24/03/17.
 */
class LoginFragment: Fragment() {
	
	private var email: TextInputEditText by Delegates.lazy {
		view!!.findViewById(R.id.in_email_user) as TextInputEditText
	}
	
	private var pass: TextInputEditText by Delegates.lazy {
		view!!.findViewById(R.id.in_pass) as TextInputEditText
	}
	
	private var service: Service by Delegates.lazy {
		Service()
	}
	private var loginActivity: LoginActivity by Delegates.lazy {
		activity as LoginActivity
	}
	
	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
			= inflater!!.inflate(R.layout.fragment_login, container, false)
	
	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		view!!.findViewById(R.id.b_login).setOnClickListener { login() }
		view.findViewById(R.id.b_signup).setOnClickListener { signup() }
		view.findViewById(R.id.b_forgot).setOnClickListener { forgot() }
	}
	
	private fun login() {
		loginActivity.login()
	}
	
	private fun signup() {
		loginActivity.signup()
	}
	
	private fun forgot() {
		
	}
	
}
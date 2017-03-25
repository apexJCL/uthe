package com.lattuguini.uthe.activities

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lattuguini.uthe.R
import com.lattuguini.uthe.fragments.LoginFragment
import com.lattuguini.uthe.fragments.SignupFragment
import com.lattuguini.uthe.shared.Constants
import com.lattuguini.uthe.shared.Delegates

/**
 * Created by lattuguini on 24/03/17.
 */
class LoginActivity: AppCompatActivity() {
	
	private var login: LoginFragment by Delegates.lazy {
		LoginFragment()
	}
	private var signup: SignupFragment by Delegates.lazy {
		SignupFragment()
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity)
		
		init()
	}
	
	private fun init() {
		supportFragmentManager.beginTransaction()
				.add(R.id.content, login, login.tag)
				.commit()
	}
	
	fun signup() {
		supportFragmentManager.beginTransaction()
				.replace(R.id.content, signup, signup.tag)
				.addToBackStack(signup.tag)
				.commit()
	}
	
	fun returnFromSignup() {
		supportFragmentManager.popBackStack()
	}
	
	fun login(id: Int) {
		val editor = getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE).edit()
		editor.putBoolean(Constants.IS_LOGGED, true)
		editor.putInt(Constants.USER_ID, id)
		editor.apply()
		setResult(Activity.RESULT_OK)
		finish()
	}
	
}
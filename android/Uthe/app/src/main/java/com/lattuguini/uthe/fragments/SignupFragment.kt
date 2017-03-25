package com.lattuguini.uthe.fragments

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
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
	
	private var service: Service by Delegates.lazy { Service() }
	private var loginActivity: LoginActivity by Delegates.lazy { activity as LoginActivity }
	private var username: TextInputEditText by Delegates.lazy { view!!.findViewById(R.id.in_username) as TextInputEditText }
	private var firstname: TextInputEditText by Delegates.lazy { view!!.findViewById(R.id.in_first_name) as TextInputEditText }
	private var lastname: TextInputEditText by Delegates.lazy { view!!.findViewById(R.id.in_last_name) as TextInputEditText }
	private var email: TextInputEditText by Delegates.lazy { view!!.findViewById(R.id.in_email) as TextInputEditText }
	private var pass: TextInputEditText by Delegates.lazy { view!!.findViewById(R.id.in_pass) as TextInputEditText }
	private var passAgain: TextInputEditText by Delegates.lazy { view!!.findViewById(R.id.in_pass_again) as TextInputEditText }
	
	private var usernameValidation = false
	private var firstnameValidation = false
	private var lastnameValidation = false
	private var emailValidation = false
	private var passwordValidation = false
	
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
		activity.supportActionBar!!.title = resources.getString(R.string.signup)
		
		val emptyField = resources.getString(R.string.empty_field)
		val incorrectEmail = resources.getString(R.string.incorrect_email)
		val passwordError = resources.getString(R.string.password_error)
		addTextWatcher(username,
				view.findViewById(R.id.til_username) as TextInputLayout,
				emptyField,
				this::validateEmptyField)
		addTextWatcher(firstname,
				view.findViewById(R.id.til_username) as TextInputLayout,
				emptyField,
				this::validateEmptyField)
		addTextWatcher(lastname,
				view.findViewById(R.id.til_username) as TextInputLayout,
				emptyField,
				this::validateEmptyField)
		addTextWatcher(email,
				view.findViewById(R.id.til_username) as TextInputLayout,
				emptyField,
				this::validateEmptyField)
		addTextWatcher(pass,
				view.findViewById(R.id.til_username) as TextInputLayout,
				emptyField,
				this::validateEmptyField)
		addTextWatcher(passAgain,
				view.findViewById(R.id.til_username) as TextInputLayout,
				emptyField,
				this::validateEmptyField)
	}
	
	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		if (item!!.itemId == android.R.id.home)
			loginActivity.returnFromSignup()
		
		return super.onOptionsItemSelected(item)
	}
	
	private fun addTextWatcher(input: TextInputEditText,
	                           layout: TextInputLayout,
	                           error: String,
	                           validation: (String) -> Boolean,
	                           showError: TextInputLayout? = null) {
		input.addTextChangedListener(object: TextWatcher {
			override fun afterTextChanged(s: Editable?) {}
			
			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
			
			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
				
			}
		})
	}
	
	private fun validateEmptyField(name: String): Boolean = name.isEmpty()
	
	private fun validateEmail(email: String): Boolean
			= !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
	
	private fun validatePasswords(): Boolean = pass.text.toString() == passAgain.text.toString()
	
}
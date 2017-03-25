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
import android.widget.Toast
import com.lattuguini.uthe.R
import com.lattuguini.uthe.activities.LoginActivity
import com.lattuguini.uthe.net.Service
import com.lattuguini.uthe.shared.Delegates
import com.lattuguini.uthe.shared.Models
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
	private var layer: View by Delegates.lazy { view!!.findViewById(R.id.layer) }
	private var loader: View by Delegates.lazy { view!!.findViewById(R.id.loader) }
	
	private var validations = BooleanArray(5, { false })
	private val USERNAME = 0
	private val EMAIL = 1
	private val FIRSTNAME = 2
	private val LASTNAME = 3
	private val PASSWORD = 4
	
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
		view.findViewById(R.id.fab_signup).setOnClickListener { signup() }
		
		addValidations(view)
	}
	
	/**
	 * Add awesome validations to input fields
	 */
	private fun addValidations(view: View) {
		val emptyField = resources.getString(R.string.empty_field)
		val incorrectEmail = resources.getString(R.string.incorrect_email)
		val passwordError = resources.getString(R.string.password_error)
		val til_username = view.findViewById(R.id.til_username) as TextInputLayout
		val til_email = view.findViewById(R.id.til_email) as TextInputLayout
		val til_firstname = view.findViewById(R.id.til_first_name) as TextInputLayout
		val til_lastname = view.findViewById(R.id.til_last_name) as TextInputLayout
		val til_pass = view.findViewById(R.id.til_pass) as TextInputLayout
		val til_passagain = view.findViewById(R.id.til_pass_again) as TextInputLayout
		addTextWatcher(username,
				til_username,
				emptyField,
				this::validateEmptyField,
				USERNAME)
		addTextWatcher(firstname,
				til_firstname,
				emptyField,
				this::validateEmptyField,
				FIRSTNAME)
		addTextWatcher(lastname,
				til_lastname,
				emptyField,
				this::validateEmptyField,
				LASTNAME)
		addTextWatcher(pass,
				til_pass,
				emptyField,
				this::validateEmptyField,
				PASSWORD)
		addTextWatcher(passAgain,
				til_passagain,
				emptyField,
				this::validateEmptyField,
				PASSWORD)
		addTextWatcher(email,
				til_email,
				incorrectEmail,
				this::validateEmail,
				EMAIL)
		addTextWatcher(pass,
				passwordError,
				this::validatePasswords,
				til_passagain,
				PASSWORD)
		addTextWatcher(passAgain,
				passwordError,
				this::validatePasswords,
				til_passagain,
				PASSWORD)
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
	                           valIndex: Int) {
		input.addTextChangedListener(object: TextWatcher {
			override fun afterTextChanged(s: Editable?) {}
			
			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
			
			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
				if (!validation(s.toString())) {
					layout.error = error
					validations[valIndex] = false
				} else {
					layout.error = null
					validations[valIndex] = true
				}
			}
		})
	}
	
	private fun addTextWatcher(input: TextInputEditText,
	                           error: String,
	                           validation: () -> Boolean,
	                           errorLayout: TextInputLayout,
	                           valIndex: Int) {
		input.addTextChangedListener(object: TextWatcher {
			override fun afterTextChanged(s: Editable?) {}
			
			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
			
			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
				if (!validation()) {
					errorLayout.error = error
					validations[valIndex] = false
				} else {
					errorLayout.error = null
					validations[valIndex] = true
				}
			}
		})
	}
	
	private fun validateEmptyField(name: String): Boolean = !name.isEmpty()
	
	private fun validateEmail(email: String): Boolean
			= !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
	
	private fun validatePasswords(): Boolean = pass.text.toString() == passAgain.text.toString()
	
	private fun signup() {
		validations.forEach {
			if (!it) {
				return
			}
		}
		
		signup(username.text.toString(),
				pass.text.toString(),
				email.text.toString(),
				firstname.text.toString(),
				lastname.text.toString())
	}
	
	private fun signup(username: String,
	                   password: String,
	                   email: String,
	                   firstname: String,
	                   lastname: String) {
		loader.visibility = View.VISIBLE
		layer.visibility = View.VISIBLE
		service.signup(username,
				password,
				email,
				firstname,
				lastname,
				object: Callback<Models.RegisterResponse> {
					override fun onFailure(call: Call<Models.RegisterResponse>?, t: Throwable?) {
						loader.visibility = View.GONE
						layer.visibility = View.GONE
						Toast.makeText(context, "Error: ${t!!.message}", Toast.LENGTH_SHORT).show()
					}
					
					override fun onResponse(call: Call<Models.RegisterResponse>?, response: Response<Models.RegisterResponse>?) {
						loader.visibility = View.GONE
						layer.visibility = View.GONE
						
						val register = response!!.body()
						if (register == null) {
							Toast.makeText(context, "Error ${response.code()}", Toast.LENGTH_SHORT).show()
							return
						}
						if (register.has_errors) {
							Toast.makeText(context, register.errors, Toast.LENGTH_SHORT).show()
							return
						}
						
						loginActivity.login(register.user_id)
					}
					
				})
	}
	
}
package com.lattuguini.uthe.net

import android.webkit.ValueCallback
import com.lattuguini.uthe.shared.Constants
import com.lattuguini.uthe.shared.Models
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by lattuguini on 24/03/17.
 */
class Service {
	
	val api: API
	
	init {
		val logging = HttpLoggingInterceptor()
		logging.level = HttpLoggingInterceptor.Level.BODY
		val clientBuilder = OkHttpClient.Builder()
				.connectTimeout(60, TimeUnit.SECONDS)
				.addInterceptor(logging)
		val retrofit = Retrofit.Builder()
				.baseUrl(Constants.URL)
				.client(clientBuilder.build())
				.addConverterFactory(GsonConverterFactory.create())
				.build()
		api = retrofit.create(API::class.java)
	}
	
	fun getIntake(id: Int,
	              callback: Callback<Models.Intake>)
			= api.getIntake(id).enqueue(callback)
	
	fun signup(username: String,
	           password: String,
	           email: String,
	           firstname: String,
	           lastname: String,
	           callback: Callback<Models.RegisterResponse>)
			= api.signup(Models.Register(username,
			password,
			email,
			Models.userdata(firstname, lastname)))
			.enqueue(callback)
	
	fun login(username: String,
	          pass: String,
	          callback: Callback<Int>)
			= api.login(Models.Login(username, pass)).enqueue(callback)
	
}
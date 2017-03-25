package com.lattuguini.uthe.net

import com.lattuguini.uthe.shared.Constants
import com.lattuguini.uthe.shared.Models
import okhttp3.OkHttpClient
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
		val clientBuilder = OkHttpClient.Builder()
				.connectTimeout(60, TimeUnit.SECONDS)
		val client = clientBuilder.build()
		val retrofit = Retrofit.Builder()
				.baseUrl(Constants.URL)
				.client(client)
				.addConverterFactory(GsonConverterFactory.create())
				.build()
		api = retrofit.create(API::class.java)
	}
	
	fun getIntake(id: Int,
	              callback: Callback<Models.Intake>) {
		api.getIntake(id).enqueue(callback)
	}
	
}
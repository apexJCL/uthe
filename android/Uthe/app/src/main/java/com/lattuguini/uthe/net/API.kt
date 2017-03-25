package com.lattuguini.uthe.net

import com.lattuguini.uthe.shared.Models
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by lattuguini on 24/03/17.
 */
interface API {
	
	/**
	 * Returns an intake's details
	 *
	 * @param id    Intake's id
	 */
	@GET("/intakes/{id}")
	fun getIntake(@Path("id") id: Int): Call<Models.Intake>
	
	@Headers(
		"Accept: application/json"
	)
	@POST("/user/signup")
	fun signup(@Body register: Models.Register): Call<Models.RegisterResponse>
	
	@Headers(
			"Accept: application/json"
	)
	@POST("/user/login")
	fun login(@Body login: Models.Login): Call<Int>
	
	@GET("/user/{id}/statistics")
	fun getStatistics(@Path("id") id: Int): Call<Models.Statistics>
	
	@GET("/control/enable/{user_id}/{intake_id}")
	fun enableControl(@Path("user_id") user_id: Int,
	                  @Path("intake_id") intake_id: Int): Call<Boolean>
	
}
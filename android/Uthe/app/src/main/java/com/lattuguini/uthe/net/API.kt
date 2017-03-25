package com.lattuguini.uthe.net

import com.lattuguini.uthe.shared.Models
import retrofit2.Call
import retrofit2.http.GET
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
	
}
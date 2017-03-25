package com.lattuguini.uthe.shared

/**
 * Created by lattuguini on 24/03/17.
 */
object Models {
	
	data class Intake(val id: Int,
	                  val description: String,
	                  val time: Int,
	                  val volume: Int)
	
	data class RegisterResponse(val user_id: Int,
	                            val has_errors: Boolean,
	                            val errors: String)
	
	data class Login(val username: String,
	                 val password: String)
	
	data class Statistics(val username: String,
	                      val first_name: String,
	                      val last_name: String,
	                      val allowed_liters: Int,
	                      val consumed_liters: Int,
	                      val remaining_liters: Int)
	
	data class Register(val username: String,
	                    val password: String,
	                    val email: String,
	                    val userdata: userdata)
	
	data class userdata(val first_name: String,
	                    val last_name: String)
	
}
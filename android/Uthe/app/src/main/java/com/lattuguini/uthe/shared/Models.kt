package com.lattuguini.uthe.shared

/**
 * Created by lattuguini on 24/03/17.
 */
object Models {
	
	data class Intake(val id: Int,
	                  val description: String,
	                  val time: Int,
	                  val volume: Int)
	
	data class UserIntakes(val id: Int,
	                       val user_id: Int,
	                       val intake_id: Int,
	                       val time: Int,
	                       val volume: Int)
	
	data class UserData(val id: Int,
	                    val user_id: Int,
	                    val firstName: String,
	                    val lastName: String,
	                    val allowed_liters: Int,
	                    val consumed_liters: Int)
	
}
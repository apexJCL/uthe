package com.lattuguini.uthe.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lattuguini.uthe.R
import com.lattuguini.uthe.net.Service
import com.lattuguini.uthe.shared.Constants
import com.lattuguini.uthe.shared.Delegates
import com.lattuguini.uthe.shared.Models
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by lattuguini on 24/03/17.
 */
class StatisticsFragment : Fragment() {
	
	var service: Service by Delegates.lazy { Service() }
	
	override fun onCreateView(inflater: LayoutInflater?,
	                          container: ViewGroup?,
	                          savedInstanceState: Bundle?): View?
			= inflater!!.inflate(R.layout.fragment_statistics, container, false)
	
	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}
	
	override fun onResume() {
		super.onResume()
		loadData()
	}
	
	private fun loadData() {
		service.getStatistics(Constants.ID,
				object: Callback<Models.Statistics> {
					override fun onResponse(call: Call<Models.Statistics>?, response: Response<Models.Statistics>?) {
						println(response!!.body())
					}
					
					override fun onFailure(call: Call<Models.Statistics>?, t: Throwable?) {
						println("ERROR")
					}
					
				})
	}
	
}
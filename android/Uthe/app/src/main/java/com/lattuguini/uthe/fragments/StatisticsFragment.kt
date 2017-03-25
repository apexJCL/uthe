package com.lattuguini.uthe.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lattuguini.uthe.R
import com.lattuguini.uthe.adapters.StatisticsAdapter
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
	var recycler: RecyclerView by Delegates.lazy { view!!.findViewById(R.id.recycler) as RecyclerView }
	private var layer: View by Delegates.lazy { view!!.findViewById(R.id.layer) }
	private var loader: View by Delegates.lazy { view!!.findViewById(R.id.loader) }
	var adapter = StatisticsAdapter()
	
	override fun onCreateView(inflater: LayoutInflater?,
	                          container: ViewGroup?,
	                          savedInstanceState: Bundle?): View?
			= inflater!!.inflate(R.layout.fragment_statistics, container, false)
	
	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val toolbar = view!!.findViewById(R.id.toolbar) as Toolbar
		val activity = activity as AppCompatActivity
		recycler.layoutManager = LinearLayoutManager(context)
		recycler.hasFixedSize()
		recycler.adapter = adapter
		activity.setSupportActionBar(toolbar)
		activity.supportActionBar!!.title = resources.getString(R.string.statistics_title)
	}
	
	override fun onResume() {
		super.onResume()
		loadData()
	}
	
	private fun loadData() {
		loader.visibility = View.VISIBLE
		layer.visibility = View.VISIBLE
		service.getStatistics(Constants.ID,
				object: Callback<Models.Statistics> {
					override fun onResponse(call: Call<Models.Statistics>?, response: Response<Models.Statistics>?) {
						adapter.clear()
						adapter.addItem(response!!.body())
						loader.visibility = View.GONE
						layer.visibility = View.GONE
					}
					
					override fun onFailure(call: Call<Models.Statistics>?, t: Throwable?) {
						println("Error ${t!!.message}")
						loader.visibility = View.GONE
						layer.visibility = View.GONE
					}
					
				})
	}
	
}
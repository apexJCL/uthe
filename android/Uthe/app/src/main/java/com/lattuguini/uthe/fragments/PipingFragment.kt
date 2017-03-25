package com.lattuguini.uthe.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lattuguini.uthe.R
import com.lattuguini.uthe.activities.MainActivity
import com.lattuguini.uthe.shared.Delegates

/**
 * Created by lattuguini on 24/03/17.
 */
class PipingFragment : Fragment() {
	
	val mainActivity: MainActivity by Delegates.lazy { activity as MainActivity }
	
	override fun onCreateView(inflater: LayoutInflater?,
	                          container: ViewGroup?,
	                          savedInstanceState: Bundle?): View?
			= inflater!!.inflate(R.layout.fragment_piping, container, false)
	
	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		view!!.findViewById(R.id.b_scan).setOnClickListener { showScan() }
	}
	
	private fun showScan() {
		mainActivity.addExtraFragment(ScanFragment())
	}
	
}
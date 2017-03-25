package com.lattuguini.uthe.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lattuguini.uthe.R

/**
 * Created by lattuguini on 24/03/17.
 */
class PipingFragment : Fragment() {
	
	override fun onCreateView(inflater: LayoutInflater?,
	                          container: ViewGroup?,
	                          savedInstanceState: Bundle?): View?
			= inflater!!.inflate(R.layout.fragment_piping, container, false)
	
}
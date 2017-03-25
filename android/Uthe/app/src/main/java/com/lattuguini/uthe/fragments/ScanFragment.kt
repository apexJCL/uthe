package com.lattuguini.uthe.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import com.lattuguini.uthe.R
import com.lattuguini.uthe.activities.MainActivity
import com.lattuguini.uthe.net.Service
import com.lattuguini.uthe.shared.Constants
import com.lattuguini.uthe.shared.Delegates
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by lattuguini on 25/03/17.
 */
class ScanFragment: Fragment() {
	
	private val mainActivity: MainActivity by Delegates.lazy { activity as MainActivity }
	private val PERMISSIONS = 1
	private var scan: QRCodeReaderView by Delegates.lazy { view!!.findViewById(R.id.qr_surface) as QRCodeReaderView }
	private var layer: View by Delegates.lazy { view!!.findViewById(R.id.layer) }
	private var loader: View by Delegates.lazy { view!!.findViewById(R.id.loader) }
	private var service: Service by Delegates.lazy { Service() }
	
	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		setHasOptionsMenu(true)
		return inflater!!.inflate(R.layout.fragment_scan, container, false)
	}
	
	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		checkForPermissions()
	}
	
	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		if(item!!.itemId == android.R.id.home)
			mainActivity.popFragment()
		return super.onOptionsItemSelected(item)
	}
	
	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		if(requestCode == PERMISSIONS &&
				grantResults.isNotEmpty() &&
				grantResults[0] == PackageManager.PERMISSION_GRANTED)
			checkForPermissions()
	}
	
	private fun checkForPermissions() {
		if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
			requestPermissions(arrayOf(Manifest.permission.CAMERA), PERMISSIONS)
			return
		}
		
		initScanner()
	}
	
	private fun initScanner() {
		scan.setQRDecodingEnabled(true)
		scan.setAutofocusInterval(2000L)
		scan.setBackCamera()
		scan.setOnQRCodeReadListener { text, points ->
			if(text.contains("http://example.com/"))
				sendData(text.replace("http://example.com/", "").toInt())
		}
	}
	
	fun sendData(id: Int) {
		loader.visibility = View.VISIBLE
		layer.visibility = View.VISIBLE
		scan.setQRDecodingEnabled(false)
		
		service.enableControl(Constants.ID,
				id,
				object: Callback<Boolean> {
					override fun onResponse(call: Call<Boolean>?, response: Response<Boolean>?) {
						loader.visibility = View.GONE
						layer.visibility = View.GONE
						if(response!!.code() == 200)
							mainActivity.popFragment()
						else
							Toast.makeText(context, "Error ${response.code()}", Toast.LENGTH_SHORT).show()
					}
					
					override fun onFailure(call: Call<Boolean>?, t: Throwable?) {
						loader.visibility = View.GONE
						layer.visibility = View.GONE
						Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
					}
				})
	}
	
}
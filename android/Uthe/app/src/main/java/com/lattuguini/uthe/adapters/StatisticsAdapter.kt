package com.lattuguini.uthe.adapters

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lattuguini.uthe.R
import com.lattuguini.uthe.shared.Delegates
import com.lattuguini.uthe.shared.Models

/**
 * Created by lattuguini on 25/03/17.
 */
class StatisticsAdapter : RecyclerView.Adapter<StatisticsAdapter.Viewholder>() {
	
	private val items = ArrayList<Models.Statistics>()
	
	fun addItem(item: Models.Statistics) {
		items.add(item)
		notifyDataSetChanged()
	}
	
	fun addItems(items: Array<Models.Statistics>) {
		this.items.addAll(items)
		notifyDataSetChanged()
	}
	
	fun clear() {
		items.clear()
		notifyDataSetChanged()
	}
	
	override fun onBindViewHolder(holder: Viewholder?, position: Int) {
		holder!!.setData(items[position])
	}
	
	override fun getItemCount(): Int = items.size
	
	override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Viewholder {
		return Viewholder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_statistics, null))
	}
	
	class Viewholder(view: View) : RecyclerView.ViewHolder(view) {
		
		private var allowed: AppCompatTextView by Delegates.lazy { view.findViewById(R.id.allowed_liters) as AppCompatTextView }
		private var consumed: AppCompatTextView by Delegates.lazy { view.findViewById(R.id.consumed_liters) as AppCompatTextView }
		private var remaining: AppCompatTextView by Delegates.lazy { view.findViewById(R.id.remaining_liters) as AppCompatTextView }
		
		fun setData(statistics: Models.Statistics) {
			allowed.text = "${statistics.allowed_liters}"
			consumed.text = "${statistics.consumed_liters}"
			remaining.text = "${statistics.remaining_liters}"
		}
		
	}
	
}
package com.lattuguini.uthe.shared

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by lattuguini on 24/03/17.
 */
object Delegates {
	
	private class FragmentDelegate<T>(initializer: () -> T): ReadWriteProperty<Any?, T> {
		
		private var initializer: (() -> T)? = initializer
		private var value: T? = null
		
		override fun getValue(thisRef: Any?, property: KProperty<*>): T {
			return value ?: initializer!!()
		}
		
		override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
			this.value = value
		}
		
	}
	
	fun <T> lazyFragment(initializer: () -> T): ReadWriteProperty<Any?, T> = FragmentDelegate(initializer)
	
}
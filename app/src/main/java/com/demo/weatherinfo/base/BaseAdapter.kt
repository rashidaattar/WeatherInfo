package com.demo.weatherinfo.base

import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Rashida on 4/4/20.
 *
 */
/**
 * Created by Rashida on 4/8/19.
 */
abstract class BaseAdapter<T, E : RecyclerView.ViewHolder?> : RecyclerView.Adapter<E>() {
    protected var list: List<T>? = null

    fun setData(list: List<T>?) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (list.isNullOrEmpty())
            return 0
        else
            return list!!.size
    }
}

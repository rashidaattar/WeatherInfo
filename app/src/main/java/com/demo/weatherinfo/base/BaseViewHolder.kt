package com.demo.weatherinfo.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Rashida on 4/4/20.
 *
 */
open class BaseViewHolder<out T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)

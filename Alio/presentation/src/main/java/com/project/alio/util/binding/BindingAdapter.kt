package com.project.alio.util.binding

import android.content.Context
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.project.alio.R
import java.util.*

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("setWeekClick")
    fun bindSetWeekClickState(view: AppCompatButton, activity: AppCompatActivity) {
        val select = ContextCompat.getDrawable(
            activity as Context,
            R.drawable.background_week_selected
        )

        val unSelect = ContextCompat.getDrawable(
            activity as Context,
            R.drawable.background_week_unselected
        )

        view.setOnClickListener {
            if (view.background == select) {
                view.background = unSelect
            } else {
                view.background = select
            }
        }
    }

    @JvmStatic
    @BindingAdapter("setTime")
    fun bindSetTime(view: TextView, time: Calendar) {
        val hour = time.get(Calendar.HOUR_OF_DAY)
        val min = time.get(Calendar.MINUTE)
        view.text = String.format("%02d:%02d", hour, min)
    }

    @JvmStatic
    @BindingAdapter("setMainDayOfWeek")
    fun bindSetMainDayOfWeek(view: TextView, isEnable: Boolean) {
        if (isEnable)
            view.setTextColor(ContextCompat.getColor(view.context, R.color.black))
        else
            view.setTextColor(ContextCompat.getColor(view.context, R.color.gray))

    }

}
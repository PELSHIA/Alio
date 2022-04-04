package com.project.alio.view.adapter.binding

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.project.alio.R

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
}
package com.talentland.talentlandappandroid.core.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.ShapeAppearanceModel
import com.talentland.talentlandappandroid.core.CornerStyle

/**
 * ViewHolder base que aplica esquinas redondeadas según la posición del item.
 */
abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected fun applyCornerRadius(view: View, cornerStyle: CornerStyle) {
        val radius = 10f.dpToPx()

        if (view is MaterialCardView) {
            val shapeAppearanceModel = ShapeAppearanceModel.builder()

            when (cornerStyle) {
                CornerStyle.TOP -> {
                    shapeAppearanceModel.setTopLeftCornerSize(radius)
                    shapeAppearanceModel.setTopRightCornerSize(radius)
                    shapeAppearanceModel.setBottomLeftCornerSize(0f)
                    shapeAppearanceModel.setBottomRightCornerSize(0f)
                }

                CornerStyle.MIDDLE -> {
                    shapeAppearanceModel.setAllCornerSizes(0f)
                }

                CornerStyle.BOTTOM -> {
                    shapeAppearanceModel.setTopLeftCornerSize(0f)
                    shapeAppearanceModel.setTopRightCornerSize(0f)
                    shapeAppearanceModel.setBottomLeftCornerSize(radius)
                    shapeAppearanceModel.setBottomRightCornerSize(radius)
                }

                CornerStyle.SINGLE,
                CornerStyle.FULL -> {
                    shapeAppearanceModel.setAllCornerSizes(radius)
                }
            }

            view.shapeAppearanceModel = shapeAppearanceModel.build()

            val elevation = when (cornerStyle) {
                CornerStyle.TOP,
                CornerStyle.BOTTOM,
                CornerStyle.SINGLE,
                CornerStyle.FULL -> 2f.dpToPx()

                else -> 2f.dpToPx()
            }
            view.cardElevation = elevation
            // No sobrescribir strokeWidth si ya está definido en el XML
            // view.strokeWidth = 0
        }
    }

    private fun Float.dpToPx(): Float {
        return this * itemView.context.resources.displayMetrics.density
    }
}



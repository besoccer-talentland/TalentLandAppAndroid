package com.talentland.talentlandappandroid.core.util

import android.content.Context
import android.util.TypedValue

/**
 * Convierte un valor en dp a píxeles.
 * 
 * @param context El contexto para acceder a los recursos.
 * @return El valor convertido a píxeles.
 */
fun Int.dpToPx(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        context.resources.displayMetrics
    ).toInt()
}


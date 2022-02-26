package de.orome.myshoppal.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton


class CustomButton(context: Context, attrs: AttributeSet): AppCompatButton(context,attrs) {
    init {
        applyFont()
    }
    private fun applyFont() {
        val regularTypeface: Typeface =
            Typeface.createFromAsset(context.assets,"Montserrat-Bold.ttf")
        typeface = regularTypeface
    }
}
package de.orome.myshoppal.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class TextViewRegular(context: Context, attributeSet: AttributeSet): AppCompatTextView(context, attributeSet) {

    init {
        applyFont()
    }
    private fun applyFont() {
        val regularTypeface: Typeface =
            Typeface.createFromAsset(context.assets,"Montserrat-Regular.ttf")
            typeface = regularTypeface
    }
}
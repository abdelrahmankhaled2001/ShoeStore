package com.udacity.shoestore

import android.widget.EditText
import androidx.databinding.InverseMethod

object StringDoubleConverter{
    @InverseMethod("toDouble")
    @JvmStatic fun toString(value: Double): String {

return value.toString()
    }

    @JvmStatic fun toDouble(
        value:String
    ): Double {
        if(value=="")
            return 0.0
return value.toDouble()
    }
}

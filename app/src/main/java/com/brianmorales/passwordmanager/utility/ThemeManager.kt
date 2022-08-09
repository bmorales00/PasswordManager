package com.brianmorales.passwordmanager.utility

import android.content.Context
import android.content.res.Resources
import com.brianmorales.passwordmanager.R

class ThemeManager {
    companion object{
        fun setTheme(context: Context){
            val themeId =  R.style.Theme_NoActionBar

            context.setTheme(themeId)
        }
    }

}
package com.android.mvvmandroidlib.utills

import java.util.regex.Pattern

object StringUtils {

    fun isPasswordValid(password: String): Boolean {

        return if (password.length >= 8) {
            val letter = Pattern.compile("[a-zA-z]")
            val digit = Pattern.compile("[0-9]")
            val special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")
            //Pattern eight = Pattern.compile (".{8}");


            val hasLetter = letter.matcher(password)
            val hasDigit = digit.matcher(password)
            val hasSpecial = special.matcher(password)

            hasLetter.find() && hasDigit.find() && hasSpecial.find()

        } else
            false

    }
}

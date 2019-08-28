package com.android.mvvmandroidlib.utills

import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.text.TextUtils
import java.util.regex.Pattern

object StringUtils {

    private const val SPECIAL_CHARACTER: String = "[!@#$%&*()_+=|<>?{}\\[\\]~-]"
    private const val DIGIT: String = "[0-9]"
    private const val ALPHABET: String = "[a-zA-Z]"
    private const val ALPHABET_SMALL: String = "[a-z]"
    private const val ALPHABET_CAPS: String = "[A-Z]"

    enum class PasswordType {
        ONLY_DIGIT,
        ONLY_ALPHABET,
        DIGIT_WITH_ALPHABET,
        DIGIT_WITH_SPECIAL_CHARACTER,
        ALPHABET_WITH_SPECIAL_CHARACTER,
        DIGIT_WITH_ALPHABET_AND_SPECIAL_CHARACTER,
        DIGIT_WITH_AT_LEAST_ONE_CAPS_ALPHABET,
        DIGIT_WITH_AT_LEAST_ONE_CAPS_ALPHABET_AND_SPECIAL_CHARACTER
    }

    /**
     * Method is used to check whether is password valid or not.
     */
    fun isPasswordValid(password: String, length: Int, type: PasswordType): Boolean {

        val letter = Pattern.compile(ALPHABET)
        val letterCaps = Pattern.compile(ALPHABET_CAPS)
        val digit = Pattern.compile(DIGIT)
        val special = Pattern.compile(SPECIAL_CHARACTER)
        //Pattern eight = Pattern.compile (".{8}");


        val hasLetter = letter.matcher(password)
        val hasLetterCaps = letterCaps.matcher(password)
        val hasDigit = digit.matcher(password)
        val hasSpecial = special.matcher(password)

        if (password.length < length) {
            return false
        }

        return when (type) {
            PasswordType.ONLY_DIGIT -> {
                hasDigit.find()
            }
            PasswordType.ONLY_ALPHABET -> {
                hasLetter.find()
            }
            PasswordType.DIGIT_WITH_ALPHABET -> {
                hasDigit.find() && hasLetter.find()
            }
            PasswordType.DIGIT_WITH_SPECIAL_CHARACTER -> {
                hasDigit.find() && hasSpecial.find()
            }
            PasswordType.ALPHABET_WITH_SPECIAL_CHARACTER -> {
                hasLetter.find() && hasSpecial.find()
            }
            PasswordType.DIGIT_WITH_ALPHABET_AND_SPECIAL_CHARACTER -> {
                hasDigit.find() && hasLetter.find() && hasSpecial.find()
            }
            PasswordType.DIGIT_WITH_AT_LEAST_ONE_CAPS_ALPHABET -> {
                hasDigit.find() && hasLetterCaps.find()
            }
            PasswordType.DIGIT_WITH_AT_LEAST_ONE_CAPS_ALPHABET_AND_SPECIAL_CHARACTER -> {
                hasDigit.find() && hasLetterCaps.find() && hasSpecial.find()
            }
        }
    }

    fun isNullOrEmpty(@Nullable str: String?): Boolean {
        return TextUtils.isEmpty(str)
    }

    fun nullIfEmpty(@Nullable str: String?): String? {
        return if (isNullOrEmpty(str)) null else str
    }

    @NonNull
    fun emptyIfNull(@Nullable str: String?): String {
        return str ?: ""
    }

    fun length(@Nullable str: String?): Int {
        return if (isNullOrEmpty(str)) 0 else str!!.length
    }
}

package com.android.mvvmandroidlib.utills

import java.util.regex.Pattern

object StringUtils {

    private const val SPECIAL_CHARACTER: String = "[!@#$%&*()_+=|<>?{}\\[\\]~-]"
    private const val DIGIT: String = "[0-9]"
    private const val ALPHABET: String = "[a-zA-Z]"
    private const val ALPHABET_SMALL: String = "[a-z]"
    private const val ALPHABET_CAPS: String = "[A-Z]"

    enum class PASSWORD_TYPE {
        ONLY_DIGIT,
        ONLY_ALPHABET,
        DIGIT_WITH_ALPHABET,
        DIGIT_WITH_SPECIAL_CHARACTER,
        ALPHABET_WITH_SPECIAL_CHARACTER,
        DIGIT_WITH_ALPHABET_AND_SPECIAL_CHARACTER,
        DIGIT_WITH_ATLEAST_ONE_CAPS_ALPHABET,
        DIGIT_WITH_ATLEAST_ONE_CAPS_ALPHABET_AND_SPECIAL_CHARACTER
    }

    /**
     * Method is used to check whether is password valid or not.
     */
    fun isPasswordValid(password: String, length: Int, type: PASSWORD_TYPE): Boolean {

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
            PASSWORD_TYPE.ONLY_DIGIT -> {
                hasDigit.find()
            }
            PASSWORD_TYPE.ONLY_ALPHABET -> {
                hasLetter.find()
            }
            PASSWORD_TYPE.DIGIT_WITH_ALPHABET -> {
                hasDigit.find() && hasLetter.find()
            }
            PASSWORD_TYPE.DIGIT_WITH_SPECIAL_CHARACTER -> {
                hasDigit.find() && hasSpecial.find()
            }
            PASSWORD_TYPE.ALPHABET_WITH_SPECIAL_CHARACTER -> {
                hasLetter.find() && hasSpecial.find()
            }
            PASSWORD_TYPE.DIGIT_WITH_ALPHABET_AND_SPECIAL_CHARACTER -> {
                hasDigit.find() && hasLetter.find() && hasSpecial.find()
            }
            PASSWORD_TYPE.DIGIT_WITH_ATLEAST_ONE_CAPS_ALPHABET -> {
                hasDigit.find() && hasLetterCaps.find()
            }
            PASSWORD_TYPE.DIGIT_WITH_ATLEAST_ONE_CAPS_ALPHABET_AND_SPECIAL_CHARACTER -> {
                hasDigit.find() && hasLetterCaps.find() && hasSpecial.find()
            }
        }
    }
}

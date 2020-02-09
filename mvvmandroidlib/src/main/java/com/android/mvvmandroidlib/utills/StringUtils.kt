package com.android.mvvmandroidlib.utills

import android.text.TextUtils
import android.util.Patterns
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import java.util.regex.Pattern

/**
 * This util class is used to provide methods related to String.
 * Ex - It is used to check string is empty or not etc..
 * This class is declared as object('Singleton').
 *
 * @author Shubham Chauhan
 */
object StringUtils {

    private const val SPECIAL_CHARACTER: String = "[!@#$%&*()_+=|<>?{}\\[\\]~-]"
    private const val DIGIT: String = "[0-9]"
    private const val ALPHABET: String = "[a-zA-Z]"
    private const val ALPHABET_SMALL: String = "[a-z]"
    private const val ALPHABET_CAPS: String = "[A-Z]"
    private const val EMAIL_SYMBOL: String = "@"
    const val EMPTY = ""

    /**
     * Enum class to represent password type.
     */
    enum class PasswordType {
        ALPHANUMERIC,
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
     *
     * @param password password
     * @param length length of password
     * @param type password type
     *
     * @return true if password is valid otherwise false.
     */
    fun isPasswordValid(
        password: String?,
        length: Int = 4,
        type: PasswordType = PasswordType.ALPHANUMERIC
    ): Boolean {

        if (isNullOrEmpty(password) || password?.length ?: 0 < length) {
            return false
        }

        val letter = Pattern.compile(ALPHABET)
        val letterCaps = Pattern.compile(ALPHABET_CAPS)
        val digit = Pattern.compile(DIGIT)
        val special = Pattern.compile(SPECIAL_CHARACTER)

        val hasLetter = letter.matcher(password)
        val hasLetterCaps = letterCaps.matcher(password)
        val hasDigit = digit.matcher(password)
        val hasSpecial = special.matcher(password)

        return when (type) {
            PasswordType.ALPHANUMERIC -> {
                hasDigit.find() || hasLetter.find() || hasLetterCaps.find()
            }
            PasswordType.ONLY_DIGIT -> {
                hasDigit.find() && !hasLetter.find()
            }
            PasswordType.ONLY_ALPHABET -> {
                hasLetter.find() && !hasDigit.find()
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

    /**
     * Method is used to check that user name is valid or not.
     *
     * @param username username
     * @return true if valid otherwise false
     */
    fun isUserNameValid(username: String?): Boolean {
        if (isNullOrEmpty(username)) {
            return false
        }
        return if (username?.contains(EMAIL_SYMBOL) == true) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            Patterns.PHONE.matcher(username).matches()
        }
    }

    /**
     * Method is used to check that email id is valid or not.
     *
     * @param emailId emailId
     * @return true if valid otherwise false
     */
    fun isEmailIdValid(emailId: String?): Boolean {
        return !isNullOrEmpty(emailId) && Patterns.EMAIL_ADDRESS.matcher(emailId).matches()
    }

    /**
     * Method is used to check that phone number is valid or not.
     *
     * @param phoneNumber phoneNumber
     * @return true if valid otherwise false
     */
    fun isPhoneNumberValid(phoneNumber: String?): Boolean {
        return !isNullOrEmpty(phoneNumber) && Patterns.PHONE.matcher(phoneNumber).matches()
    }

    /**
     * Method is used to know that given string is null or empty.
     *
     * @param str string
     * @return true if data is null or empty otherwise else.
     */
    fun isNullOrEmpty(@Nullable str: String?): Boolean {
        return TextUtils.isEmpty(str)
    }

    /**
     * Method is used to set data null if it is empty.
     *
     * @param str string
     * @return null if empty otherwise return actual data.
     */
    fun nullIfEmpty(@Nullable str: String?): String? {
        return if (isNullOrEmpty(str)) null else str
    }

    /**
     * Method is used to set data empty if it is null.
     *
     * @param str string
     * @return empty if null otherwise return actual data.
     */
    @NonNull
    fun emptyIfNull(@Nullable str: String?): String {
        return str ?: EMPTY
    }

    /**
     * Method is used to get length of data.
     *
     * @param str string
     * @return length of data.
     */
    fun length(@Nullable str: String?): Int {
        return if (isNullOrEmpty(str)) 0 else str?.length ?: 0
    }
}

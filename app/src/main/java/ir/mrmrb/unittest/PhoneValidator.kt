package ir.mrmrb.unittest

import java.util.regex.Pattern
import javax.inject.Inject

class PhoneValidator @Inject constructor() {
    companion object{
        const val PHONE_REGEX = "09(1[0-9]|3[1-9]|2[1-9])-?[0-9]{3}-?[0-9]{4}"
    }
    fun check(phone:String):Boolean{
        return Pattern.compile(PHONE_REGEX).matcher(phone).matches()
    }
}
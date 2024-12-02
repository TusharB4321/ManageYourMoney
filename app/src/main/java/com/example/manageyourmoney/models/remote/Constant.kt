package com.example.manageyourmoney.models.remote

import com.example.manageyourmoney.utils.Resources

object Constant {

    private const val REGEX = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    fun isPasswordValid(password:String,callback:(Resources<String>)->Unit):Boolean{
        return if (password.toString().length>= 8){
            true
        }else{
            callback(Resources.Error("Password should contain at least 8 characters"))
            false
        }
    }

     fun doPasswordsMatch(password: String,confirmPassword:String,callback: (Resources<String>) -> Unit): Boolean {
        return if (password== confirmPassword) {
            true
        } else {
            callback(Resources.Error("Passwords should match"))
            false
        }
    }

     fun isEmailValid(email:String,callback: (Resources<String>) -> Unit): Boolean {
        return if (email.isNotEmpty() && email.matches(Regex(REGEX))
        ) {
            true
        } else {
            callback(Resources.Error("Enter a valid email"))
            false
        }
    }


}
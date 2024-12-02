package com.example.manageyourmoney.repository
import com.example.manageyourmoney.models.remote.Constant
import com.example.manageyourmoney.models.remote.data.User
import com.example.manageyourmoney.utils.Resources
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class AuthRepository(
    private val auth:FirebaseAuth,
    private val database: DatabaseReference
)
{
    fun registerUser(email:String,password:String,
                     confirmPassword:String,
                     callback:(Resources<String>)->Unit)
    {
        if (Constant.isEmailValid(email,callback)&&Constant.isPasswordValid(password,callback)
            &&Constant.doPasswordsMatch(password,confirmPassword,callback)){

            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {task->
                    if (task.isSuccessful) {
                       val userId=auth.currentUser?.uid?:return@addOnCompleteListener
                        val user=User(email,password)

                        database.child("users").child(userId).setValue(user)
                            .addOnCompleteListener { dbTask->
                                if (dbTask.isSuccessful){
                                    callback(Resources.Success("User registered successfully"))
                                }else{
                                    callback(Resources.Error(dbTask.exception?.localizedMessage ?: "Error saving user data"))

                                }

                            }
                    }else{
                        callback(Resources.Error(task.exception?.localizedMessage ?: "Error creating user"))
                    }

                }
        }
    }

    fun signInUser(email:String,password: String,callback: (Resources<String>) -> Unit){

        if (Constant.isEmailValid(email,callback)&&Constant.isPasswordValid(password,callback))
        {
           auth.signInWithEmailAndPassword(email,password)
               .addOnCompleteListener {task->
                   if (task.isSuccessful){
                       callback(Resources.Success("Sign In Successfully"))
                   }else{
                       callback(Resources.Error(task.exception?.localizedMessage ?: "Sign-In Failed"))
                   }
               }
        }
        }
}
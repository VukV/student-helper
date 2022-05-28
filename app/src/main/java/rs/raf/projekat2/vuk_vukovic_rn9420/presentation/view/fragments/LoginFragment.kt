package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import org.koin.android.ext.android.inject
import rs.raf.projekat2.vuk_vukovic_rn9420.R
import rs.raf.projekat2.vuk_vukovic_rn9420.data.alreadyLoggedIn
import rs.raf.projekat2.vuk_vukovic_rn9420.data.passwordData
import rs.raf.projekat2.vuk_vukovic_rn9420.data.usernameData


class LoginFragment: Fragment() {

    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                setupLogin()
            }
        }
    }

    @Composable
    private fun setupLogin(){
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ){
            var username by remember { mutableStateOf(TextFieldValue()) }
            var password by remember { mutableStateOf(TextFieldValue()) }

            Text(
                text = "Login",
                fontSize = 30.sp
            )
            TextField(
                value = username,
                onValueChange = {username = it},
                singleLine = true,
                label = {Text(text = "Korisničko ime")},
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color(0xFF0099BC),
                    focusedLabelColor = Color(0xFF0099BC),
                    cursorColor = Color(0xFF0099BC)
                ),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 20.sp
                )
            )
            TextField(
                value = password,
                onValueChange = {password = it},
                singleLine = true,
                label = {Text(text = "Šifra")},
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color(0xFF0099BC),
                    focusedLabelColor = Color(0xFF0099BC),
                    cursorColor = Color(0xFF0099BC)
                ),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 20.sp
                ),
                visualTransformation = PasswordVisualTransformation()
            )
            Button(onClick = {
                if (checkUsername(username.text) && checkPassword(password.text)){
                    loginToSharedPref()
                    continueToApp()
                }
            },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0099BC)))
            {
                Text(text = "Login", fontSize = 20.sp, color = Color.White)
            }
        }
    }

    private fun checkUsername(username: String): Boolean{
        return if (username == usernameData){
            true
        } else{
            Toast.makeText(activity, "Pogrešno korisničko ime", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun checkPassword(password: String): Boolean{
        return if (password == passwordData){
            true
        } else{
            Toast.makeText(activity, "Pogrešna Šifra", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun loginToSharedPref(){
        sharedPreferences.edit().putBoolean(alreadyLoggedIn, true).apply()
    }

    private fun continueToApp(){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragmentContainerMain, MainFragment())
        transaction?.commit()
    }
}
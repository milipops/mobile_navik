package com.example.navik.screens

import android.bluetooth.BluetoothHidDeviceAppQosSettings
import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navik.R
import kotlinx.coroutines.delay
import androidx.navigation.NavController


@Composable
fun SignIn(
    onSignInSuccess: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotPassword: () -> Unit,
    navController: NavController
) {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val isEmailValid = remember { mutableStateOf(true) }

    val isEmailEmpty = remember { mutableStateOf(false) }
    val isPasswordValid = remember { mutableStateOf(true) }
    val isPasswordEmpty = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                textAlign = TextAlign.Center,
                text = "Sign In",
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.padding(15.dp))

            Card(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                elevation = CardDefaults.elevatedCardElevation(20.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                TextField(
                    value = email.value,
                    onValueChange = {
                        email.value = it
                        isEmailValid.value = Patterns.EMAIL_ADDRESS.matcher(it).matches()
                    },
                    placeholder = { Text("Email") },
                    modifier = Modifier.padding(12.dp),
                    maxLines = 1,
                    isError = !isEmailValid.value
                )
            }

            if (!isEmailValid.value) {
                Text(
                    text = "Invalid email address",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 10.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(5.dp))
            Card(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                elevation = CardDefaults.elevatedCardElevation(20.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                TextField(
                    value = password.value,
                    onValueChange = { password.value = it
                                    isPasswordValid.value = it.length >= 6
                                    isPasswordEmpty.value = it.isEmpty()
                                    },
                    placeholder = { Text("Password") },
                    modifier = Modifier.padding(12.dp),
                    maxLines = 1,
                    visualTransformation = PasswordVisualTransformation(),
                    isError = !isEmailValid.value || !isPasswordEmpty.value

                )
            }

            if (!isEmailValid.value || !isPasswordEmpty.value){
                Text(
                    text = "Password must be at least 6 characters or password is empty ",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 10.dp, top = 4.dp)
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth().padding(top = 15.dp, bottom = 15.dp, end = 10.dp)
                    .clickable { onForgotPassword() },
                textAlign = TextAlign.End,
                text = "Forgot Password?",
                fontSize = 14.sp,
                color = Color(0xFF1D68FF)
            )
            Spacer(modifier = Modifier.padding(35.dp))

            Button(
                onClick = {
                    if (isEmailValid.value && isPasswordValid.value && isPasswordEmpty.value) {
                        onSignInSuccess()
                    }
                },
                modifier = Modifier.fillMaxWidth().heightIn(56.dp),
                enabled = isEmailValid.value && isPasswordValid.value && isPasswordEmpty.value
            ) {
                Text(text = "Sign In", fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp).clickable { onSignUpClick() },
                textAlign = TextAlign.Center,
                text = "No account? Sign Up",
                fontSize = 14.sp,
                color = Color(0xFF1D68FF)
            )
        }
    }
}






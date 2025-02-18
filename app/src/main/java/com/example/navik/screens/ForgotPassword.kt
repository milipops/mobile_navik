package com.example.navik.screens

import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun ForgotPassword(onForgotSignIn: () -> Unit, navController: NavController) {
    val email = rememberSaveable { mutableStateOf("") }
    val password1 = rememberSaveable { mutableStateOf("") }
    val password2 = rememberSaveable { mutableStateOf("") }

    val isEmailValid = remember { mutableStateOf(true) }
    val isPasswordSame = remember { mutableStateOf(true) }
    val isEmailEmpty = remember { mutableStateOf(false) }
    val isPasswordValid = remember { mutableStateOf(true) }
    val isPasswordEmpty = remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                text = "Change Password",
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.padding(20.dp))

            // Email
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                elevation = CardDefaults.elevatedCardElevation(20.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                TextField(
                    value = email.value,
                    onValueChange = {
                        email.value = it
                        isEmailValid.value = Patterns.EMAIL_ADDRESS.matcher(it).matches()
                        isEmailEmpty.value = it.isEmpty()
                    },
                    placeholder = { Text(text = "Email") },
                    enabled = true,
                    maxLines = 1,
                    modifier = Modifier.padding(12.dp),
                    isError = !isEmailValid.value || isEmailEmpty.value
                )
            }

            if (!isEmailValid.value || isEmailEmpty.value) {
                Text(
                    text = if (isEmailEmpty.value) "Email cannot be empty" else "Invalid email address",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 10.dp, top = 4.dp)
                )
            }

            // New Password
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                elevation = CardDefaults.elevatedCardElevation(20.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                TextField(
                    value = password1.value,
                    onValueChange = {
                        password1.value = it
                        isPasswordSame.value = (password1.value == password2.value)
                        isPasswordValid.value = it.length >= 6
                        isPasswordEmpty.value = it.isEmpty()
                    },
                    placeholder = { Text(text = "New Password") },
                    enabled = true,
                    maxLines = 1,
                    modifier = Modifier.padding(12.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    isError = !isPasswordValid.value || isPasswordEmpty.value
                )
            }

            if (!isPasswordValid.value || isPasswordEmpty.value) {
                Text(
                    text = "Password must be at least 6 characters or paswword is  empty",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 10.dp, top = 4.dp)
                )
            }

            // Confirm New Password
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                elevation = CardDefaults.elevatedCardElevation(20.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                TextField(
                    value = password2.value,
                    onValueChange = {
                        password2.value = it
                        isPasswordSame.value = (password1.value == it)
                    },
                    placeholder = {
                        Text(text = "Confirm New Password")
                                  },
                    enabled = true,
                    maxLines = 1,
                    modifier = Modifier.padding(12.dp),
                    isError = !isPasswordSame.value,
                    visualTransformation = PasswordVisualTransformation()
                )
            }

            if (!isPasswordSame.value) {
                Text(
                    text = "Passwords don't match",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 20.dp, top = 4.dp)
                )
            }

            Button(
                onClick = {
                    if (isEmailValid.value && isPasswordSame.value
                        && isPasswordValid.value && !isEmailEmpty.value) {
                        onForgotSignIn()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(56.dp)
                    .padding(35.dp),
                enabled = isEmailValid.value && isPasswordSame.value && isPasswordValid.value && !isEmailEmpty.value
            ) {
                Text(text = "Reset Password", fontSize = 16.sp, textAlign = TextAlign.Center)
            }
        }
    }
}

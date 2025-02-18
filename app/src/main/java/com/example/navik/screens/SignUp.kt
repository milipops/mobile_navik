package com.example.navik.screens

import android.util.Patterns
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SignUp(onSignUpSuccess: () -> Unit, navController: NavController) {
    val email = rememberSaveable { mutableStateOf("") }
    val name = rememberSaveable { mutableStateOf("") }
    val surname = rememberSaveable { mutableStateOf("") }
    val age = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    val isEmailValid = remember { mutableStateOf(true) }
    val isPasswordValid = remember { mutableStateOf(true) }
    val isAgeValid = remember { mutableStateOf(true) }
    val isFormValid = remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                text = "Sign Up",
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.padding(15.dp))

            // Name
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                elevation = CardDefaults.elevatedCardElevation(20.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                TextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    placeholder = { Text(text = "Name") },
                    maxLines = 1,
                    modifier = Modifier.padding(12.dp)
                )
            }

            // Surname
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                elevation = CardDefaults.elevatedCardElevation(20.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                TextField(
                    value = surname.value,
                    onValueChange = { surname.value = it },
                    placeholder = { Text(text = "Surname") },
                    maxLines = 1,
                    modifier = Modifier.padding(12.dp)
                )
            }

            // Email
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                elevation = CardDefaults.elevatedCardElevation(20.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                TextField(
                    value = email.value,
                    onValueChange = {
                        email.value = it
                        isEmailValid.value = Patterns.EMAIL_ADDRESS.matcher(it).matches()
                    },
                    placeholder = { Text(text = "Email") },
                    maxLines = 1,
                    modifier = Modifier.padding(12.dp),
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

            // Password
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                elevation = CardDefaults.elevatedCardElevation(20.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                TextField(
                    value = password.value,
                    onValueChange = {
                        password.value = it
                        isPasswordValid.value = it.length >= 6
                    },
                    placeholder = { Text(text = "Password") },
                    maxLines = 1,
                    modifier = Modifier.padding(12.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    isError = !isPasswordValid.value
                )
            }

            if (!isPasswordValid.value) {
                Text(
                    text = "Password must be at least 6 characters",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 10.dp, top = 4.dp)
                )
            }

            // Age
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                elevation = CardDefaults.elevatedCardElevation(20.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                TextField(
                    value = age.value,
                    onValueChange = { age.value = it
                                    isAgeValid.value = it.toIntOrNull() != null
                                    },
                    placeholder = { Text(text = "Age") },
                    maxLines = 1,
                    modifier = Modifier.padding(12.dp),
                    isError = !isAgeValid.value
                )
            }

            if (!isAgeValid.value){
                Text(
                    text = "Enter your age",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 20.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Button(
                onClick = {
                    if (isEmailValid.value && isPasswordValid.value && isAgeValid.value &&
                        name.value.isNotEmpty() && surname.value.isNotEmpty()
                        && email.value.isNotEmpty() && password.value.isNotEmpty()
                    ) {
                        onSignUpSuccess()
                    } else {
                        isFormValid.value = false
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(46.dp),
                enabled = isEmailValid.value && isPasswordValid.value && isAgeValid.value &&
                        name.value.isNotEmpty() && surname.value.isNotEmpty()
                        && email.value.isNotEmpty() && password.value.isNotEmpty()
            ) {
                Text(text = "Sign Up", fontSize = 15.sp)
            }

            if (!isFormValid.value) {
                Text(
                    text = "Please fill in all fields correctly",
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp, start = 10.dp)
                )
            }
        }
    }
}

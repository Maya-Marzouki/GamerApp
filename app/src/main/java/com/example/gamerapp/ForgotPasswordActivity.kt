package com.example.gamerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ForgotPasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForgotPasswordScreen(
                onSubmit = { code ->
                    val intent = Intent(this, OTPValidationActivity::class.java)
                    intent.putExtra("OTP_CODE", code)
                    startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun ForgotPasswordScreen(onSubmit: (String) -> Unit) {
    val redColor = Color(0xFFE53935) // rouge uniforme
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Forgot Password", fontSize = 22.sp)

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                cursorColor = redColor,
                focusedIndicatorColor = redColor,
                unfocusedIndicatorColor = Color.Gray,
                focusedLeadingIconColor = redColor,
                focusedTrailingIconColor = redColor,
                focusedLabelColor = redColor
            )
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { onSubmit("1234") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = redColor,
                contentColor = Color.White
            )
        ) {
            Text("Submit (Email)")
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { onSubmit("6789") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = redColor,
                contentColor = Color.White
            )
        ) {
            Text("Send SMS")
        }
    }
}
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TextFieldDefaults

class OTPValidationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val expectedCode = intent.getStringExtra("OTP_CODE")

        setContent {
            OTPValidationScreen(
                onVerify = { code ->
                    if (code == expectedCode) {
                        startActivity(Intent(this, ResetPasswordActivity::class.java))
                    } else {
                        Toast.makeText(this, "Invalid code", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}

@Composable
fun OTPValidationScreen(onVerify: (String) -> Unit) {
    val context = LocalContext.current
    var code by remember { mutableStateOf("") }
    val redColor = Color(0xFFE53935) // rouge uniforme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Enter OTP Code", fontSize = 22.sp)

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = code,
            onValueChange = { if (it.length <= 4) code = it },
            label = { Text("Code") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                cursorColor = Color(0xFFE53935),
                focusedIndicatorColor = Color(0xFFE53935),
                unfocusedIndicatorColor = Color.Gray,
                focusedLeadingIconColor = Color(0xFFE53935),
                focusedTrailingIconColor = Color(0xFFE53935),
                focusedLabelColor = Color(0xFFE53935)
            )
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { onVerify(code) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = redColor,
                contentColor = Color.White
            )
        ) {
            Text("Verify")
        }

        Spacer(Modifier.height(8.dp))

        TextButton(onClick = {
            Toast.makeText(context, "Resend Coming soon", Toast.LENGTH_SHORT).show()
        }) {
            Text("Resend Code", color = redColor)
        }
    }
}
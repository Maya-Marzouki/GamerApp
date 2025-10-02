package com.example.gamerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TextFieldDefaults

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(
                onLogin = { email, password ->
                    if (email == "test@test.com" && password == "1234") {
                        startActivity(Intent(this, HomeActivity::class.java))
                    } else {
                        Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
                    }
                },
                onSignUp = { startActivity(Intent(this, SignUpActivity::class.java)) },
                onForgot = { startActivity(Intent(this, ForgotPasswordActivity::class.java)) }
            )
        }
    }
}

@Composable
fun LoginScreen(
    onLogin: (String, String) -> Unit,
    onSignUp: () -> Unit,
    onForgot: () -> Unit
) {
    val context = LocalContext.current
    val redColor = Color(0xFFE53935)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(120.dp)
            )
            Spacer(Modifier.height(20.dp))

            Text("Login to your account", fontSize = 22.sp)

            Spacer(Modifier.height(20.dp))

            // Champs Email
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
                    focusedLabelColor = redColor
                )
            )

            Spacer(Modifier.height(8.dp))
            // Champs Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = redColor,
                    focusedIndicatorColor = redColor,
                    unfocusedIndicatorColor = Color.Gray,
                    focusedLabelColor = redColor
                )
            )

            Spacer(Modifier.height(16.dp))
            // Bouton Login
            Button(
                onClick = { onLogin(email, password) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = redColor,
                    contentColor = Color.White
                )
            ) {
                Text("Login")
            }

            Spacer(Modifier.height(8.dp))

            // Row Remember Me + Forgot
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        colors = CheckboxDefaults.colors(checkedColor = redColor)
                    )
                    Text("Remember Me")
                }
                TextButton(onClick = onForgot) {
                    Text("Forgot Password?", color = redColor)
                }
            }

            Spacer(Modifier.height(20.dp))

            // Boutons sociaux
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(
                    onClick = { Toast.makeText(context, "Facebook Coming soon", Toast.LENGTH_SHORT).show() },
                    colors = ButtonDefaults.buttonColors(containerColor = redColor, contentColor = Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = "Facebook",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Facebook")
                }

                Button(
                    onClick = {
                        // Naviguer vers ResetPasswordActivity
                        val intent = Intent(context, ResetPasswordActivity::class.java)
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = redColor, contentColor = Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Google",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Google")
                }
            }

            Spacer(Modifier.height(20.dp))

            // Register
            TextButton(onClick = onSignUp) {
                Text("Don’t have an account? Register Now", color = redColor)
            }
        }
    }
}
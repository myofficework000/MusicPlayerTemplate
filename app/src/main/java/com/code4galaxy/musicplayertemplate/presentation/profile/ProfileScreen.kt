package com.code4galaxy.musicplayertemplate.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.code4galaxy.musicplayertemplate.presentation.auth.AuthViewModel

@Composable
fun ProfileScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onLogoutClick: () -> Unit
) {

    val user by viewModel.user.collectAsState()

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0F0F12),
            Color(0xFF21102F)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .padding(24.dp)
    ) {

        Text(
            text = "Profile",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                modifier = Modifier.size(84.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF3A2150)
                )
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = Color.White,
                        modifier = Modifier.size(44.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier.size(18.dp)
            )

            Column {

                Text(
                    text = user?.email
                        ?.substringBefore("@")
                        ?.replaceFirstChar { it.uppercase() }
                        ?: "Music User",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = user?.email ?: "No email available",
                    color = Color(0xFFB8B2C2),
                    fontSize = 14.sp
                )
            }
        }

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        ProfileInfoCard(
            title = "Email",
            value = user?.email ?: "Not available",
            icon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email",
                    tint = Color(0xFFB56CFF)
                )
            }
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        ProfileInfoCard(
            title = "Email Verification",
            value =
                if (user?.isEmailVerified == true) {
                    "Verified"
                } else {
                    "Not Verified"
                },
            icon = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Verification",
                    tint =
                        if (user?.isEmailVerified == true) {
                            Color(0xFF66D17A)
                        } else {
                            Color(0xFFFFB74D)
                        }
                )
            }
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = {
                viewModel.logoutUser()
                onLogoutClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3A2150),
                contentColor = Color.White
            )
        ) {

            Icon(
                imageVector = Icons.Default.Logout,
                contentDescription = "Logout"
            )

            Spacer(
                modifier = Modifier.size(8.dp)
            )

            Text(
                text = "Logout",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun ProfileInfoCard(
    title: String,
    value: String,
    icon: @Composable () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A2630)
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            icon()

            Spacer(
                modifier = Modifier.size(14.dp)
            )

            Column {

                Text(
                    text = title,
                    color = Color(0xFFB8B2C2),
                    fontSize = 13.sp
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                Text(
                    text = value,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
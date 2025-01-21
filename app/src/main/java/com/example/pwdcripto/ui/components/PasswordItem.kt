package com.example.pwdcripto.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pwdcripto.data.local.entity.PasswordEntity

@Composable
fun PasswordItem (password: PasswordEntity) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Text(text = "${password.tag} : ",
                fontWeight = FontWeight.Bold
            )
            Text(text = password.password,
                style = MaterialTheme.typography.bodyMedium
                )

        }
    }
}
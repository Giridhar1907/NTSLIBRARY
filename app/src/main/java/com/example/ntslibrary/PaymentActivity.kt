package com.example.ntslibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ntslibrary.ui.theme.NTSLIBRARYTheme
import android.app.Activity
import android.content.Intent
import androidx.compose.ui.platform.LocalContext

class PaymentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NTSLIBRARYTheme {
                Surface { PaymentScreen(onClose = { finish() }) }
            }
        }
    }
}

@Composable
fun PaymentScreen(onClose: () -> Unit) {
    val context = LocalContext.current
    var cardType by remember { mutableStateOf(0) }
    var cardHolder by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var validUntil by remember { mutableStateOf("") }
    var securityCode by remember { mutableStateOf("") }
    var acceptTerms by remember { mutableStateOf(true) }
    var useDefault by remember { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 22.dp, start = 22.dp, end = 22.dp)
        ) {
            // Top bar with close and title
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    context.startActivity(
                        Intent(context, HomePage::class.java).apply {
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        }
                    )
                    if (context is Activity) {
                        (context as Activity).finish()
                    }
                }) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
                Spacer(Modifier.weight(1f))
                Text(
                    "Payment",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 36.dp)
                )
            }
            Spacer(Modifier.height(16.dp))
            // Select card type
            Text("Select card", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
            Spacer(Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                PaymentCardOption(
                    selected = cardType == 0,
                    onClick = { cardType = 0 },
                    icon = Icons.Default.CreditCard,
                    contentDesc = "Visa"
                )
                PaymentCardOption(
                    selected = cardType == 1,
                    onClick = { cardType = 1 },
                    icon = Icons.Default.Payment,
                    contentDesc = "MasterCard"
                )
                PaymentCardOption(
                    selected = cardType == 2,
                    onClick = { cardType = 2 },
                    icon = Icons.Default.AccountCircle,
                    contentDesc = "PayPal"
                )
            }
            Spacer(Modifier.height(18.dp))
            OutlinedTextField(
                value = cardHolder,
                onValueChange = { cardHolder = it },
                label = { Text("Card holder") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
                label = { Text("Card number") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(Icons.Default.CreditCard, null)
                },
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = validUntil,
                    onValueChange = { validUntil = it },
                    label = { Text("Valid until") },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                )
                OutlinedTextField(
                    value = securityCode,
                    onValueChange = { securityCode = it },
                    label = { Text("Security code") },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    leadingIcon = {
                        Icon(Icons.Default.Lock, null)
                    },
                    shape = RoundedCornerShape(12.dp)
                )
            }
            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = acceptTerms, onCheckedChange = { acceptTerms = it })
                Text("Accept the ", fontSize = 14.sp)
                TextButton(onClick = { /* Terms */ }, contentPadding = PaddingValues(0.dp)) {
                    Text("Term and Conditions", fontSize = 14.sp)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = useDefault, onCheckedChange = { useDefault = it })
                Text("Use as default payment method", fontSize = 14.sp)
            }
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { /* Add card logic */ },
                shape = RoundedCornerShape(9.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1678F2))
            ) {
                Text(
                    "Add card",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun PaymentCardOption(
    selected: Boolean,
    onClick: () -> Unit,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDesc: String
) {
    val outline = if (selected) Color(0xFF1868F2) else Color(0xFFE2E8F0)
    val bg = if (selected) Color(0xFFF8FBFF) else Color.White
    Box(
        Modifier
            .background(bg, RoundedCornerShape(10.dp))
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = outline,
                shape = RoundedCornerShape(10.dp)
            )
            .size(54.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDesc,
            tint = if (selected) Color(0xFF1678F2) else Color(0xFFB5B9C9),
            modifier = Modifier.size(32.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    NTSLIBRARYTheme {
        PaymentScreen(onClose = {})
    }
}

package com.example.ntslibrary.destinations

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ntslibrary.PaymentActivity

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    var contactName by rememberSaveable { mutableStateOf("") }
    var contactEmail by rememberSaveable { mutableStateOf("") }
    var contactMsg by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header
        Spacer(Modifier.height(10.dp))
        Text(
            "Profile",
            modifier = Modifier.fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(Modifier.height(10.dp))
        // User info + edit button
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                Modifier
                    .size(76.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE4ECFF)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Outlined.Person,
                    contentDescription = null,
                    tint = Color(0xFF8CB2F4),
                    modifier = Modifier.size(54.dp)
                )
            }
            Spacer(Modifier.height(8.dp))
            Text("Eleanor Vance", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("eleanor.vance@example.com", color = Color(0xFF8291B3), fontSize = 13.5.sp)
            Spacer(Modifier.height(7.dp))
            Button(
                onClick = {},
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF8FAFF)),
                elevation = ButtonDefaults.buttonElevation(0.dp),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 3.dp)
            ) {
                Icon(
                    Icons.Outlined.Edit,
                    contentDescription = null,
                    tint = Color(0xFF1678F2),
                    modifier = Modifier.size(17.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    "Edit Profile",
                    color = Color(0xFF1678F2),
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp
                )
            }
        }
        Spacer(Modifier.height(22.dp))
        // My Progress: 2 cards
        Text(
            "My Progress",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(9.dp))
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(13.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileStatsCard(
                label = "Coins Earned",
                value = "1,250",
                icon = Icons.Outlined.MonetizationOn,
                modifier = Modifier.weight(1f)
            )
            ProfileStatsCard(
                label = "Papers Uploaded",
                value = "18",
                icon = Icons.Outlined.Description,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(Modifier.height(25.dp))
        // Account and Settings
        Text(
            "Account & Settings",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(8.dp))
        OutlinedCard(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(1.dp, Color(0xFFE2EDFC))
        ) {
            Column(Modifier.padding(vertical = 2.dp)) {
                ProfileSettingsRow(
                    icon = Icons.Outlined.StarOutline,
                    iconColor = Color(0xFF63AAFA),
                    text = "Subscription Plan"
                )
                Divider(modifier = Modifier.padding(start = 56.dp, end = 10.dp))
                ProfileSettingsRow(
                    icon = Icons.Outlined.Payment,
                    iconColor = Color(0xFFB88BF6),
                    text = "Payments",
                    onClick = {
                        context.startActivity(
                            Intent(context, PaymentActivity::class.java)
                        )
                    }
                )
                Divider(modifier = Modifier.padding(start = 56.dp, end = 10.dp))
                ProfileSettingsRow(
                    icon = Icons.Outlined.SupportAgent,
                    iconColor = Color(0xFFF8BC2E),
                    text = "Support"
                )
            }
        }
        Spacer(Modifier.height(25.dp))
        // Contact Us
        Card(
            Modifier.padding(horizontal = 0.dp, vertical = 1.dp),
            shape = RoundedCornerShape(0.dp),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 17.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1.5f)) {
                    Text(
                        "Contact us",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Spacer(Modifier.height(8.dp))
                    ProfileContactField(
                        label = "Name",
                        value = contactName,
                        onValueChange = { contactName = it })
                    ProfileContactField(
                        label = "Email",
                        value = contactEmail,
                        onValueChange = { contactEmail = it })
                    ProfileContactField(
                        label = "Message",
                        value = contactMsg,
                        onValueChange = { contactMsg = it },
                        singleLine = false,
                        minLines = 2
                    )
                    Spacer(Modifier.height(6.dp))
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(30),
                        modifier = Modifier.height(40.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1678F2)),
                        contentPadding = PaddingValues(horizontal = 25.dp, vertical = 3.dp)
                    ) {
                        Text(
                            "Send",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
                Spacer(Modifier.width(16.dp))
                Box(
                    Modifier
                        .weight(1f)
                        .aspectRatio(1.11f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Outlined.Map,
                        contentDescription = null,
                        tint = Color(0xFFA4B9DE),
                        modifier = Modifier.size(52.dp)
                    )
                }
            }
        }
        Spacer(Modifier.height(18.dp))
    }
}

@Composable
fun ProfileStatsCard(
    label: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Unspecified),
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(
            Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = Color(0xFFFFC72C),
                modifier = Modifier.size(23.dp)
            )
            Spacer(Modifier.height(7.dp))
            Text(value, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(
                label,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.5.sp,
            )
        }
    }
}

@Composable
fun ProfileSettingsRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    text: String,
    onClick: (() -> Unit)? = null
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(start = 12.dp, end = 7.dp)
            .let { if (onClick != null) it.clickable { onClick() } else it },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(37.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(iconColor.copy(alpha = 0.11f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(22.dp))
        }
        Spacer(Modifier.width(13.dp))
        Text(
            text,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )
        Icon(
            Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = Color(0xFFB0B3C3),
            modifier = Modifier.size(22.dp)
        )
    }
}

@Composable
fun ProfileContactField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    minLines: Int = 1
) {
    Column(Modifier.padding(bottom = 3.dp)) {
        Text(label,fontSize = 12.5.sp, fontWeight = FontWeight.Medium)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            minLines = minLines,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp)
                .defaultMinSize(minHeight = 37.dp),
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE2EDFC),
                focusedBorderColor = Color(0xFF1678F2),
                cursorColor = Color(0xFF1678F2)
            )
        )
    }
}

package com.example.ntslibrary.destinations

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material.icons.outlined.MonetizationOn
import androidx.compose.material.icons.outlined.RateReview
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TaskCardData(
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val iconColor: Color,
    val title: String,
    val subtitle: String,
    val coins: Int,
    val actionLabel: String,
    val actionColor: Color
)
@Composable
fun TasksScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Primary Section Header
        Spacer(Modifier.height(8.dp))
        Text(
            "TASKS",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(Modifier.height(10.dp))
        // Balance card, as in design
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFFFFBEB)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                Modifier
                    .padding(start = 10.dp, top = 8.dp, bottom = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFFFF2B0))
                    .padding(horizontal = 12.dp, vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Your Balance:",
                    color = Color(0xFF476184),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )
                Spacer(Modifier.width(3.dp))
                Text(
                    "2540",
                    color = Color(0xFF476184),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Spacer(Modifier.width(2.dp))
                Icon(
                    Icons.Outlined.MonetizationOn,
                    contentDescription = null,
                    tint = Color(0xFFFFC72C),
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE9F2FF)),
                shape = RoundedCornerShape(40),
                contentPadding = PaddingValues(horizontal = 15.dp, vertical = 3.dp),
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(
                    "Earn More",
                    color = Color(0xFF1678F2),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
        Spacer(Modifier.height(20.dp))
        // Label: Daily Challenges
        Text(
            "Daily Challenges",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp)
        )
        OutlinedTasksCardList(
            tasks = listOf(
                TaskCardData(
                    icon = Icons.Outlined.CalendarMonth,
                    iconColor = Color(0xFF36A5EA),
                    title = "Daily Login Bonus",
                    subtitle = "Log in daily to claim your reward.",
                    coins = 50,
                    actionLabel = "Claim Reward",
                    actionColor = Color(0xFF1678F2)
                ),
                TaskCardData(
                    icon = Icons.Outlined.Article,
                    iconColor = Color(0xFF34C682),
                    title = "Read a Featured Article",
                    subtitle = "Spend 5 minutes reading any featured article.",
                    coins = 100,
                    actionLabel = "Start Reading",
                    actionColor = Color(0xFF34C682)
                )
            ),
            cardBorderColor = Color(0xFFECF4FF)
        )
        Spacer(Modifier.height(18.dp))
        Text(
            "Contribution Opportunities",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp)
        )
        OutlinedTasksCardList(
            tasks = listOf(
                TaskCardData(
                    icon = Icons.Outlined.CloudUpload,
                    iconColor = Color(0xFF7C5AED),
                    title = "Upload a Research Paper",
                    subtitle = "Contribute to the library and earn big.",
                    coins = 500,
                    actionLabel = "Upload Now",
                    actionColor = Color(0xFF7C5AED)
                ),
                TaskCardData(
                    icon = Icons.Outlined.RateReview,
                    iconColor = Color(0xFFFFC72C),
                    title = "Write a Book Review",
                    subtitle = "Share your insights on a recently read book.",
                    coins = 150,
                    actionLabel = "Write Review",
                    actionColor = Color(0xFFFFC72C)
                )
            ),
            cardBorderColor = Color(0xFFFFEFA0)
        )
        Spacer(Modifier.height(22.dp))
        // Progress Label
        Text(
            "Your Weekly Progress",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
        )
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(1.dp)
        ) {
            Column(Modifier.padding(horizontal = 14.dp, vertical = 12.dp)) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Weekly Task Completion",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF476184),
                        fontSize = 15.sp
                    )
                    Text(
                        "75%",
                        color = Color(0xFF1678F2),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
                Spacer(Modifier.height(6.dp))
                LinearProgressIndicator(
                    progress = 0.75f,
                    color = Color(0xFF1678F2),
                    trackColor = Color(0xFFECF4FF),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(20))
                )
                Spacer(Modifier.height(5.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("3/4 Tasks Completed", fontSize = 12.sp, color = Color.Gray)
                    Text("Target: 4 Tasks", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
        Spacer(Modifier.height(18.dp))
    }
}


@Composable
fun OutlinedTasksCardList(tasks: List<TaskCardData>, cardBorderColor: Color) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        tasks.forEach { task ->
            OutlinedCard(
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(1.dp, cardBorderColor)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 11.dp, horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Icon background
                    Box(
                        Modifier
                            .size(42.dp)
                            .clip(RoundedCornerShape(11.dp))
                            .background(task.iconColor.copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            task.icon,
                            contentDescription = null,
                            tint = task.iconColor,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(
                            task.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.5.sp,
                        )
                        Text(task.subtitle, fontSize = 11.5.sp)
                        Spacer(Modifier.height(3.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Outlined.MonetizationOn,
                                contentDescription = null,
                                tint = Color(0xFFFFC72C),
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                "${task.coins}Coins",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 11.sp,
                                color = Color(0xFF1678F2),
                                modifier = Modifier.padding(start = 3.dp)
                            )
                        }
                    }
                    Spacer(Modifier.width(7.dp))
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = task.actionColor),
                        shape = RoundedCornerShape(32.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
                        modifier = Modifier.height(33.dp)
                    ) {
                        Text(
                            task.actionLabel,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.5.sp
                        )
                    }
                }
            }
        }
    }
}
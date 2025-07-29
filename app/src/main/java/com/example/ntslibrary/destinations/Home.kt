package com.example.ntslibrary.destinations

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.outlined.Campaign
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ntslibrary.Books
import com.example.ntslibrary.TopBar

data class BookRec(val title: String, val author: String)

@Composable
fun HomePageBody(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 8.dp)
    ) {
        TopBar()
        Spacer(Modifier.height(8.dp))
        WelcomeCard()
        Spacer(Modifier.height(12.dp))
        QuickAccessSection()
        Spacer(Modifier.height(16.dp))
        AcademicProgressSection()
        Spacer(Modifier.height(16.dp))
        RecommendedSection()
        Spacer(Modifier.height(16.dp))
        UpdatesSection()
    }
}
@Composable
fun WelcomeCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("Welcome back, Jane!", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(
                "Your knowledge journey awaits.",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 2.dp, bottom = 10.dp)
            )
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1678F2)),
                shape = RoundedCornerShape(40.dp),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 2.dp)
            ) {
                Icon(
                    Icons.Filled.MonetizationOn,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(5.dp))
                Text("2500 Coins", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}


@Composable
fun QuickAccessSection() {
    val context = LocalContext.current
    Column(
        Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 4.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            "Quick Access",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            QuickActionButton(
                Icons.Outlined.MenuBook,
                "Browse Books" ,
                "Explore new reads",
                Modifier.weight(1f)
            ) {
                val intent = Intent(context, Books::class.java)
                context.startActivity(intent)
            }
            QuickActionButton(
                Icons.Outlined.EmojiEvents, "Earn Coins", "Complete tasks", Modifier.weight(1f)
            )
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            QuickActionButton(
                Icons.Outlined.CloudUpload,
                "Upload Paper",
                "Share your research",
                Modifier.weight(1f)
            )
            QuickActionButton(
                Icons.Outlined.Leaderboard, "My Progress", "View achievements", Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun AcademicProgressSection() {
    Column(Modifier.padding(horizontal = 16.dp)) {
        Text(
            "Academic Progress",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Row(
            Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column(Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Text("Chapter Completion", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Spacer(Modifier.height(6.dp))
                    LinearProgressIndicator(
                        progress = 0.72f,
                        color = Color(0xFF1678F2),
                        trackColor = Color(0xFFBBDEFB),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp)
                    )
                    Spacer(Modifier.height(10.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("72%", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = Color(0xFF1678F2))
                        Text("28% Remaining", fontSize = 11.sp, color = Color.Gray)
                    }
                }
            }
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Engagement Score", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Spacer(Modifier.height(10.dp))
                    Box(
                        Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFFDE7)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("8.5", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFFFFC72C))
                    }
                    Spacer(Modifier.height(4.dp))
                    Text("Your weekly average", fontSize = 11.sp, color = Color.Gray)
                }
            }
        }
    }
}


@Composable
fun RecommendedSection() {
    Column(Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
        Text(
            "Recommended for You",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        val books = listOf(
            BookRec("The Art of Data Science", "Dr. Arya Sharma"),
            BookRec("Quantum Computing", "Prof. Alex Chan"),
            BookRec("AI in Education", "Dr. Brooke Lee")
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(end = 8.dp, top = 4.dp, bottom = 8.dp)
        ) {
            items(books) { book ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .width(150.dp)
                        .height(180.dp)
                        .border(
                            shape = RoundedCornerShape(12.dp),
                            width = 1.dp,
                            color = Color.Gray
                        ),
                    colors = CardDefaults.cardColors(Color.Transparent)
                ) {
                    Column {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(90.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Outlined.MenuBook,
                                contentDescription = null,
                                tint = Color(0xFF8589A7),
                                modifier = Modifier.size(36.dp)
                            )
                        }
                        Column(Modifier.padding(horizontal = 10.dp, vertical = 12.dp)) {
                            Text(
                                book.title,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                maxLines = 2
                            )
                            Text(book.author, fontSize = 12.sp, color = Color.Gray, maxLines = 1)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UpdatesSection() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE9F2FF)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(Modifier.padding(13.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Outlined.Campaign,
                contentDescription = null,
                tint = Color(0xFF1678F2),
                modifier = Modifier.size(28.dp)
            )
            Spacer(Modifier.width(10.dp))
            Column {
                Text(
                    "New Research Papers Added Weekly!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = Color(0xFF1678F2)
                )
                Text(
                    "Explore thousands of new articles and journals. Stay ahead with the latest academic insights.",
                    fontSize = 11.sp,
                    color = Color(0xFF476184)
                )
            }
        }
    }
}

@Composable
fun QuickActionButton(
    icon: ImageVector,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .height(100.dp)
            .let { if (onClick != null) it.clickable { onClick() } else it }
            .border(
                shape = RoundedCornerShape(20.dp),
                color = Color.Gray,
                width = 1.dp
            ),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                icon,
                contentDescription = title,
                tint = Color(0xFFFFC72C),
                modifier = Modifier.size(27.dp)
            )
            Text(title, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            Text(subtitle, fontSize = 11.sp, color = Color.Gray)
        }
    }
}
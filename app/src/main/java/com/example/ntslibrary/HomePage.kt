package com.example.ntslibrary

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ntslibrary.ui.theme.NTSLIBRARYTheme
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalContext
import kotlin.jvm.java

class HomePage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NTSLIBRARYTheme {
                MainScreenWithNavBar()
            }
        }
    }
}

@Composable
fun MainScreenWithNavBar() {
    val context = LocalContext.current
    val activity = context as? Activity
    val initialTab = (activity?.intent?.getIntExtra("selectedTab", 0)) ?: 0
    var selectedItem by rememberSaveable { mutableStateOf(initialTab) }
    val navItems = listOf(
        NavBarItem("Home", Icons.Outlined.Home),
        NavBarItem("Tasks", Icons.Outlined.Checklist),
        NavBarItem("Upload", Icons.Outlined.CloudUpload),
        NavBarItem("Profile", Icons.Outlined.Person),
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { idx, item ->
                    NavigationBarItem(
                        selected = selectedItem == idx,
                        onClick = { selectedItem = idx },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        when (selectedItem) {
            0 -> HomePageBody(Modifier.padding(innerPadding))
            1 -> TasksScreen(Modifier.padding(innerPadding))
            2 -> UploadScreen(Modifier.padding(innerPadding))
            3 -> ProfileScreen(Modifier.padding(innerPadding))
        }
    }
}

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
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
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
            color = Color(0xFF476184),
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
            color = Color(0xFF476184),
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
            color = Color(0xFF476184),
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
                border = BorderStroke(1.2.dp, cardBorderColor)
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
                            color = Color(0xFF232B49)
                        )
                        Text(task.subtitle, fontSize = 11.5.sp, color = Color(0xFF7883A6))
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
fun UploadScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    var title by rememberSaveable { mutableStateOf("") }
    var authors by rememberSaveable { mutableStateOf("") }
    var abstractText by rememberSaveable { mutableStateOf("") }
    var keywords by rememberSaveable { mutableStateOf("") }
    var subject by rememberSaveable { mutableStateOf("") }
    // Simulate progress: use any number for now
    val progress = 0.75f

    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Header
        Spacer(Modifier.height(10.dp))
        Text(
            "Upload Paper",
            modifier = Modifier.fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(Modifier.height(12.dp))
        // Submission Guidelines
        OutlinedCard(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.2.dp, Color(0xFFE2EDFC))
        ) {
            Column(Modifier.padding(15.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Outlined.FileUpload,
                        contentDescription = null,
                        tint = Color(0xFFFFC72C),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Submission Guidelines",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF232B49),
                        fontSize = 15.sp
                    )
                }
                Spacer(Modifier.height(10.dp))
                UploadPaperGuideline("Papers must be original work and not previously published. Ensure proper citation for any referenced material.")
                Spacer(Modifier.height(3.dp))
                UploadPaperGuideline("Format your paper according to APA 7th edition. Submissions must be in PDF format, up to 20MB.")
                Spacer(Modifier.height(3.dp))
                UploadPaperGuideline("All submissions undergo a plagiarism check and peer review process to maintain academic integrity.")
            }
        }
        Spacer(Modifier.height(16.dp))
        // Drag & Drop or Browse Files
        OutlinedCard(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.2.dp, Color(0xFFE2EDFC))
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 17.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Outlined.CloudUpload,
                    contentDescription = null,
                    tint = Color(0xFF1678F2),
                    modifier = Modifier.size(36.dp)
                )
                Spacer(Modifier.height(2.dp))
                Text("Drag & drop your paper here, or", color = Color(0xFF232B49), fontSize = 14.sp)
                Spacer(Modifier.height(4.dp))
                Button(
                    onClick = { /* Dummy action */ },
                    shape = RoundedCornerShape(32.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFECF4FF)),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 2.dp)
                ) {
                    Text(
                        "Browse Files",
                        color = Color(0xFF1678F2),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
        Spacer(Modifier.height(18.dp))
        // Paper Details header
        Text(
            "Paper Details",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color(0xFF232B49),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(11.dp))
        // Form fields
        LabeledTextField(
            "Paper Title",
            value = title,
            onValueChange = { title = it },
            placeholder = "Enter the full title of your research paper"
        )
        LabeledTextField(
            "Authors",
            value = authors,
            onValueChange = { authors = it },
            placeholder = "Comma-separated list of authors (e.g., John Doe, Jane Smith)"
        )
        LabeledTextField(
            "Abstract",
            value = abstractText,
            onValueChange = { abstractText = it },
            placeholder = "Provide a concise summary of your paper (max 300 words)",
            singleLine = false,
            minLines = 3
        )
        LabeledTextField(
            "Keywords",
            value = keywords,
            onValueChange = { keywords = it },
            placeholder = "Keywords to describe your paper (e.g., AI, Education, Data Science)"
        )
        LabeledTextField(
            "Subject Area",
            value = subject,
            onValueChange = { subject = it },
            placeholder = "Specify the primary subject area (e.g., Computer Science)"
        )
        Spacer(Modifier.height(14.dp))
        // Upload progress
        OutlinedCard(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(11.dp),
            border = BorderStroke(1.2.dp, Color(0xFFE2EDFC))
        ) {
            Column(Modifier.padding(horizontal = 12.dp, vertical = 13.dp)) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        "Upload Progress",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF476184),
                        fontSize = 14.sp
                    )
                    Text(
                        "${(progress * 100).toInt()}%",
                        color = Color(0xFF1678F2),
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(7.dp))
                LinearProgressIndicator(
                    progress = progress,
                    color = Color(0xFF1678F2),
                    trackColor = Color(0xFFE9F2FF),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "Uploading file...",
                    fontSize = 12.sp,
                    color = Color(0xFF476184),
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
        Spacer(Modifier.height(22.dp))
        // Submit Paper Button
        Button(
            onClick = { /* Submit action */ },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1678F2)),
            contentPadding = PaddingValues(vertical = 4.dp)
        ) {
            Text(
                "Submit Paper",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(Modifier.width(7.dp))
            Icon(Icons.Outlined.Send, contentDescription = null, tint = Color.White)
        }
        Spacer(Modifier.height(22.dp))
    }
}

@Composable
fun UploadPaperGuideline(text: String) {
    Row(Modifier.padding(vertical = 2.dp)) {
        Icon(
            Icons.Outlined.WarningAmber,
            contentDescription = null,
            tint = Color(0xFFFFC72C),
            modifier = Modifier.size(18.dp)
        )
        Spacer(Modifier.width(6.dp))
        Text(text, color = Color(0xFF928B44), fontSize = 13.sp)
    }
}

@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    singleLine: Boolean = true,
    minLines: Int = 1
) {
    Column(Modifier.padding(horizontal = 16.dp, vertical = 5.dp)) {
        Text(label, color = Color(0xFF7883A6), fontSize = 13.sp, fontWeight = FontWeight.Medium)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color(0xFFBDC8DE), fontSize = 14.sp) },
            singleLine = singleLine,
            minLines = minLines,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp)
                .defaultMinSize(minHeight = 45.dp),
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE2EDFC),
                focusedBorderColor = Color(0xFF1678F2),
                cursorColor = Color(0xFF1678F2)
            )
        )
    }
}

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
            color = Color(0xFF232B49),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(9.dp))
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(13.dp)
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
            color = Color(0xFF232B49),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(8.dp))
        OutlinedCard(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(1.2.dp, Color(0xFFE2EDFC))
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
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE5F0FF)),
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
                        fontSize = 15.sp,
                        color = Color(0xFF232B49)
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
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier,
        shape = RoundedCornerShape(13.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(
            Modifier.padding(vertical = 16.dp, horizontal = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFFFF7D9)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = Color(0xFFFFC72C),
                    modifier = Modifier.size(23.dp)
                )
            }
            Spacer(Modifier.height(7.dp))
            Text(value, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF232B49))
            Text(
                label,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.5.sp,
                color = Color(0xFFB0B3C3)
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
            color = Color(0xFF232B49),
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
        Text(label, color = Color(0xFF7883A6), fontSize = 12.5.sp, fontWeight = FontWeight.Medium)
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

@Composable
fun TopBar() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "NTS Library",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(Color(0xFFF0F0F0)),
            contentAlignment = Alignment.Center
        ) {
            Text("JD", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun WelcomeCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F8FE)),
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
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(5.dp))
                Text("2500 Coins", color = Color.White, fontWeight = FontWeight.SemiBold)
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
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            QuickActionButton(
                Icons.Outlined.MenuBook,
                "Browse Books",
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
fun QuickActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F8FE)),
        modifier = modifier
            .height(100.dp)
            .let { if (onClick != null) it.clickable { onClick() } else it }
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
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F8FE)),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column(Modifier.padding(12.dp)) {
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
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F8FE)),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column(
                    Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
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
                        .width(180.dp)
                        .height(180.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .background(Color(0xFFDEE3F7)), // Placeholder for image
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

data class BookRec(val title: String, val author: String)

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

data class NavBarItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    NTSLIBRARYTheme {
        MainScreenWithNavBar()
    }
}

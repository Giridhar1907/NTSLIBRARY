package com.example.ntslibrary

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ntslibrary.ui.theme.NTSLIBRARYTheme
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.platform.LocalContext
import com.example.ntslibrary.destinations.HomePageBody
import com.example.ntslibrary.destinations.ProfileScreen
import com.example.ntslibrary.destinations.TasksScreen
import com.example.ntslibrary.destinations.UploadScreen
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
    var selectedItem by rememberSaveable { mutableIntStateOf(initialTab) }
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
fun TopBar() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
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

data class NavBarItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    NTSLIBRARYTheme {
        MainScreenWithNavBar()
    }
}

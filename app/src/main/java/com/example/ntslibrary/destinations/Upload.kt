package com.example.ntslibrary.destinations

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material.icons.outlined.FileUpload
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            textAlign = TextAlign.Center,
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
            border = BorderStroke(0.dp, Color(0xFFE2EDFC))
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
                .fillMaxWidth()
                .border(
                    shape = RoundedCornerShape(12.dp),
                    width = 1.dp,
                    color = Color.Gray
                ),
            shape = RoundedCornerShape(12.dp)
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
                Text("Drag & drop your paper here, or")
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
                .fillMaxWidth()
                .border(
                    shape = RoundedCornerShape(20.dp),
                    width = 0.dp,
                    color = Color.Gray
                ),
            shape = RoundedCornerShape(20.dp)
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
        Text(label, fontSize = 13.sp, fontWeight = FontWeight.Medium)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color.Gray, fontSize = 14.sp) },
            singleLine = singleLine,
            minLines = minLines,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp)
                .defaultMinSize(minHeight = 45.dp),
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1678F2),
                cursorColor = Color(0xFF1678F2)
            )
        )
    }
}
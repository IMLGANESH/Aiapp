package com.aiapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

// Apple-inspired color palette
val CupertinoBackground = Color(0xFFF2F2F7)
val CupertinoSurface = Color(0xFFFFFFFF)
val CupertinoBlue = Color(0xFF007AFF)

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CupertinoBackground)
            .padding(16.dp)
    ) {
        Text(
            text = "Settings", 
            style = MaterialTheme.typography.headlineLarge, 
            fontFamily = FontFamily.SansSerif
        )
        Spacer(modifier = Modifier.height(24.dp))
        
        CupertinoSection(title = "LOCAL MEMORY") {
            CupertinoRow(label = "Enable Vector DB", isToggle = true)
            CupertinoRow(label = "Clear Device Memory")
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        CupertinoSection(title = "API CONFIGURATION (15/20 Active)") {
            CupertinoRow(label = "Manage API Keys")
            CupertinoRow(label = "Auto-Rotate Free Tiers", isToggle = true)
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        CupertinoSection(title = "SYSTEM INTEGRATION") {
            CupertinoRow(label = "Enable Screen Context (Accessibility)", isToggle = true)
            CupertinoRow(label = "Draw Over Other Apps")
        }
    }
}

@Composable
fun CupertinoSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Text(title, color = Color.Gray, modifier = Modifier.padding(start = 16.dp, bottom = 8.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(CupertinoSurface)
    ) {
        content()
    }
}

@Composable
fun CupertinoRow(label: String, isToggle: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontFamily = FontFamily.SansSerif)
        if (isToggle) {
            Switch(
                checked = true, 
                onCheckedChange = {}, 
                colors = SwitchDefaults.colors(checkedThumbColor = CupertinoBlue)
            )
        }
    }
}

package com.example.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layouts.ui.theme.LayoutsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutsCodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    PhotographerCard()
                }
            }
        }
    }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    Row(modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clip(RoundedCornerShape(4.dp))
        .background(MaterialTheme.colors.surface)
        .clickable(onClick = { /* Ignoring onClick */ })
        .padding(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(48.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {

        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", modifier = Modifier.align(CenterHorizontally),  style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Preview
@Composable
fun PhotographerCardPreview() {
    LayoutsCodelabTheme {
        PhotographerCard()
    }
}
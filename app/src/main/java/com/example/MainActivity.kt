package com.example

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MyApp { MainContent() } }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp { MainContent() }
}

@Composable
fun MainContent(names: List<String> = listOf("Android", "ios")) {
    val counterState = rememberSaveable { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxHeight()) {
        Names(
            modifier = Modifier.weight(1f),
            names = List(1000) { "Hello Android $it" },
            onClick = { counterState.value = counterState.value + 1 }
        )
        Counter(
            count = counterState.value,
            onClick = { counterState.value = counterState.value + 1 }
        )
    }
}

@Composable
fun Names(names: List<String>, onClick: () -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(name = name, onClick = onClick)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun Greeting(name: String, onClick: () -> Unit) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Color.Red else Color.Transparent,
        animationSpec = tween(1_000)
    )
    Text(
        text = "Hello $name!",
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable(onClick = { isSelected = !isSelected })
    )
}

@Composable
fun Counter(count: Int, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        modifier = modifier.clickable { onClick() },
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.White
        )
    ) {
        Text("I've been clicked $count times.")
    }
}
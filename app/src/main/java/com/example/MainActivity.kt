package com.example

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp { MainContent() }
}

@Composable
fun MainContent(names: List<String> = listOf("Android", "ios")) {
    val counterState = rememberSaveable { mutableStateOf(0) }
    Column {
        names.forEach {
            Greeting(name = it)
            Divider(color = Color.Black)
        }
        Counter(
            count = counterState.value,
            onClick = {
                counterState.value += 1
            }
        )
    }
}

@Composable
fun Counter(count: Int, onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        Text("I've been clicked $count times.")
    }
}
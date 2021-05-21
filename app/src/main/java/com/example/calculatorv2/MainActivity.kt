package com.example.calculatorv2

import kotlin.text.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import com.example.calculatorv2.ui.theme.CalculatorV2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun TextInput() {
    val num = remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = num.value,
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

fun Equation(equation: String): String {
    val arr = equation.split("(?<=[*+/\\-])|(?=[*+/\\-])".toRegex())

    val num1: Double = arr[0].toDouble()
    val num2: Double = arr[2].toDouble()
    var answer = 0.0
    when {
        arr[1].equals("+") -> {
            answer = num1 + num2
        }
        arr[1].equals("-") -> {
            answer = num1 - num2
        }
        arr[1].equals("/") -> {
            answer = num1 / num2
        }
        arr[1].equals("*") -> {
            answer = num1 * num2
        }
    }
    return answer.toString().replace(".0", "")
}

@Composable
fun MyScreenContent() {
    var count by rememberSaveable { mutableStateOf("") }
    val text = listOf(count)

    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        NameList(names = text)
        TextInput()
        Counter(count = count,onCountChange = { count = it })
    }
}

@Composable
fun Counter(count: String, onCountChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(top = 180.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { onCountChange(count + "7") },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
            ) {
                Text("7", style = MaterialTheme.typography.h5)
            }
            Button(onClick = { onCountChange(count + "8") },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
            ) {
                Text("8", style = MaterialTheme.typography.h5)
            }
            Button(onClick = { onCountChange(count + "9") },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
            ) {
                Text("9", style = MaterialTheme.typography.h5)
            }
            Button(onClick = { onCountChange("$count/") },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
            ) {
                Text("/", style = MaterialTheme.typography.h5)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { onCountChange(count + "4") },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
            ) {
                Text("4", style = MaterialTheme.typography.h5)
            }
            Button(onClick = { onCountChange(count + "5") },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
            ) {
                Text("5", style = MaterialTheme.typography.h5)
            }
            Button(onClick = { onCountChange(count + "6") },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
            ) {
                Text("6", style = MaterialTheme.typography.h5)
            }
            Button(onClick = { onCountChange("$count*") },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
            ) {
                Text("x", style = MaterialTheme.typography.h5)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { onCountChange(count + "1") },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
            ) {
                Text("1", style = MaterialTheme.typography.h5)
            }
            Button(onClick = { onCountChange(count + "2") },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
            ) {
                Text("2", style = MaterialTheme.typography.h5)
            }
            Button(onClick = { onCountChange(count + "3") },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
            ) {
                Text("3", style = MaterialTheme.typography.h5)
            }
            Button(onClick = { onCountChange("$count-") },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
            ) {
                Text("-", style = MaterialTheme.typography.h5)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { onCountChange(count + "0") },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
            ) {
                Text("0", style = MaterialTheme.typography.h5)
            }
            Button(onClick = { onCountChange("0") },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
            ) {
                Text("AC", style = MaterialTheme.typography.h5)
            }
            Button(onClick = { onCountChange("$count+") },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
            ) {
                Text("+", style = MaterialTheme.typography.h5)
            }
            Button(onClick = { onCountChange(Equation(count)) },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(10.dp),
            ) {
                Text("=", style = MaterialTheme.typography.h5)
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    CalculatorV2Theme {
        Surface(color = Color.White) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = name,
        modifier = Modifier
            .padding(15.dp)
            .background(color = Color.Transparent),
        style = MaterialTheme.typography.h3,
    )
}

@Preview("Preview test")
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}
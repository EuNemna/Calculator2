package com.example.calculatorv2

import kotlin.text.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import com.example.calculatorv2.ui.theme.CalculatorV2Theme
import com.example.calculatorv2.ui.theme.Purple200
import com.example.calculatorv2.ui.theme.Purple500
import com.example.calculatorv2.ui.theme.Purple700

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
fun Greeting(name: String) {
    Text(text = name,
        modifier = Modifier
            .padding(15.dp)
            .background(color = Color.Transparent),
        style = MaterialTheme.typography.h2,
        color = Color.White
    )
}

fun equation(equation: String): String {
    val replacedValue = equation.replace("×", "*").replace("÷", "/")
    val arr = replacedValue.split("(?<=[*+/\\-])|(?=[*+/\\-])".toRegex())

    var num1: Double = 0.0
    var num2: Double = 0.0
    var answer = 0.0

    if (arr.size >= 3) {
        if (arr[1] == "-" && arr[0] == "") {
            num1 = -arr[2].toDouble()
            num2 = arr[4].toDouble()

            when {
                arr[3] == "+" -> {
                    answer = num1 + num2
                }
                arr[3] == "-" -> {
                    answer = num1 - num2
                }
                arr[3] == "/" -> {
                    answer = num1 / num2
                }
                arr[3] == "*" -> {
                    answer = num1 * num2
                }
            }
        } else {
            num1 = arr[0].toDouble()
            num2 = arr[2].toDouble()

            when {
                arr[1] == "+" -> {
                    answer = num1 + num2
                }
                arr[1] == "-" -> {
                    answer = num1 - num2
                }
                arr[1] == "/" -> {
                    answer = num1 / num2
                }
                arr[1] == "*" -> {
                    answer = num1 * num2
                }
            }
        }
        return answer.toString().replace(".0", "")
    } else {
        return equation
    }
}

fun delete(equation: String):String {
    if (equation != "") {
        return equation.dropLast(1)
    }

    return equation
}

fun checkDuplicate(operator: String): String {
    if (operator[operator.lastIndex - 1] == operator[operator.lastIndex]) {
        return operator.dropLast(1)
    }

    return operator
}

fun checkIfNegative(equation: String): String {
    if (equation[0] == '-') {
        return equation.removeRange(0, 1)
    } else {
        return "-$equation"
    }
}

@Composable
fun MyScreenContent() {
    var count by rememberSaveable { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Greeting(count)
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
            .padding(top = 130.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { onCountChange("") },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("AC", style = MaterialTheme.typography.h5, color = Color.White)
            }
            Button(onClick = { onCountChange(delete(count)) },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("⌫", style = MaterialTheme.typography.h5, color = Color.White)
            }
            Button(onClick = { onCountChange(checkIfNegative(count)) },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("±", style = MaterialTheme.typography.h5, color = Color.White)
            }
            Button(onClick = { onCountChange(checkDuplicate("$count÷")) },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("÷", style = MaterialTheme.typography.h5, color = Color.White)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { onCountChange(count + "7") },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("7", style = MaterialTheme.typography.h5, color = Color.White)
            }
            Button(onClick = { onCountChange(count + "8") },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("8", style = MaterialTheme.typography.h5, color = Color.White)
            }
            Button(onClick = { onCountChange(count + "9") },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("9", style = MaterialTheme.typography.h5, color = Color.White)
            }
            Button(onClick = { onCountChange(checkDuplicate("$count×")) },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("×", style = MaterialTheme.typography.h5, color = Color.White)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { onCountChange(count + "4") },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("4", style = MaterialTheme.typography.h5, color = Color.White)
            }
            Button(onClick = { onCountChange(count + "5") },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("5", style = MaterialTheme.typography.h5, color = Color.White)
            }
            Button(onClick = { onCountChange(count + "6") },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("6", style = MaterialTheme.typography.h5, color = Color.White)
            }
            Button(onClick = { onCountChange(checkDuplicate("$count-")) },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("-", style = MaterialTheme.typography.h5, color = Color.White)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { onCountChange(count + "1") },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("1", style = MaterialTheme.typography.h5, color = Color.White)
            }
            Button(onClick = { onCountChange(count + "2") },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("2", style = MaterialTheme.typography.h5, color = Color.White)
            }
            Button(onClick = { onCountChange(count + "3") },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("3", style = MaterialTheme.typography.h5, color = Color.White)
            }
            Button(onClick = { onCountChange(checkDuplicate("$count+")) },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("+", style = MaterialTheme.typography.h5, color = Color.White)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { onCountChange("0.$count") },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("%", style = MaterialTheme.typography.h5, color = Color.White)
            }
            Button(onClick = { onCountChange(count + "0") },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text("0", style = MaterialTheme.typography.h5, color = Color.White)
            }
            Button(onClick = { onCountChange(checkDuplicate("$count.")) },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp)
                ,shape = CircleShape
            ) {
                Text(".", style = MaterialTheme.typography.h5, color = Color.White)
            }
            Button(onClick = { onCountChange(equation(count)) },
                colors = ButtonDefaults.buttonColors(Purple500),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 10.dp, bottom = 10.dp)
                ,shape = CircleShape
            ) {
                Text("=", style = MaterialTheme.typography.h5, color = Color.White)
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    CalculatorV2Theme {
        Surface(color = Color.Black) {
            content()
        }
    }
}

@Preview("Preview test")
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}
package com.example.allcomponentvalidationjetpack.android

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

class MainActivity : ComponentActivity() {

    object Gender {
        const val male = "Male"
        const val female = "Female"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            callPreview()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun callPreview() {
    loginForm()
}

@Composable
fun CheckboxForm() {

}


// Login Function with TextField Validation
@Composable
fun loginForm() {

    val context = LocalContext.current


    // Dropdown menu option
    var expanded by remember { mutableStateOf(false) }
    val list = listOf("Chennai","Mumbai","Madurai","Bangalore")
    var selectedItem by  remember {
        mutableStateOf("")
    }

    var textFiledSize by remember {
        mutableStateOf(Size.Zero)
    }

    val icon = if (expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var username = remember { mutableStateOf("") }
        var password = remember { mutableStateOf("") }

        val checkedStateJava = remember { mutableStateOf(false) }

        val checkedStateKotlin = remember { mutableStateOf(false) }

        val checkedState = remember { mutableStateOf(false) }

        //Radio button option

        val selectedGender = remember {
            mutableStateOf("")
        }


        Text(text = "Login Form", fontWeight = FontWeight.Bold, fontSize = 35.sp)
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text(text = "Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = "Password") },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(20.dp))

        Row() {

            Checkbox(
                checked = checkedStateJava.value,
                onCheckedChange = {
                    checkedStateJava.value = it
                    checkedStateKotlin.value = false

                },
                modifier = Modifier.padding(5.dp),
                colors = CheckboxDefaults.colors(Color.Red)
            )

            Text(
                text = "Java",
                modifier = Modifier
                    .padding(5.dp),
                fontSize = 18.sp
            )




            Checkbox(
                checked = checkedStateKotlin.value,
                onCheckedChange = {
                    checkedStateKotlin.value = it
                    checkedStateJava.value = false
                },
                modifier = Modifier.padding(5.dp),
                colors = CheckboxDefaults.colors(Color.Red),
            )

            Text(text = "Kotlin", modifier = Modifier.padding(5.dp), fontSize = 18.sp)


        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Single",
                modifier = Modifier
                    .padding(5.dp),
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.width(30.dp))

            Switch(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                colors = SwitchDefaults.colors(Color.Green)
            )

            Spacer(modifier = Modifier.width(50.dp))

            Text(
                text = "Married",
                modifier = Modifier
                    .padding(5.dp),
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            RadioButton(
                selected = selectedGender.value == MainActivity.Gender.male,
                onClick = { selectedGender.value = MainActivity.Gender.male },
                colors = RadioButtonDefaults.colors(Color.Green))

            Spacer(modifier = Modifier.size(16.dp))
            Text(MainActivity.Gender.male)

            Spacer(modifier = Modifier.size(16.dp))

            RadioButton(
                selected = selectedGender.value == MainActivity.Gender.female,
                onClick = { selectedGender.value = MainActivity.Gender.female },
                colors = RadioButtonDefaults.colors(Color.Green))

            Spacer(modifier = Modifier.size(16.dp))
            Text(MainActivity.Gender.female)
        }

        Spacer(modifier = Modifier.height(20.dp))


        // Dropdown menu option

        OutlinedTextField(
            value = selectedItem,
            onValueChange = { selectedItem = it},
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFiledSize = coordinates.size.toSize()
                },
            label = { Text(text = "Select Location")},
            trailingIcon = {
                Icon(icon,"",Modifier.clickable { expanded = !expanded })
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textFiledSize.width.toDp()}))
        {
            list.forEach { label ->

                DropdownMenuItem(
                    text = { Text(text = label) },
                    onClick = { selectedItem = label
                    expanded = false })

            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (username.value.isEmpty()) {
                    Toast.makeText(context, "Please Enter Username", Toast.LENGTH_SHORT).show()
                } else if (password.value.isEmpty()) {
                    Toast.makeText(context, "Please Enter Password", Toast.LENGTH_SHORT).show()
                } else if (!checkedStateJava.value && !checkedStateKotlin.value) {

                    Toast.makeText(context, "Please select the course", Toast.LENGTH_SHORT)
                        .show()

                } else if (!checkedState.value) {
                    Toast.makeText(context, "Please select the your marital status", Toast.LENGTH_SHORT)
                        .show()
                } else if(selectedGender.value.isEmpty()){
                    Toast.makeText(context, "Please select your gender", Toast.LENGTH_SHORT)
                        .show()
                } else if(selectedItem.isEmpty()){
                    Toast.makeText(context, "Please select your location", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(context, "Login Successfully Completed", Toast.LENGTH_SHORT)
                        .show()

                }
            },
            modifier = Modifier.fillMaxWidth()

        ) {
            Text(text = "Login", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }


    }
}



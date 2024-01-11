package com.example.inventorygudang.ui.halaman

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.inventorygudang.R
import java.util.Calendar



@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePicker(
    onDateSelected: (String) -> Unit,
    defaultDate: String = "01/01/2022", // Add a default date
    modifier: Modifier
) {
    val context = LocalContext.current

    val calendar = remember { Calendar.getInstance() }
    val date = remember { mutableStateOf(defaultDate) } // Use the default date

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, day ->
                date.value = "$day/${month + 1}/$year"
                onDateSelected(date.value)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedTextField(
            value = date.value,
            onValueChange = {},
            label = { Text("Tanggal") },
            singleLine = true,
            modifier = Modifier.weight(1f),
            enabled = false, // Disabled to make it look like static text
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                disabledTextColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                disabledBorderColor = Color.Black
            ),
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        OutlinedButton(
            onClick = { datePickerDialog.show() },
            modifier = Modifier.size(48.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.date),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp).background(Color.Black)
                )
                Text("Select Date")
            }
        }
    }
}
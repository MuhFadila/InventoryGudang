package com.example.inventorygudang.ui.halaman

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.inventorygudang.R
import com.example.inventorygudang.model.DetailBarang
import com.example.inventorygudang.model.UIStateBarang
import java.util.Calendar


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EntryBarangBody(
    uiStateBarang: UIStateBarang,
    onBarangValueChange: (DetailBarang) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        FormInputBarang(
            detailBarang = uiStateBarang.detailBarang,
            onValueChange = onBarangValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = uiStateBarang.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.btn_submit))
        }
    }
}




@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputBarang(
    detailBarang: DetailBarang,
    modifier: Modifier = Modifier,
    onValueChange: (DetailBarang) -> Unit = {},
    enabled: Boolean = true
){

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        DatePicker(
            onDateSelected = { selectedDate ->
                onValueChange(detailBarang.copy(tanggal = selectedDate))
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = detailBarang.nama,
            onValueChange = { onValueChange(detailBarang.copy(nama = it)) },
            label = { Text(stringResource(R.string.nama)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailBarang.kode,
            onValueChange = { onValueChange(detailBarang.copy(kode = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(stringResource(R.string.kode)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailBarang.jumlah,
            onValueChange = { onValueChange(detailBarang.copy(jumlah = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(text = stringResource(R.string.jumlah)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailBarang.harga,
            onValueChange = { onValueChange(detailBarang.copy(harga = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(text = stringResource(R.string.harga)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        if (enabled) {
            Text(
                text = stringResource(R.string.required_field),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}


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
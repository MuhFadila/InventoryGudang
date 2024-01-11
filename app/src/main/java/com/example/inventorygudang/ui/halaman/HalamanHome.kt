package com.example.inventorygudang.ui.halaman

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.inventorygudang.R
import com.example.inventorygudang.data.Barang



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyHome(
    itemBarang: List<Barang>,
    modifier: Modifier = Modifier,
    onBarangClick: (Int) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        TextField(
            value = searchQuery,
            onValueChange = {newQuery ->
                searchQuery = newQuery
            },
            placeholder = {
                Text(text = stringResource(R.string.search))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_small))
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))

        if (itemBarang.isEmpty()) {
            Text(
                text = stringResource(R.string.deskripsi_no_item),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListBarang(
                itemBarang = itemBarang.filter { it.nama.contains(searchQuery, ignoreCase = true) },
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                onItemClick = { onBarangClick(it.id) }
            )
        }
    }
}
@Composable
fun ListBarang(
    itemBarang: List<Barang>,
    modifier: Modifier = Modifier,
    onItemClick: (Barang) -> Unit
) {
    LazyColumn(modifier = Modifier) {
        items(items = itemBarang, key = { it.id }) { person ->
            DataBarang(
                barang = person,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(person) }
            )
        }
    }
}
@Composable
fun DataBarang(
    barang: Barang,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = barang.tanggal,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                )
                Text(
                    text = barang.kode,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = barang.nama,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = barang.jumlah,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = barang.harga,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
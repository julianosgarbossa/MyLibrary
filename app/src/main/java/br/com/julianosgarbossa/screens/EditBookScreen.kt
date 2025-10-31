package br.com.julianosgarbossa.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBookScreen(
    bookId: String,
    onDone: () -> Unit,
    viewModel: EditBookViewModel = viewModel()
) {
    LaunchedEffect(bookId) { viewModel.load(bookId) }
    val formState = viewModel.form.collectAsState()
    var statusExpanded by remember { mutableStateOf(false) }
    val statuses = listOf("Lendo", "Lido", "Quero Ler")

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = formState.value.title,
            onValueChange = { s -> viewModel.update { it.copy(title = s) } },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = formState.value.author,
            onValueChange = { s -> viewModel.update { it.copy(author = s) } },
            label = { Text("Autor") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = formState.value.year,
                onValueChange = { s -> viewModel.update { it.copy(year = s) } },
                label = { Text("Ano") },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = formState.value.pages,
                onValueChange = { s -> viewModel.update { it.copy(pages = s) } },
                label = { Text("Páginas") },
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = formState.value.genre,
            onValueChange = { s -> viewModel.update { it.copy(genre = s) } },
            label = { Text("Gênero") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        ExposedDropdownMenuBox(
            expanded = statusExpanded,
            onExpandedChange = { statusExpanded = it }
        ) {
            OutlinedTextField(
                value = formState.value.status,
                onValueChange = {},
                readOnly = true,
                label = { Text("Status") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = statusExpanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            DropdownMenu(expanded = statusExpanded, onDismissRequest = { statusExpanded = false }) {
                statuses.forEach { st ->
                    DropdownMenuItem(text = { Text(st) }, onClick = {
                        viewModel.update { it.copy(status = st) }
                        statusExpanded = false
                    })
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = formState.value.notes,
            onValueChange = { s -> viewModel.update { it.copy(notes = s) } },
            label = { Text("Anotações") },
            modifier = Modifier.fillMaxWidth().height(120.dp)
        )
        Spacer(Modifier.height(16.dp))
        Row {
            repeat(5) { i ->
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.clickable { viewModel.update { it.copy(rating = i + 1) } }.padding(4.dp),
                    tint = if (i < formState.value.rating) androidx.compose.ui.graphics.Color(0xFFFFC107) else androidx.compose.ui.graphics.Color.LightGray
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { viewModel.save(onDone) }, modifier = Modifier.fillMaxWidth()) {
            Text("Salvar Alterações")
        }
    }
}



package br.com.julianosgarbossa.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DetailBookScreen(
    bookId: String,
    viewModel: DetailBookViewModel = viewModel()
) {
    LaunchedEffect(bookId) { viewModel.load(bookId) }
    val book by viewModel.book.collectAsState()
    Column(Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState())) {
        book?.let { b ->
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 3.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(Modifier.padding(0.dp)) {
                    ListItem(
                        leadingContent = { Icon(Icons.Filled.Book, contentDescription = null) },
                        headlineContent = { Text(b.title.ifBlank { "(Sem título)" }) },
                        supportingContent = { Text("Título") }
                    )
                    HorizontalDivider()
                    ListItem(
                        leadingContent = { Icon(Icons.Filled.Person, contentDescription = null) },
                        headlineContent = { Text(b.author.ifBlank { "(Autor desconhecido)" }) },
                        supportingContent = { Text("Autor") }
                    )
                    HorizontalDivider()
                    ListItem(
                        leadingContent = { Icon(Icons.Filled.Event, contentDescription = null) },
                        headlineContent = { Text(b.year.toString()) },
                        supportingContent = { Text("Ano") }
                    )
                    HorizontalDivider()
                    ListItem(
                        leadingContent = { Icon(Icons.AutoMirrored.Filled.MenuBook, contentDescription = null) },
                        headlineContent = { Text(b.pages.toString()) },
                        supportingContent = { Text("Páginas") }
                    )
                    HorizontalDivider()
                    ListItem(
                        leadingContent = { Icon(Icons.Filled.Category, contentDescription = null) },
                        headlineContent = { Text(b.genre.ifBlank { "—" }) },
                        supportingContent = { Text("Gênero") }
                    )
                    HorizontalDivider()
                    ListItem(
                        leadingContent = { Icon(Icons.AutoMirrored.Filled.Notes, contentDescription = null) },
                        headlineContent = { Text(b.status) },
                        supportingContent = { Text("Status") }
                    )
                    HorizontalDivider()
                    ListItem(
                        leadingContent = { Icon(Icons.Filled.Star, contentDescription = null, tint = androidx.compose.ui.graphics.Color(0xFFFFC107)) },
                        headlineContent = { Text("${b.rating}") },
                        supportingContent = { Text("Avaliação") }
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 3.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Anotações",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = b.notes.ifBlank { "Sem anotações." },
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        } ?: run {
            Text("Livro não encontrado", style = MaterialTheme.typography.bodyLarge)
        }
    }
}



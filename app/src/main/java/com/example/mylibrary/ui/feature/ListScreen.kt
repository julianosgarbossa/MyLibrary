package com.example.mylibrary.ui.feature

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mylibrary.domain.Book
import com.example.mylibrary.domain.book1
import com.example.mylibrary.domain.book2
import com.example.mylibrary.domain.book3
import com.example.mylibrary.domain.book4
import com.example.mylibrary.ui.components.BookItem
import com.example.mylibrary.ui.theme.MyLibraryTheme

@Composable
fun ListScreen() {
    ListContent(books = emptyList())
}

@Composable
fun ListContent(
    books: List<Book>
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .consumeWindowInsets(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(books) { index, book ->
                BookItem(
                    book = book,
                    onCompledtedChange = {},
                    onItemClick = {},
                    onEditClick = {},
                    onDeleteClick = {}
                )

                if (index < books.lastIndex) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview
@Composable
private fun ListContentPreview() {
    MyLibraryTheme {
        ListContent(
            books = listOf(
                book1,
                book2,
                book3,
                book4
            )
        )
    }
}
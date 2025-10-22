package com.example.mylibrary.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mylibrary.domain.Book
import com.example.mylibrary.domain.book1
import com.example.mylibrary.domain.book3
import com.example.mylibrary.ui.theme.MyLibraryTheme

@Composable
fun BookItem(
    book: Book,
    onCompledtedChange: (Boolean) -> Unit,
    onItemClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onItemClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 2.dp,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline
        )
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.width(0.dp))

            Checkbox(checked = book.isCompleted, onCheckedChange = onCompledtedChange)

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (book.isCompleted) TextDecoration.LineThrough else null
                )
                
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = book.author,
                    style = MaterialTheme.typography.bodySmall,
                    textDecoration = if (book.isCompleted) TextDecoration.LineThrough else null
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }

            Spacer(modifier = Modifier.width(0.dp))

            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Preview
@Composable
private fun BookItemPreview() {
    MyLibraryTheme {
        BookItem(
            book = book3,
            onCompledtedChange = {},
            onItemClick = {},
            onEditClick = {},
            onDeleteClick = {}
        )
    }
}

@Preview
@Composable
private fun BookItemCompletedPreview() {
    MyLibraryTheme {
        BookItem(
            book = book1,
            onCompledtedChange = {},
            onItemClick = {},
            onEditClick = {},
            onDeleteClick = {}
        )
    }
}


package br.com.julianosgarbossa.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.julianosgarbossa.model.Book
import br.com.julianosgarbossa.model.LocalData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListBookViewModel(application: Application) : AndroidViewModel(application) {
    private val localData = LocalData(application)

    private val _books = MutableStateFlow<List<Book>>(emptyList())

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    val filteredBooks: StateFlow<List<Book>> = combine(_books, _query) { list, q ->
        val qNorm = q.trim().lowercase()
        list.filter { book ->
            if (qNorm.isEmpty()) true else {
                book.title.lowercase().contains(qNorm) || book.author.lowercase().contains(qNorm)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _books.value = localData.getAllBooks()
        }
    }

    fun deleteBook(bookId: String) {
        viewModelScope.launch {
            localData.deleteBook(bookId)
            _books.value = localData.getAllBooks()
        }
    }

    fun setQuery(value: String) { _query.value = value }
}



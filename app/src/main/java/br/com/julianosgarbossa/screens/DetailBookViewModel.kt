package br.com.julianosgarbossa.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.julianosgarbossa.model.Book
import br.com.julianosgarbossa.model.LocalData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailBookViewModel(application: Application) : AndroidViewModel(application) {
    private val localData = LocalData(application)
    private val _book = MutableStateFlow<Book?>(null)
    val book: StateFlow<Book?> = _book

    fun load(bookId: String) {
        _book.value = localData.getBookById(bookId)
    }
}



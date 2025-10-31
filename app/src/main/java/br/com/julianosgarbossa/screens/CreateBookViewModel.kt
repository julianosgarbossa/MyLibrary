package br.com.julianosgarbossa.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.julianosgarbossa.model.Book
import br.com.julianosgarbossa.model.LocalData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateBookViewModel(application: Application) : AndroidViewModel(application) {
    private val localData = LocalData(application)

    private val _form = MutableStateFlow(BookFormState())
    val form: StateFlow<BookFormState> = _form

    fun update(block: (BookFormState) -> BookFormState) {
        _form.value = block(_form.value)
    }

    fun add(onDone: () -> Unit) {
        viewModelScope.launch {
            val f = _form.value
            if (f.title.isBlank() || f.author.isBlank()) {
                return@launch
            }
            val statusFinal = f.status.ifBlank { "Quero Ler" }
            val ratingFinal = if (f.rating < 0) 0 else f.rating
            val book = Book(
                title = f.title.trim(),
                author = f.author.trim(),
                year = f.year.toIntOrNull() ?: 0,
                genre = f.genre.trim(),
                pages = f.pages.toIntOrNull() ?: 0,
                status = statusFinal,
                rating = ratingFinal,
                notes = f.notes.trim()
            )
            localData.addBook(book)
            onDone()
        }
    }
}



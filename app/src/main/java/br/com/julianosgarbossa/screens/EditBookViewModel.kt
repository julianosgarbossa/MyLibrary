package br.com.julianosgarbossa.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.julianosgarbossa.model.Book
import br.com.julianosgarbossa.model.LocalData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditBookViewModel(application: Application) : AndroidViewModel(application) {
    private val localData = LocalData(application)
    private val _form = MutableStateFlow(BookFormState())
    val form: StateFlow<BookFormState> = _form
    private var currentId: String? = null

    fun load(bookId: String) {
        currentId = bookId
        val book = localData.getBookById(bookId) ?: return
        _form.value = BookFormState(
            title = book.title,
            author = book.author,
            year = book.year.toString(),
            genre = book.genre,
            pages = book.pages.toString(),
            status = book.status,
            rating = book.rating,
            notes = book.notes
        )
    }

    fun update(block: (BookFormState) -> BookFormState) {
        _form.value = block(_form.value)
    }

    fun save(onDone: () -> Unit) {
        val id = currentId ?: return
        viewModelScope.launch {
            val f = _form.value
            val updated = Book(
                id = id,
                title = f.title.trim(),
                author = f.author.trim(),
                year = f.year.toIntOrNull() ?: 0,
                genre = f.genre.trim(),
                pages = f.pages.toIntOrNull() ?: 0,
                status = f.status,
                rating = f.rating,
                notes = f.notes.trim()
            )
            localData.updateBook(updated)
            onDone()
        }
    }
}



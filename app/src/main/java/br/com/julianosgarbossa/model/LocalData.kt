package br.com.julianosgarbossa.model

import android.content.Context
import android.content.SharedPreferences
import br.com.julianosgarbossa.base.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocalData(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()
    private val listType = object : TypeToken<List<Book>>() {}.type

    fun getAllBooks(): List<Book> {
        val json = prefs.getString(Constants.KEY_BOOKS_JSON, null) ?: return emptyList()
        return try {
            gson.fromJson<List<Book>>(json, listType) ?: emptyList()
        } catch (_: Exception) {
            emptyList()
        }
    }

    private fun saveAllBooks(books: List<Book>) {
        val json = gson.toJson(books)
        prefs.edit().putString(Constants.KEY_BOOKS_JSON, json).apply()
    }

    fun addBook(book: Book) {
        val updated = getAllBooks().toMutableList().apply { add(book) }
        saveAllBooks(updated)
    }

    fun updateBook(updatedBook: Book) {
        val updated = getAllBooks().map { if (it.id == updatedBook.id) updatedBook else it }
        saveAllBooks(updated)
    }

    fun deleteBook(bookId: String) {
        val updated = getAllBooks().filterNot { it.id == bookId }
        saveAllBooks(updated)
    }

    fun getBookById(bookId: String): Book? = getAllBooks().firstOrNull { it.id == bookId }
}



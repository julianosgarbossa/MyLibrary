package com.example.mylibrary.domain

import androidx.annotation.StringRes

data class Book(
    val id: Long,
    val title: String,
    val author: String,
    val isCompleted: Boolean,
)

// mock objects
val book1 = Book(
    id = 1,
    title = "Engenharia de Software Moderna",
    author = "Marco Tulio Valente",
    isCompleted = true,
)

val book2 = Book(
    id = 2,
    title = "Código Limpo",
    author = "Robert C. Martin",
    isCompleted = true,
)

val book3 = Book(
    id = 3,
    title = "Arquitetura Limpa",
    author = "Robert C. Martin",
    isCompleted = false,
)

val book4 = Book(
    id = 4,
    title = "Entendendo Algoritmos",
    author = "Aditya Y. Bhargava",
    isCompleted = true,
)



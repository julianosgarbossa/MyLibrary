package br.com.julianosgarbossa.model

import java.util.UUID

data class Book(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val author: String,
    val year: Int,
    val genre: String,
    val pages: Int,
    val status: String,
    val rating: Int,
    val notes: String
)



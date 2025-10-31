package br.com.julianosgarbossa.screens

data class BookFormState(
    val title: String = "",
    val author: String = "",
    val year: String = "",
    val genre: String = "",
    val pages: String = "",
    val status: String = "Quero Ler",
    val rating: Int = 0,
    val notes: String = ""
)



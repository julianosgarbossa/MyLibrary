package br.com.julianosgarbossa.base

sealed class Routes(val route: String) {
    data object ListBooks : Routes("listBooks")
    data object CreateBook : Routes("createBook")
    data object EditBook : Routes("editBook/{bookId}") {
        fun buildRoute(bookId: String) = "editBook/$bookId"
        const val ARG_BOOK_ID = "bookId"
    }
    data object DetailBook : Routes("detailBook/{bookId}") {
        fun buildRoute(bookId: String) = "detailBook/$bookId"
        const val ARG_BOOK_ID = "bookId"
    }
}



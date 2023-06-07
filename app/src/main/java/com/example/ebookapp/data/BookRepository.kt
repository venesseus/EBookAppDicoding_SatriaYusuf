package com.example.ebookapp.data

import com.example.ebookapp.model.Book
import com.example.ebookapp.model.FakeBookDataSource
import kotlinx.coroutines.flow.Flow

class BookRepository {

    fun getBook(): List<Book> {
        return FakeBookDataSource.dummyBook
    }

    fun searchBook(query: String): List<Book> {
        return FakeBookDataSource.dummyBook.filter { book ->
            book.title.contains(query, ignoreCase = true)
        }
    }

    fun getBookById(id: String): Book {
        return FakeBookDataSource.dummyBook.find {
            it.id == id
        } as Book
    }
}
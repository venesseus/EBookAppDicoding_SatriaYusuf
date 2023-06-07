package com.example.ebookapp.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ebookapp.data.BookRepository
import com.example.ebookapp.model.Book
import com.example.ebookapp.ui.common.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val bookRepository: BookRepository,
) : ViewModel() {

    private val _books = MutableStateFlow(
        bookRepository.getBook()
            .sortedBy { it.title }
            .groupBy { it.title[0] }
    )
    val books: StateFlow<Map<Char, List<Book>>> get() = _books

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun Search(newQuery: String) {
        _query.value = newQuery
        _books.value = bookRepository.searchBook(newQuery)
            .sortedBy { it.title }
            .groupBy { it.title[0] }
    }
}
package com.example.ebookapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ebookapp.data.BookRepository
import com.example.ebookapp.model.Book
import com.example.ebookapp.ui.common.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(private val bookRepository: BookRepository) : ViewModel() {

    fun getBookData (id: String): StateFlow<Book> = MutableStateFlow(
        bookRepository.getBookById(id)
    ).asStateFlow()
}
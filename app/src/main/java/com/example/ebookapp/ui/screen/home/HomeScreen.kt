package com.example.ebookapp.ui.screen.home

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ebookapp.data.BookRepository
import com.example.ebookapp.ui.components.BookHeader
import com.example.ebookapp.ui.components.BookItem
import com.example.ebookapp.ui.components.ScrollTopButton
import com.example.ebookapp.ui.components.SearchView
import com.example.ebookapp.ui.theme.EBookAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(
            BookRepository()
        )
    ),
    navigateToDetail: (String) -> Unit,
) {
    val books by viewModel.books.collectAsState()
    val query by viewModel.query

    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                SearchView(
                    query = query,
                    onQueryChange = viewModel::Search,
                    modifier = Modifier
                        .background(MaterialTheme.colors.primary)
                )
            }
            books.forEach{ (initial, books) ->
                stickyHeader {
                    BookHeader(initial)
                }
                items(books, key = { it.id }) { book ->
                    BookItem(
                        id = book.id,
                        title = book.title,
                        description = book.description,
                        image = book.image,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement(tween(durationMillis = 1000)),
                        navigateToDetail = navigateToDetail
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            ScrollTopButton(
                onClick = {
                    scope.launch {
                        listState.scrollToItem(index = 0)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    EBookAppTheme {
        HomeScreen(
            modifier = Modifier,
            navigateToDetail = {}
        )
    }
}
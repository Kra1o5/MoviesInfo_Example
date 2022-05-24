package com.randomdroids.moviesinfo.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.randomdroids.moviesinfo.data.server.ServerConstants.IMG_URL
import com.randomdroids.moviesinfo.domain.Movie


@Composable
fun MovieCard(
    elevation: Dp = 1.dp,
    border: BorderStroke? = null,
    background: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(background),
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    movieItem: Movie
) {
    Card(
        backgroundColor = background,
        contentColor = contentColor,
        shape = shape,
        elevation = elevation,
        border = border
    ) {
        // Container
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.fillMaxWidth()) {
                    movieItem.title?.let { Text(text = it, style = MaterialTheme.typography.h6) }

                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        movieItem.publishDate?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.body1
                            )
                        }
                    }
                }
            }

            // Multimedia
            movieItem.posterPath?.let {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(),
                    model = IMG_URL + it,
                    contentScale = ContentScale.FillHeight,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun MovieList(viewModel: MainViewModel) {
    viewModel.requestMoviesList()
    val movieData = viewModel.movies.collectAsState().value
    val loadingState = viewModel.loading.collectAsState().value
    val movies = List(movieData.size) { index ->

        Movie(
            posterPath = movieData[index].posterPath,
            title = movieData[index].title,
            publishDate = movieData[index].publishDate!!.take(4),
            id = movieData[index].id,
        )
    }

    AnimatedVisibility(
        modifier = Modifier.fillMaxSize(),
        visible = loadingState,
        enter = EnterTransition.None,
        exit = fadeOut()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .background(Color.Transparent)
                .wrapContentSize()
        )
    }

    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(movies) { item ->
            MovieCard(
                movieItem = item,
            )
        }
    }
}
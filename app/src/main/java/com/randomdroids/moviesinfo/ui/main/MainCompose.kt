package com.randomdroids.moviesinfo.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.randomdroids.moviesinfo.R
import com.randomdroids.moviesinfo.data.server.ServerConstants.IMG_URL
import com.randomdroids.moviesinfo.domain.Movie

@Composable
fun SearchInput(viewModel: MainViewModel) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            viewModel.requestMoviesList(it)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors.primary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface, CircleShape),
        leadingIcon = {
            if (text.isEmpty()) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        },
        shape = CircleShape,
        maxLines = 1,
        singleLine = true
    )
}

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    elevation: Dp = 1.dp,
    border: BorderStroke? = null,
    background: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(background),
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    movieItem: Movie,
    navController: NavHostController
) {
    Card(
        backgroundColor = background,
        contentColor = contentColor,
        shape = shape,
        elevation = elevation,
        border = border,
        modifier = modifier
            .clickable {
                navController.navigate("details/${movieItem.id}")
            },
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
fun MovieList(
    viewModel: MainViewModel,
    navController: NavHostController,
) {
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

        item { SearchInput(viewModel) }
        items(movies) { item ->
            MovieCard(
                movieItem = item,
                navController = navController
            )
        }
    }

    if (movieData.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.no_results),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
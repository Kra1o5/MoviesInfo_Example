package com.randomdroids.moviesinfo.ui.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.randomdroids.moviesinfo.R
import com.randomdroids.moviesinfo.data.server.ServerConstants.IMG_URL
import com.randomdroids.moviesinfo.domain.MovieDetails


@Composable
private fun MovieHeader(
    scrollState: ScrollState,
    movieDetails: MovieDetails,
    containerHeight: Dp
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    movieDetails.posterPath?.let {
        AsyncImage(
            modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 0.dp)
                .heightIn(max = containerHeight / 2)
                .fillMaxWidth()
                .padding(top = offsetDp),
            model = IMG_URL + it,
            contentScale = ContentScale.FillHeight,
            contentDescription = null
        )
    }

}

@Composable
fun MovieScreen(viewModel: DetailsViewModel = viewModel()) {
    val movieData = viewModel.movies.collectAsState().value
    val loadingState = viewModel.loading.collectAsState().value
    val scrollState = rememberScrollState()

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

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    movieData?.let {
                        MovieHeader(
                            scrollState,
                            it,
                            this@BoxWithConstraints.maxHeight
                        )
                    }
                    movieData?.let { MovieContent(movieDetails = it, containerHeight = 200.dp) }
                }
            }
        }
    }
}

@Composable
private fun MovieTitle(
    movieDetails: MovieDetails
) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)) {
        movieDetails.title?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun MovieInfo(label: String, value: String) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider(modifier = Modifier.padding(bottom = 4.dp))
        Text(
            text = label,
            modifier = Modifier.height(24.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.subtitle1,
        )
        Text(
            text = value,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Visible
        )
    }
}

@Composable
private fun MovieContent(movieDetails: MovieDetails, containerHeight: Dp) {
    Column {
        MovieTitle(movieDetails)
        movieDetails.releaseDate?.let {
            MovieInfo(
                stringResource(R.string.release_date),
                it.take(4)
            )
        }
        movieDetails.description?.let { MovieInfo(stringResource(R.string.description), it) }
        movieDetails.duration?.let { MovieInfo(stringResource(R.string.duration), it) }
        movieDetails.director?.crew?.find { it.job == "Director" }?.name?.let {
            MovieInfo(
                stringResource(R.string.director),
                it
            )
        }
        movieDetails.score?.let { MovieInfo(stringResource(R.string.score), it) }
        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}
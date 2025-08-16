package com.example.jikananimeapp.composeScreens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jikananimeapp.BuildConfig
import com.example.jikananimeapp.R
import com.example.jikananimeapp.data.AnimeEntity

@Composable
fun HomeListScreen(
    items: List<AnimeEntity>,
    onAnimeClick: (Int) -> Unit,
    onRetry: () -> Unit
) {
    Column {
        TopBar()

        if (items.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "No internet or no data available.")
                Spacer(Modifier.height(12.dp))
                Button(onClick = onRetry) {
                    Text("Retry")
                }
            }
        } else {
            LazyColumn {
                items(items) { anime ->
                    AnimeItem(
                        anime = anime,
                        onClick = { onAnimeClick(anime.mal_id) }
                    )
                }
            }
        }
    }


}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AnimeItem(anime: AnimeEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.background)),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
        ) {
            val (animeImg, text, episodes, rating) = createRefs()
            if (!BuildConfig.HIDE_POSTERS) {
                GlideImage(
                    model = anime.image_url,
                    contentDescription = anime.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .constrainAs(animeImg) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                )
            } else {
                // Placeholder that is same size
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .constrainAs(animeImg) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                        .background(colorResource(id = R.color.border_color)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("N/A", color = colorResource(id = R.color.title_text_color))
                }
            }

            Text(
                text = anime.title ?: "No Title",
                modifier = Modifier
                    .padding(start = 16.dp, end = 6.dp, top = 16.dp)
                    .constrainAs(text) {
                        start.linkTo(animeImg.end)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        width = Dimension.fillToConstraints
                    },
                fontSize = 16.sp,
                lineHeight = 15.sp,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily.Default,
                color = colorResource(id = R.color.title_text_color),
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Episodes: ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                        append(anime.episodes?.toString() ?: "No Title")
                    }
                },
                modifier = Modifier
                    .padding(start = 16.dp, end = 6.dp, top = 8.dp)
                    .constrainAs(episodes) {
                        start.linkTo(animeImg.end)
                        end.linkTo(parent.end)
                        top.linkTo(text.bottom)
                        width = Dimension.fillToConstraints
                    },
                fontSize = 14.sp,
                lineHeight = 15.sp,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily.Default,
                color = colorResource(id = R.color.title_text_color)
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Rating: ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                        append(anime.score.toString())
                    }
                },
                modifier = Modifier
                    .padding(start = 16.dp, end = 6.dp, top = 2.dp)
                    .constrainAs(rating) {
                        start.linkTo(animeImg.end)
                        end.linkTo(parent.end)
                        top.linkTo(episodes.bottom)
                        width = Dimension.fillToConstraints
                    },
                fontSize = 14.sp,
                lineHeight = 15.sp,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily.Default,
                color = colorResource(id = R.color.title_text_color)
            )
        }
    }
}


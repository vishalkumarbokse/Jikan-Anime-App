package com.example.jikananimeapp.composeScreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Dimension
import com.example.jikananimeapp.R
import com.example.jikananimeapp.data.AnimeDetailEntity

@Composable
fun DetailScreen(detail: AnimeDetailEntity?) {
    if (detail == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
            Text(text = "You are offline or the data is not available", modifier = Modifier.padding(16.dp))
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TopBar()
            AnimeTrailerPlayer(youtubeId = detail.youtube_id, embedUrl = detail.trailer_embed_url)
            Spacer(Modifier.height(12.dp))
            Text(text = detail.title ?: "", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Genres: ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                        append(detail.genres ?: "No Title")
                    }
                },
                modifier = Modifier
                    .padding(top = 2.dp, start = 16.dp, end = 16.dp),
                fontSize = 16.sp,
                lineHeight = 15.sp,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily.Default,
                color = colorResource(id = R.color.title_text_color)
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Episodes: ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                        append(detail.episodes.toString() ?: "No Title")
                    }
                },
                modifier = Modifier
                    .padding(top = 2.dp, start = 16.dp, end = 16.dp),
                fontSize = 16.sp,
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
                        append(detail.score.toString())
                    }
                },
                modifier = Modifier
                    .padding(top = 2.dp, start = 16.dp, end = 16.dp),
                fontSize = 16.sp,
                lineHeight = 15.sp,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily.Default,
                color = colorResource(id = R.color.title_text_color)
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Main Cast: ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                        append(detail.mainCast.toString())
                    }
                },
                modifier = Modifier
                    .padding(top = 2.dp, start = 16.dp, end = 16.dp),
                fontSize = 16.sp,
                lineHeight = 18.sp,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily.Default,
                color = colorResource(id = R.color.title_text_color)
            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                    .height(1.dp),
                color = colorResource(id = R.color.border_color)
            )
            Spacer(Modifier.height(8.dp))
            Text(text = detail.synopsis ?: "", style = MaterialTheme.typography.bodyMedium,modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp))
        }
    }
}

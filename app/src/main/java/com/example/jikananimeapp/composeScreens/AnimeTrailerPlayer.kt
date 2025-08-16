package com.example.jikananimeapp.composeScreens

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter
import com.example.jikananimeapp.BuildConfig

@Composable
fun AnimeTrailerPlayer(
    youtubeId: String?,
    embedUrl: String?,
    modifier: Modifier = Modifier
) {
    val thumbnailUrl = youtubeId?.let { "https://img.youtube.com/vi/$it/hqdefault.jpg" }
    var playVideo by remember { mutableStateOf(false) }

    Box(modifier = modifier
        .fillMaxWidth()
        .height(200.dp)
    ) {
        when {
            !embedUrl.isNullOrEmpty() && !playVideo && !BuildConfig.HIDE_POSTERS -> {
                // Show thumbnail image
                thumbnailUrl?.let { url ->
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = rememberAsyncImagePainter(url),
                            contentDescription = "Trailer Thumbnail",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable { playVideo = true }
                        )
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Play",
                            tint = Color.White,
                            modifier = Modifier
                                .size(48.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
            }

            playVideo && !embedUrl.isNullOrEmpty() -> {
                var isLoading by remember { mutableStateOf(true) }

                // WebView container
                Box(modifier = Modifier.fillMaxSize()) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    AndroidView(
                        factory = { context ->
                            WebView(context).apply {
                                layoutParams = android.view.ViewGroup.LayoutParams(
                                    android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                                    android.view.ViewGroup.LayoutParams.MATCH_PARENT
                                )
                                settings.javaScriptEnabled = true
                                settings.domStorageEnabled = true
                                settings.mediaPlaybackRequiresUserGesture = false
                                settings.loadWithOverviewMode = true
                                settings.useWideViewPort = true
                                webChromeClient = WebChromeClient()
                                webViewClient = object : WebViewClient() {
                                    override fun onPageFinished(view: WebView?, url: String?) {
                                        super.onPageFinished(view, url)
                                        isLoading = false
                                    }
                                }

                                val finalUrl = if (!embedUrl.contains("controls=")) {
                                    embedUrl + "&controls=1&modestbranding=1&rel=0"
                                } else embedUrl

                                val html = """
                                    <html>
                                      <body style="margin:0;padding:0;overflow:hidden;">
                                        <iframe 
                                          width="100%" 
                                          height="100%" 
                                          src="$finalUrl" 
                                          frameborder="0" 
                                          allowfullscreen 
                                          allow="autoplay; encrypted-media">
                                        </iframe>
                                      </body>
                                    </html>
                                """.trimIndent()

                                loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (BuildConfig.HIDE_POSTERS) "Poster Hidden" else "Trailer not available",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

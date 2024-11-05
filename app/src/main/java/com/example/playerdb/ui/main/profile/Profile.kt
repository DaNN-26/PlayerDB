package com.example.playerdb.ui.main.profile

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.playerdb.components.main.profile.ProfileComponent

@Composable
fun Profile(
    component: ProfileComponent,
    contentPadding: PaddingValues
) {
    val state by component.state.subscribeAsState()

   Column(
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally,
       modifier = Modifier.padding(contentPadding)
   ) {
       AndroidView(factory = { context ->
           WebView(context).apply {
               layoutParams = ViewGroup.LayoutParams(
                   ViewGroup.LayoutParams.MATCH_PARENT,
                   ViewGroup.LayoutParams.MATCH_PARENT
               )
               webViewClient = WebViewClient()
               loadUrl(state.url)
           }
       }, update = {
           it.loadUrl(state.url)
       })
   }
}
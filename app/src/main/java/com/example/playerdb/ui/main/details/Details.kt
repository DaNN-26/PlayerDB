package com.example.playerdb.ui.main.details

import android.content.Intent
import android.net.LinkAddress
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.playerdb.components.main.details.DetailsComponent
import com.example.playerdb.mvi.main.details.DetailsIntent
import com.example.playerdb.ui.components.TopBar
import java.net.URL

@Composable
fun Details(
    component: DetailsComponent
) {
    val state = component.state.subscribeAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = { TopBar(
            title = "Details",
            canNavigateBack = true,
            navigateBack = { component.processIntent(DetailsIntent.NavigateBack) }
        ) },
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
        if (state.value.isLoading) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                CircularProgressIndicator()
            }
        } else if(!state.value.isLoading && !state.value.isError) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .padding(contentPadding)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(state.value.user.data.player.avatar)
                        .build(),
                    contentDescription = "Profile image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .shadow(6.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .size(150.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Username: ${state.value.user.data.player.username}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "ID: ${state.value.user.data.player.id}\n" +
                            "STEAM2ID: ${state.value.user.data.player.meta.steam2Id}\n" +
                            "STEAM2ID NEW: ${state.value.user.data.player.meta.steam2IdNew}"
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(state.value.user.data.player.meta.profileUrl)
                        )
                        context.startActivity(intent)
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    elevation = ButtonDefaults.buttonElevation(6.dp)
                ) {
                    Text(
                        text = "Go to steam profile",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
        else {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                    Text(
                        text = "ERROR:\nACCOUNT NOT FOUND",
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
        }
    }
}
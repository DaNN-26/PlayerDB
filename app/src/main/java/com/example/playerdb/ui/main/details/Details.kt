package com.example.playerdb.ui.main.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.playerdb.components.main.details.DetailsComponent
import com.example.playerdb.mvi.main.details.DetailsIntent

@Composable
fun Details(
    component: DetailsComponent,
    contentPadding: PaddingValues
) {
    val state by component.state.subscribeAsState()
    val context = LocalContext.current

    Surface(
        modifier = Modifier.padding(contentPadding)
    ) {
        @Suppress("KotlinConstantConditions")
        if (state.isLoading) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        } else if (!state.isLoading && !state.isError) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(state.user.data.player.avatar)
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
                    text = "Username: ${state.user.data.player.username}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "ID: ${state.user.data.player.id}\n" +
                            "STEAM2ID: ${state.user.data.player.meta.steam2Id}\n" +
                            "STEAM2ID NEW: ${state.user.data.player.meta.steam2IdNew}"
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        component.processIntent(DetailsIntent.OpenProfileUrl)
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
        } else {
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
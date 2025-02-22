package br.com.localspotify.presentation.screen.player

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import br.com.localspotify.R
import br.com.localspotify.presentation.atomic.organism.BottomBarContentOrganism
import br.com.localspotify.presentation.screen.player.detail.DetailScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PlayerScreen(
    modifier: Modifier = Modifier
) {
    var showDetail by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        SharedTransitionLayout {
            AnimatedContent(
                targetState = showDetail,
                label = "Player Screen"
            ) { target ->
                if (!target) {
                    BottomBarContentOrganism(
                        title = "After Hours",
                        subtitle = "The Weeknd",
                        image = R.drawable.album_image_sample,
                        actionIcon = painterResource(R.drawable.ic_play),
                        onClickItem = { showDetail = true },
                        onClickAction = {},
                        animatedVisibilityScope = this@AnimatedContent,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )
                } else {
                    DetailScreen(
                        title = "After Hours",
                        subtitle = "The Weeknd",
                        image = R.drawable.album_image_sample,
                        actionIcon = painterResource(R.drawable.ic_play),
                        animatedVisibilityScope = this@AnimatedContent,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        onClickExit = { showDetail = false }
                    )
                }
            }
        }
    }
}
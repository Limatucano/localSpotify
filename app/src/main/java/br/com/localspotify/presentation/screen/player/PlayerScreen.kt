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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.localspotify.R
import br.com.localspotify.presentation.atomic.organism.BottomBarContentOrganism
import br.com.localspotify.presentation.screen.player.detail.DetailScreen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PlayerScreen(
    modifier: Modifier = Modifier,
    viewModel: PlayerViewModel = koinViewModel()
) {
    var showDetail by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    uiState.currentSelectedAudio?.let {
        val actionIcon = if (uiState.isPlaying) painterResource(R.drawable.ic_pause) else painterResource(R.drawable.ic_play)
        Column(modifier = modifier) {
            SharedTransitionLayout {
                AnimatedContent(
                    targetState = showDetail,
                    label = "Player Screen"
                ) { target ->
                    if (!target) {
                        BottomBarContentOrganism(
                            title = it.title,
                            subtitle = it.artist,
                            image = R.drawable.album_image_sample,
                            actionIcon = actionIcon,
                            onClickItem = { showDetail = true },
                            onClickAction = viewModel::playPause,
                            animatedVisibilityScope = this@AnimatedContent,
                            sharedTransitionScope = this@SharedTransitionLayout
                        )
                    } else {
                        DetailScreen(
                            title = it.title,
                            subtitle = it.artist,
                            duration = it.duration,
                            image = R.drawable.album_image_sample,
                            actionIcon = actionIcon,
                            animatedVisibilityScope = this@AnimatedContent,
                            sharedTransitionScope = this@SharedTransitionLayout,
                            onClickExit = { showDetail = false },
                            progress = uiState.progress,
                            onProgressChange = viewModel::updateProgress,
                            onClickAction = viewModel::playPause
                        )
                    }
                }
            }
        }
    }
}
package br.com.localspotify.presentation.screen.player.detail

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.localspotify.R
import br.com.localspotify.presentation.atomic.atom.ActionButtonAtom
import br.com.localspotify.presentation.atomic.atom.NextButtonAtom
import br.com.localspotify.presentation.atomic.atom.PlayPauseButtonAtom
import br.com.localspotify.presentation.atomic.atom.PreviousButtonAtom
import br.com.localspotify.presentation.atomic.atom.Spacer
import br.com.localspotify.presentation.atomic.atom.TextAtom
import br.com.localspotify.presentation.theme.AppTheme
import br.com.localspotify.presentation.theme.dimen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int? = null,
    title: String,
    subtitle: String? = null,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClickExit: () -> Unit,
    actionIcon: Painter,
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.onSecondary)
            .padding(MaterialTheme.dimen.md)
    ) {
        with(sharedTransitionScope) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                ActionButtonAtom(
                    icon = painterResource(R.drawable.ic_arrow_down),
                    contentDescription = "Play/Pause button"
                ) {
                    onClickExit()
                }
            }
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.dimen.lg,
                        vertical = MaterialTheme.dimen.md
                    )
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .sharedElement(
                            state = rememberSharedContentState(key = "image/$image"),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                    shape = RoundedCornerShape(MaterialTheme.dimen.md),
                    elevation = CardDefaults.cardElevation(MaterialTheme.dimen.zero),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.inverseSurface,
                    )
                ) {
                    image?.let {
                        Image(
                            painter = painterResource(it),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = ""
                        )
                    }
                }
                Spacer(MaterialTheme.dimen.sm)
                TextAtom(
                    modifier = Modifier
                        .sharedBounds(
                            rememberSharedContentState(key = "text/$title"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        ),
                    text = title,
                    textStyle = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                subtitle?.let {
                    TextAtom(
                        modifier = Modifier
                            .sharedBounds(
                                rememberSharedContentState(key = "text/$it"),
                                animatedVisibilityScope = animatedVisibilityScope,
                            ),
                        text = it,
                        textStyle = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Normal
                    )
                }

                Spacer(MaterialTheme.dimen.lg)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PreviousButtonAtom { }

                    Spacer(MaterialTheme.dimen.sm)

                    PlayPauseButtonAtom(
                        size = MaterialTheme.dimen.xhuge,
                        icon = actionIcon
                    ) { }

                    Spacer(MaterialTheme.dimen.sm)

                    NextButtonAtom { }
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun DetailScreenPreviewLight() {
    AppTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                DetailScreen(
                    title = "After Hours",
                    subtitle = "The Weeknd",
                    image = R.drawable.album_image_sample,
                    actionIcon = painterResource(R.drawable.ic_play),
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this,
                    onClickExit = {}
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun DetailScreenPreviewDark() {
    AppTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                DetailScreen(
                    title = "After Hours",
                    subtitle = "The Weeknd",
                    image = R.drawable.album_image_sample,
                    actionIcon = painterResource(R.drawable.ic_play),
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this,
                    onClickExit = {}
                )
            }
        }
    }
}
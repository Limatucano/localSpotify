package br.com.localspotify.presentation.atomic.organism

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import br.com.localspotify.R
import br.com.localspotify.presentation.atomic.atom.PlayPauseButtonAtom
import br.com.localspotify.presentation.atomic.atom.Spacer
import br.com.localspotify.presentation.atomic.atom.TextAtom
import br.com.localspotify.presentation.theme.AppTheme
import br.com.localspotify.presentation.theme.dimen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BottomBarContentOrganism(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int? = null,
    title: String,
    subtitle: String? = null,
    actionIcon: Painter,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClickAction: () -> Unit,
    onClickItem: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(NavigationBarDefaults.windowInsets)
            .imePadding()
            .clip(
                shape = RoundedCornerShape(
                    topStart = MaterialTheme.dimen.md,
                    topEnd = MaterialTheme.dimen.md
                )
            )
            .background(MaterialTheme.colorScheme.onSecondary)
            .clickable { onClickItem() }
            .padding(MaterialTheme.dimen.md),
        verticalAlignment = Alignment.CenterVertically
    ) {
        with(sharedTransitionScope) {
            Card(
                modifier = modifier
                    .size(
                        width = MaterialTheme.dimen.huge,
                        height = MaterialTheme.dimen.huge
                    )
                    .sharedElement(
                        state = rememberSharedContentState(key = "image/$image"),
                        animatedVisibilityScope = animatedVisibilityScope
                    ),
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(MaterialTheme.dimen.zero),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.inverseSurface,
                )
            ) {
                image?.let {
                    Image(
                        painter = painterResource(it),
                        contentDescription = ""
                    )
                }
            }
            Spacer(MaterialTheme.dimen.sm)
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                TextAtom(
                    modifier = Modifier
                        .sharedBounds(
                            rememberSharedContentState(key = "text/$title"),
                            animatedVisibilityScope = animatedVisibilityScope,
                        ),
                    text = title,
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
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Spacer(MaterialTheme.dimen.sm)

            PlayPauseButtonAtom(
                icon = actionIcon
            ) {
                onClickAction()
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun BottomBarContentOrganismPreviewLight() {
    AppTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                BottomBarContentOrganism(
                    title = "After Hours",
                    subtitle = "The Weeknd",
                    image = R.drawable.album_image_sample,
                    actionIcon = painterResource(R.drawable.ic_play),
                    onClickAction = {},
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun BottomBarContentOrganismPreviewDark() {
    AppTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                BottomBarContentOrganism(
                    title = "After Hours",
                    subtitle = "The Weeknd",
                    image = R.drawable.album_image_sample,
                    actionIcon = painterResource(R.drawable.ic_play),
                    onClickAction = {},
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun BottomBarContentOrganismPreviewLightNoImage() {
    AppTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                BottomBarContentOrganism(
                    title = "After Hours",
                    subtitle = "The Weeknd",
                    actionIcon = painterResource(R.drawable.ic_pause),
                    onClickAction = {},
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun BottomBarContentOrganismPreviewDarkNoImage() {
    AppTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                BottomBarContentOrganism(
                    title = "After Hours",
                    subtitle = "The Weeknd",
                    actionIcon = painterResource(R.drawable.ic_pause),
                    onClickAction = {},
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout
                )
            }
        }
    }
}
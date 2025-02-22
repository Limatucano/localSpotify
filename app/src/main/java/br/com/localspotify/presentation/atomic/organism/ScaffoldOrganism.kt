package br.com.localspotify.presentation.atomic.organism

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import br.com.localspotify.R
import br.com.localspotify.presentation.atomic.atom.ActionButtonAtom
import br.com.localspotify.presentation.atomic.atom.TextAtom
import br.com.localspotify.presentation.theme.AppTheme
import br.com.localspotify.presentation.theme.dimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldOrganism(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    hasTopBar: Boolean = true,
    hasBottomBar: Boolean = false,
    bottomBar: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            if (hasTopBar) {
                TopAppBar(
                    title = title,
                    navigationIcon = navigationIcon,
                    actions = actions
                )
            } else {
                Unit
            }
        },
        bottomBar = {
            if (hasBottomBar) bottomBar() else Unit
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .imePadding()
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ScaffoldOrganismPreviewLightWithBottomBar() {
    AppTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                ScaffoldOrganism(
                    content = {},
                    hasTopBar = false,
                    hasBottomBar = true,
                    bottomBar = {
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
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ScaffoldOrganismPreviewDarkWithBottomBar() {
    AppTheme {
        SharedTransitionLayout {
            AnimatedVisibility(visible = true) {
                ScaffoldOrganism(
                    content = {},
                    hasTopBar = false,
                    hasBottomBar = true,
                    bottomBar = {
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
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ScaffoldOrganismPreviewLightMain() {
    AppTheme {
        ScaffoldOrganism(
            content = {},
            title = {
                Image(
                    modifier = Modifier
                        .height(MaterialTheme.dimen.lg)
                        .width(MaterialTheme.dimen.xxhuge),
                    painter = painterResource(R.drawable.ic_logo),
                    contentDescription = null
                )
            },
            actions = {
                ActionButtonAtom(
                    modifier = Modifier
                        .padding(MaterialTheme.dimen.sm)
                        .size(MaterialTheme.dimen.lg)
                        .clip(CircleShape),
                    icon = painterResource(R.drawable.ic_search),
                    contentDescription = ""
                ) {}
            }
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ScaffoldOrganismPreviewDarkMain() {
    AppTheme {
        ScaffoldOrganism(
            content = {},
            title = {
                Image(
                    modifier = Modifier
                        .height(MaterialTheme.dimen.lg)
                        .width(MaterialTheme.dimen.xxhuge),
                    painter = painterResource(R.drawable.ic_logo),
                    contentDescription = null
                )
            },
            actions = {
                ActionButtonAtom(
                    modifier = Modifier
                        .padding(MaterialTheme.dimen.sm)
                        .size(MaterialTheme.dimen.lg)
                        .clip(CircleShape),
                    icon = painterResource(R.drawable.ic_search),
                    contentDescription = ""
                ) {}
            }
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ScaffoldOrganismPreviewLightJustText() {
    AppTheme {
        ScaffoldOrganism(
            content = {},
            title = {
                TextAtom(
                    text = "Configurações",
                    fontWeight = FontWeight.Bold,
                    textStyle = MaterialTheme.typography.bodyLarge
                )
            },
            navigationIcon = {
                ActionButtonAtom(
                    modifier = Modifier
                        .padding(MaterialTheme.dimen.sm)
                        .size(MaterialTheme.dimen.lg)
                        .clip(CircleShape),
                    icon = painterResource(R.drawable.ic_back),
                    contentDescription = ""
                ) {}
            }
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ScaffoldOrganismPreviewDarkJustText() {
    AppTheme {
        ScaffoldOrganism(
            content = {},
            title = {
                TextAtom(
                    text = "Configurações",
                    fontWeight = FontWeight.Bold,
                    textStyle = MaterialTheme.typography.bodyLarge
                )
            },
            navigationIcon = {
                ActionButtonAtom(
                    modifier = Modifier
                        .padding(MaterialTheme.dimen.sm)
                        .size(MaterialTheme.dimen.lg)
                        .clip(CircleShape),
                    icon = painterResource(R.drawable.ic_back),
                    contentDescription = ""
                ) {}
            }
        )
    }
}
package br.com.localspotify.presentation.screen.home

import android.Manifest
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.localspotify.R
import br.com.localspotify.presentation.atomic.atom.ActionButtonAtom
import br.com.localspotify.presentation.atomic.atom.Spacer
import br.com.localspotify.presentation.atomic.organism.RawMusicListOrganism
import br.com.localspotify.presentation.atomic.organism.SavedMusicListOrganism
import br.com.localspotify.presentation.atomic.organism.ScaffoldOrganism
import br.com.localspotify.presentation.theme.dimen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val permissions = rememberPermissionState(
        permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_AUDIO
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
    )

    LaunchedEffect(permissions.status) {
        when {
            permissions.status.shouldShowRationale -> { }

            !permissions.status.isGranted -> {
                permissions.launchPermissionRequest()
            }

            permissions.status.isGranted -> { viewModel.loadData() }
        }
    }

    ScaffoldOrganism(
        title = {
            Image(
                modifier = Modifier
                    .height(MaterialTheme.dimen.lg)
                    .width(MaterialTheme.dimen.xxxhuge),
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
    ) {
        PullToRefreshBox(
            isRefreshing = uiState.isLoading,
            onRefresh = viewModel::loadData
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                SavedMusicListOrganism(
                    music = uiState.savedMusic,
                    onClickItem = viewModel::handlePlayPause
                )
                Spacer(MaterialTheme.dimen.sm)
                RawMusicListOrganism(
                    music = uiState.rawMusic,
                    onClickItem = viewModel::handlePlayPause,
                    onClickSave = viewModel::onSaveMusic
                )
            }
        }
    }
}
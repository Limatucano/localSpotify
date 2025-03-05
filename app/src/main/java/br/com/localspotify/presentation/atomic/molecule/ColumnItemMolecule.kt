package br.com.localspotify.presentation.atomic.molecule

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import br.com.localspotify.R
import br.com.localspotify.presentation.atomic.atom.ActionButtonAtom
import br.com.localspotify.presentation.atomic.atom.CardImageAtom
import br.com.localspotify.presentation.atomic.atom.Spacer
import br.com.localspotify.presentation.atomic.atom.TextAtom
import br.com.localspotify.presentation.theme.AppTheme
import br.com.localspotify.presentation.theme.dimen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ColumnItemMolecule(
    modifier: Modifier = Modifier,
    title: String,
    isAlreadySaved: Boolean = false,
    image: Painter? = null,
    onClickItem: () -> Unit,
    onClickSave: () -> Unit
) {
    var isClicked by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClickItem() },
    ) {
        CardImageAtom(
            image = image,
            height = MaterialTheme.dimen.huge,
            width = MaterialTheme.dimen.huge
        )
        Spacer()
        Column(
            modifier = Modifier.weight(1f)
        ) {
            TextAtom(
                text = title,
                fontWeight = FontWeight.Bold
            )
        }
        if (!isAlreadySaved) {
            AnimatedContent(
                targetState = isClicked,
                transitionSpec = {
                    scaleIn(initialScale = 0.5f) + fadeIn() togetherWith
                            scaleOut(targetScale = 1.5f) + fadeOut()
                }
            ) {
                ActionButtonAtom(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    icon = painterResource(if(it) R.drawable.ic_check else R.drawable.ic_plus),
                    contentDescription = "",
                    onClick = {
                        coroutineScope.launch {
                            isClicked = true
                            delay(900L)
                            onClickSave()
                            isClicked = false
                        }
                    }
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ColumnItemMoleculePreview() {
    AppTheme {
        ColumnItemMolecule(
            title = "After Hours",
            image = painterResource(R.drawable.album_image_sample),
            onClickItem = {},
            onClickSave = {}
        )
    }
}
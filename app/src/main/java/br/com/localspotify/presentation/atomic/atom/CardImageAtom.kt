package br.com.localspotify.presentation.atomic.atom

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.localspotify.presentation.theme.dimen

@Composable
fun CardImageAtom(
    modifier: Modifier = Modifier,
    height: Dp = 130.dp,
    width: Dp = 130.dp,
    image: Painter? = null
) {
    Card(
        modifier = modifier
            .size(
                width = width,
                height = height
            ),
        elevation = CardDefaults.cardElevation(MaterialTheme.dimen.zero),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseSurface,
        )
    ) {
        image?.let {
            Image(
                painter = it,
                contentDescription = ""
            )
        }
    }
}
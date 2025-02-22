package br.com.localspotify.presentation.atomic.atom

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import br.com.localspotify.presentation.theme.AppTheme

@Composable
fun TextAtom(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge,
    color: Color = MaterialTheme.colorScheme.secondary,
    fontWeight: FontWeight = FontWeight.Normal,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = 1
) {
    Text(
        modifier = modifier,
        text = text,
        style = textStyle,
        color = color,
        overflow = overflow,
        fontWeight = fontWeight,
        maxLines = maxLines
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun TextAtomPreviewLight() {
    AppTheme {
        TextAtom(
            text = "Últimas ouvidas"
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun TextAtomPreviewDark() {
    AppTheme {
        TextAtom(
            text = "Últimas ouvidas"
        )
    }
}
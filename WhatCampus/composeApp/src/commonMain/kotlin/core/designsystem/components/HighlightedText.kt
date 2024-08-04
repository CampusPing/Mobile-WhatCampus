package core.designsystem.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle

@Composable
fun HighlightedText(
    fullText: String,
    highlightWords: List<String>,
    defaultColor: Color,
    highlightColor: Color,
    textStyle: TextStyle,
    textAlign: TextAlign = TextAlign.Center,
) {
    val annotatedString = buildAnnotatedString {
        var currentIndex = 0
        while (currentIndex < fullText.length) {
            val nextHighlightStart = highlightWords
                .map { fullText.indexOf(it, startIndex = currentIndex) }
                .filter { it >= 0 }
                .minOrNull() ?: fullText.length

            append(fullText.substring(currentIndex, nextHighlightStart))

            val highlightWord = highlightWords.find { fullText.startsWith(it, nextHighlightStart) }
            if (highlightWord != null) {
                withStyle(style = SpanStyle(color = highlightColor)) {
                    append(highlightWord)
                }
                currentIndex = nextHighlightStart + highlightWord.length
            } else {
                currentIndex = nextHighlightStart
            }
        }
    }

    Text(
        text = annotatedString,
        style = textStyle,
        color = defaultColor,
        textAlign = textAlign,
    )
}


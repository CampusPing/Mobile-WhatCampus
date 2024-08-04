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
    var currentTextIndex = 0
    val annotatedString = buildAnnotatedString {
        while (currentTextIndex < fullText.length) {
            val (nextHighlightStart, highlightWord) = findNextHighlight(
                text = fullText,
                highlightWords = highlightWords,
                startIndex = currentTextIndex
            )

            append(fullText.substring(currentTextIndex, nextHighlightStart))

            if (highlightWord == null) break

            withStyle(style = SpanStyle(color = highlightColor)) {
                append(highlightWord)
            }
            currentTextIndex = nextHighlightStart + highlightWord.length
        }
    }

    Text(
        text = annotatedString,
        style = textStyle.copy(color = defaultColor),
        textAlign = textAlign,
    )
}

private fun findNextHighlight(
    text: String,
    highlightWords: List<String>,
    startIndex: Int,
): Pair<Int, String?> {
    val highlightWordPositions = highlightWords.map { word -> text.indexOf(word, startIndex) to word }
    val validPositions = highlightWordPositions.filter { (startPosition, _) -> startPosition >= 0 }
    val nextHighlight = validPositions.minByOrNull { (startPosition, _) -> startPosition }

    return nextHighlight ?: (text.length to null)
}

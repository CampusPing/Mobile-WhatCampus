package feature.campusmap.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import core.designsystem.theme.WhatcamTheme

@Composable
fun ZoomableImage(
    image: Any?,
    contentDescription: String,
    modifier: Modifier = Modifier,
    isShowZoomScale: Boolean = true,
    maxScale: Float = 3f,
) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var imageSize by remember { mutableStateOf(Offset.Zero) }
    var viewportSize by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned { layoutCoordinates ->
                viewportSize = Offset(
                    layoutCoordinates.size.width.toFloat(),
                    layoutCoordinates.size.height.toFloat()
                )
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalPlatformContext.current)
                .data(image)
                .build(),
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned { layoutCoordinates ->
                    imageSize = Offset(
                        x = layoutCoordinates.size.width.toFloat(),
                        y = layoutCoordinates.size.height.toFloat()
                    )
                }
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale *= zoom
                        scale = scale.coerceIn(1f, maxScale)

                        val maxX = (imageSize.x * scale - viewportSize.x) / 2
                        val maxY = (imageSize.y * scale - viewportSize.y) / 2

                        val newOffset = if (scale == 1f) {
                            Offset.Zero
                        } else {
                            val newOffsetX = (offset.x + pan.x).coerceIn(-maxX, maxX)
                            val newOffsetY = (offset.y + pan.y).coerceIn(-maxY, maxY)
                            Offset(newOffsetX, newOffsetY)
                        }

                        offset = newOffset
                    }
                }
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y
                ),
            contentDescription = contentDescription,
            contentScale = ContentScale.Fit,
        )

        if (isShowZoomScale) {
            ZoomScale(
                scale = scale,
                onClick = { scale = 1f },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            )
        }
    }
}


@Composable
private fun ZoomScale(
    modifier: Modifier = Modifier,
    scale: Float,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(24.dp))
            .background(color = WhatcamTheme.colors.primary, shape = RoundedCornerShape(24.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp),
    ) {
        Text(
            text = "${(scale * 100).toInt()}%",
            style = WhatcamTheme.typography.labelLargeR,
            color = WhatcamTheme.colors.onPrimary,
            modifier = Modifier.background(color = WhatcamTheme.colors.primary)
        )
    }
}

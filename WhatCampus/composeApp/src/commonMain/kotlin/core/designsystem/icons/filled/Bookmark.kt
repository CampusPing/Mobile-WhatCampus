package core.designsystem.icons.filled

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import core.designsystem.theme.Black

private var _bookmark: ImageVector? = null

val Icons.Filled.Bookmark: ImageVector
    get() {
        if (_bookmark != null) {
            return _bookmark!!
        }

        _bookmark = ImageVector.Builder(
            name = "bookmark",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(10.375f, 33.875f)
                quadToRelative(-0.667f, 0.292f, -1.25f, -0.083f)
                reflectiveQuadToRelative(-0.583f, -1.084f)
                verticalLineTo(7.75f)
                quadToRelative(0f, -1.083f, 0.791f, -1.854f)
                quadToRelative(0.792f, -0.771f, 1.875f, -0.771f)
                horizontalLineToRelative(17.584f)
                quadToRelative(1.083f, 0f, 1.875f, 0.771f)
                quadToRelative(0.791f, 0.771f, 0.791f, 1.854f)
                verticalLineToRelative(24.958f)
                quadToRelative(0f, 0.709f, -0.583f, 1.084f)
                quadToRelative(-0.583f, 0.375f, -1.25f, 0.083f)
                lineTo(20f, 29.792f)
                close()
            }
        }.build()

        return _bookmark!!
    }

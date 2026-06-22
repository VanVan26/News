package com.ivanalexeevich.news.presentation.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp


object CustomIcons {
    val OpenInNew: ImageVector
        get() {
            if (_MaterialIconsOpenInNew != null) return _MaterialIconsOpenInNew!!

            _MaterialIconsOpenInNew = ImageVector.Builder(
                name = "open_in_new",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                path(
                    fill = SolidColor(Color.Transparent)
                ) {
                    moveTo(0f, 0f)
                    horizontalLineToRelative(24f)
                    verticalLineToRelative(24f)
                    horizontalLineTo(0f)
                    verticalLineTo(0f)
                    close()
                }
                path(
                    fill = SolidColor(Color.Black)
                ) {
                    moveTo(19f, 19f)
                    horizontalLineTo(5f)
                    verticalLineTo(5f)
                    horizontalLineToRelative(7f)
                    verticalLineTo(3f)
                    horizontalLineTo(5f)
                    curveToRelative(-1.11f, 0f, -2f, 0.9f, -2f, 2f)
                    verticalLineToRelative(14f)
                    curveToRelative(0f, 1.1f, 0.89f, 2f, 2f, 2f)
                    horizontalLineToRelative(14f)
                    curveToRelative(1.1f, 0f, 2f, -0.9f, 2f, -2f)
                    verticalLineToRelative(-7f)
                    horizontalLineToRelative(-2f)
                    verticalLineToRelative(7f)
                    close()
                    moveTo(14f, 3f)
                    verticalLineToRelative(2f)
                    horizontalLineToRelative(3.59f)
                    lineToRelative(-9.83f, 9.83f)
                    lineToRelative(1.41f, 1.41f)
                    lineTo(19f, 6.41f)
                    verticalLineTo(10f)
                    horizontalLineToRelative(2f)
                    verticalLineTo(3f)
                    horizontalLineToRelative(-7f)
                    close()
                }
            }.build()

            return _MaterialIconsOpenInNew!!
        }

    private var _MaterialIconsOpenInNew: ImageVector? = null
}
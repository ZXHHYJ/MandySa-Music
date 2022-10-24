package studio.mandysa.music.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.kyant.monet.a1
import com.kyant.monet.a2
import com.kyant.monet.withNight

//字体颜色
val textColor: Color
    @Composable get() = 2.a1 withNight Color.White

//字体颜色的辅助色
val textColorLight
    @Composable get() = 4.a1 withNight Color.White

//半透明白色
val translucentWhite = Color(0x80FFFFFF)

/**
 * [translucentWhite]透明无效果时使用
 */
val translucentWhiteFixBug = Color(0x33FFFFFF)

val background
    @Composable get() = 90.a2 withNight Color.Black

val onBackground
    @Composable get() = MaterialTheme.colors.onBackground

//dialog背景色
val dialogBackground
    @Composable get() = 94.a2 withNight 18.a2

//空图片背景色
val emptyImageBackground
    @Composable get() = 76.a2 withNight 50.a2

//容器颜色
val containerColor
    @Composable get() = 95.a2 withNight 70.a2

//bar专用颜色
val anyBarColor
    @Composable get() = 85.a2 withNight 20.a2

//bottomBarItem指示颜色
val barItemColor
    @Composable get() = 90.a2 withNight 25.a2
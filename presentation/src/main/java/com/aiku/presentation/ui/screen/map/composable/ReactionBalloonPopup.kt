import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.compose.ui.window.Popup
import com.aiku.core.R
import com.aiku.presentation.theme.Gray02

@Composable
fun ReactionBalloonPopup(
    onDismiss: () -> Unit,
    onReactionClick: (String) -> Unit
) {
    val density = LocalDensity.current
    val emojiIcons = listOf(
        R.drawable.ic_like,
        R.drawable.ic_hurry_up,
        R.drawable.ic_sorry
    )
    val emojiWidth = 40.dp
    val emojiHeight = 40.dp // Assume each emoji has the same height
    val horizontalPadding = 10.dp
    val verticalPadding = 10.dp

    val balloonWidth = emojiIcons.size * emojiWidth.value.dp + horizontalPadding * 2
    val balloonHeight = emojiHeight + verticalPadding * 2

    Popup(
        alignment = Alignment.TopCenter,
        offset = IntOffset(0, 40) // Popup 위치를 스티커 기준으로 조정
    ) {
        Box(
            modifier = Modifier.padding(8.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Canvas(
                modifier = Modifier.size(balloonWidth, balloonHeight)
            ) {
                val rectTop = 20.dp.toPx(density)
                val cornerRadius = 16.dp.toPx(density)

                // 둥근 사각형과 말풍선 꼬리 그리기
                val balloonPath = Path().apply {
                    // 둥근 사각형 본체
                    addRoundRect(
                        RoundRect(
                            rect = Rect(0f, rectTop, size.width, size.height),
                            cornerRadius = CornerRadius(cornerRadius, cornerRadius)
                        )
                    )

                    // 말풍선 꼬리
                    val tailWidth = 23.dp.toPx(density)
                    val tailHeight = 10.dp.toPx(density)
                    val tailStartX = size.width / 2 - tailWidth / 2
                    val tailEndX = size.width / 2 + tailWidth / 2

                    // 둥근 사각형과 말풍선 꼬리를 곡선으로 연결
                    moveTo(tailStartX, rectTop) // 말풍선 꼬리 시작점
                    cubicTo(
                        tailStartX + tailWidth * 0.2f, rectTop, // 제어점 1
                        size.width / 2 - tailWidth * 0.1f, rectTop - tailHeight * 0.8f, // 제어점 2
                        size.width / 2, rectTop - tailHeight // 끝점 (꼬리 끝 중앙)
                    )

                    // 오른쪽 곡선 (왼쪽 곡선을 대칭으로 뒤집음)
                    cubicTo(
                        size.width / 2 + tailWidth * 0.1f, rectTop - tailHeight * 0.8f, // 제어점 1
                        tailEndX - tailWidth * 0.2f, rectTop, // 제어점 2
                        tailEndX, rectTop // 끝점 (본체와 연결)
                    )
                    close()
                }

                // 외곽선 색상
                drawPath(
                    path = balloonPath,
                    color = Gray02,
                    style = Stroke(width = 3f, cap = StrokeCap.Round)
                )

                // 내부 색상
                drawPath(
                    path = balloonPath,
                    color = Color.White,
                    style = Fill
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = horizontalPadding, vertical = verticalPadding)
                    .size(balloonWidth, balloonHeight - 40.dp) // Subtract the padding for proper alignment
            ) {
                emojiIcons.forEach { iconRes ->
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(emojiWidth, emojiHeight)
                            .padding(horizontal = 5.dp)
                            .align(Alignment.CenterVertically)
                            .clickable {
                                onReactionClick(iconRes.toString())
                            }
                    )
                }
            }
        }
    }
}

// Dp를 Px로 변환하는 확장 함수
private fun Dp.toPx(density: Density): Float {
    return with(density) { this@toPx.toPx() }
}

@Preview(showBackground = true)
@Composable
private fun ReactionBalloonPopupPreview() {
    var showPopup by remember { mutableStateOf(false) }

    ReactionBalloonPopup(
        onDismiss = { showPopup = false },
        onReactionClick = { reaction ->
            showPopup = false
            println("Reaction selected: $reaction")
        }
    )
}

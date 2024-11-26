package br.senai.sp.jandira.touccanuser.utility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SpeechBubbleWithText(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.LightGray,
    cornerRadius: Dp = 16.dp,
    tipWidth: Dp = 16.dp,
    tipHeight: Dp = 12.dp,
    isLeftAligned: Boolean = true
) {
    Box(
        modifier = modifier
            .padding(vertical = 8.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val cornerPx = cornerRadius.toPx()
            val tipWidthPx = tipWidth.toPx()
            val tipHeightPx = tipHeight.toPx()

            val path = Path().apply {
                addRoundRect(
                    roundRect = RoundRect(
                        rect = Rect(
                            left = 0f,
                            top = 0f,
                            right = size.width,
                            bottom = size.height - tipHeightPx
                        ),
                        cornerRadius = CornerRadius(cornerPx, cornerPx)
                    )
                )
                if (isLeftAligned) {
                    moveTo(cornerPx, size.height - tipHeightPx)
                    lineTo(tipWidthPx / 2, size.height)
                    lineTo(tipWidthPx + cornerPx, size.height - tipHeightPx)
                } else {
                    moveTo(size.width - cornerPx - tipWidthPx, size.height - tipHeightPx)
                    lineTo(size.width - tipWidthPx / 2, size.height)
                    lineTo(size.width - cornerPx, size.height - tipHeightPx)
                }
                close()
            }

            drawPath(
                path = path,
                color = backgroundColor
            )

            clipPath(path) {
                // Placeholder para clipping, se necessário.
            }
        }

        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun ChatScreen(messages: List<Pair<String, Boolean>>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp) // Espaço entre os itens
    ) {
        items(messages.size) { index ->
            val (message, isLeftAligned) = messages[index]

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = if (isLeftAligned) Arrangement.Start else Arrangement.End
            ) {
                SpeechBubbleWithText(
                    text = message,
                    backgroundColor = if (isLeftAligned) Color(0xFFDCF8C6) else Color(0xFFEEEEEE),
                    isLeftAligned = isLeftAligned,
                    modifier = Modifier.widthIn(max = 280.dp) // Limita o tamanho do balão
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ChatScreenSamplePreview() {
    val messages = listOf(
        "Oi, tudo bem?" to true,
        "Tudo ótimo, e você?" to false,
        "Estou bem, obrigado!" to true,
        "Que bom ouvir isso!" to false
    )
    ChatScreen(messages = messages)
}

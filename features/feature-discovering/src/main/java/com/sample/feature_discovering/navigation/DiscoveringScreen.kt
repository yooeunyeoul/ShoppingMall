package com.sample.feature_discovering.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sample.feature_discovering.R
import com.sample.model.Topic
import com.sample.ui.theme.customFontFamily
import component.ToolBarButton

@Composable
internal fun DiscoveringRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    discoveringViewModel: DiscoveringViewModel = hiltViewModel()
) {
    val topic = Topic(title = "Discover", city = "서울")
    discoveringScreen(modifier = modifier, topic = topic, onBackClick)
}

@Composable
fun discoveringScreen(
    modifier: Modifier = Modifier, topic: Topic, onBackClick: () -> Unit = {}
) {
    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(44.dp))
        CustomTopAppBar(
            modifier = Modifier.padding(horizontal = 40.dp),
            startIcon = R.drawable.left_arrow, topic = topic, endIcon = R.drawable.setting_config
        )
    }
}

@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    startIcon: Int,
    topic: Topic,
    endIcon: Int
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        ToolBarButton(icon = startIcon) {

        }
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = topic.title,
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(
                text = topic.city,
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Thin,
                fontSize = 12.sp
            )
        }
        ToolBarButton(icon = endIcon) {

        }
    }

}

@Preview
@Composable
fun PreviewProgramsDiscoveringScreen() {
    discoveringScreen(topic = Topic(title = "타이틀", city = "서울"))
}
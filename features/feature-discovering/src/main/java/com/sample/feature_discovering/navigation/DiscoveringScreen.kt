package com.sample.feature_discovering.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sample.model.Topic
import com.sample.ui.theme.customFontFamily

@Composable
internal fun DiscoveringRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    discoveringViewModel: DiscoveringViewModel = hiltViewModel()
) {
    val topic = Topic(title = "Discover", city = "서울")
    DiscoveringScreen(modifier = modifier, topic = topic, onBackClick)
}

@Composable
fun DiscoveringScreen(
    modifier: Modifier = Modifier, topic: Topic, onBackClick: () -> Unit = {}
) {
    Column(Modifier.fillMaxSize()) {
        CustomTopAppBar(startContent = {}, topic = topic, endContent = {

        })
    }
}

@Composable
fun CustomTopAppBar(
    startContent: @Composable () -> Unit,
    topic: Topic,
    endContent: @Composable () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {

        startContent
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = topic.title, fontFamily = customFontFamily, fontWeight = FontWeight.Thin)
            Text(text = topic.city)
        }
        endContent
    }

}

@Preview
@Composable
fun PreviewProgramsDiscoveringScreen() {
    DiscoveringScreen(topic = Topic(title = "타이틀", city = "서울"))
}
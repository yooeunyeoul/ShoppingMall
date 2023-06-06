package component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sample.ui.theme.gray

@Composable
fun ToolBarButton(icon: Int, onclick: () -> Unit) {
    Card(
        Modifier
            .size(52.dp),
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        border = BorderStroke(1.dp, color = gray),
        colors = CardDefaults.cardColors(contentColor = White, containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        IconButton(onClick = { onclick }, modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
            )
        }
    }

}

package com.sample.shoppingmall.presentation.shoppingHome.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.common_utils.utils.NumberComma
import com.sample.domain.model.Category
import com.sample.shoppingmall.R
import com.sample.shoppingmall.presentation.components.CrossFadeImage

@Composable
fun CategoryItem(category: Category, onItemClick: (Category) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
    ) {

        CrossFadeImage(
            modifier = Modifier.height(170.dp),
            contentScale = ContentScale.Crop,
            url = category.imageUrl
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = category.brandName,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                HeartIcon(category = category, onItemClick = {category->
                    onItemClick(category)
                })
            }
            Spacer(modifier = Modifier.height(4.dp))

            Text(text = category.itemName, color = Color.Black, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(14.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = category.saleInfo.saleRate.toString() + "%", color = Color(0xFFEE6B42))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = NumberComma.decimalFormat.format(category.saleInfo.consumerPrice),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_heartfilled),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp)
                )
                Text(
                    text = NumberComma.decimalFormat.format(category.heartCount),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    painter = painterResource(id = android.R.drawable.star_off),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp)
                )
                Text(
                    text = category.reviewAveragePoint.toString(),
                    color = Color.Black
                )
                Text(
                    text = "(${category.reviewCount})",
                    color = Color.Black
                )
            }
        }


        Spacer(modifier = Modifier.height(24.dp))


    }
}

@Preview
@Composable
fun PreviewCategoryItem() {
    CategoryItem(category = Category()) {}
}

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
import com.sample.common_utils.utils.NumberComma
import com.sample.domain.model.Category
import com.sample.domain.model.SaleInfo
import com.sample.shoppingmall.R
import com.sample.shoppingmall.presentation.components.CrossFadeImage
import com.sample.shoppingmall.presentation.components.dpToSp

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
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = category.brandName,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = dpToSp(dp = 12.dp),
                    modifier = Modifier.weight(1f),
                )
//                Spacer(modifier = Modifier.weight(1f))
                HeartIcon(category = category, onItemClick = { category ->
                    onItemClick(category)
                })
            }
            Spacer(modifier = Modifier.height(4.dp))

            Text(text = category.itemName, color = Color.Black, fontSize = dpToSp(dp = 14.dp))

            Spacer(modifier = Modifier.height(14.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (category.saleInfo.totalSaleRate != 0) {
                    Text(
                        text = category.saleInfo.totalSaleRate.toString() + "%",
                        color = Color(0xFFEE6B42),
                        fontSize = dpToSp(dp = 16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))

                }

                Text(
                    text = NumberComma.decimalFormat.format(category.saleInfo.totalSellPrice),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = dpToSp(dp = 16.dp)
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
                    color = Color.Black,
                    fontSize = dpToSp(dp = 12.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    painter = painterResource(id = android.R.drawable.star_off),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp)
                )
                Text(
                    text = category.reviewAveragePoint.toString(),
                    color = Color.Black,
                    fontSize = dpToSp(dp = 12.dp)
                )
                Text(
                    text = "(${category.reviewCount})",
                    color = Color.Black,
                    fontSize = dpToSp(dp = 12.dp)
                )
            }
        }


        Spacer(modifier = Modifier.height(24.dp))


    }
}

@Preview
@Composable
fun PreviewCategoryItem() {
    CategoryItem(
        category = Category(
            brandName = "브랜드 네임 Long Long Long LongLong LongLong LongLong LongLong LongLong Long",
            heartCount = 1200,
            imageUrl = "https://img.29cm.co.kr//next-product//2020//11//18//de8724db0618460d9dc7a9a99125f8e7_20201118144729.jpg",
            itemName = "아이템 네임 Long LongLong LongLong LongLong LongLong LongLong LongLong LongLong Long",
            reviewAveragePoint = 5.5,
            itemNo = 10,
            reviewCount = 2000,
            saleInfo = SaleInfo(
                consumerPrice = 2000,
                totalSaleRate = 200,
                totalSellPrice = 20000
            )
        )
    ) {}
}

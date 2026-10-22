package com.example.stockdashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                StockDashboard()
            }
        }
    }
}

@Composable
fun StockDashboard() {
    val stockPriceFlow = remember {
        flow {
            var price = 100.0
            while (true) {
                price += Random.nextDouble(-2.0, 2.0)
                emit(price)
                delay(1000)
            }
        }
    }

    val price by stockPriceFlow.collectAsStateWithLifecycle(
        initialValue = 100.0
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Stock Trading Dashboard",
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Stock Price",
                fontSize = 18.sp
            )

            Text(
                text = "$${"%.2f".format(price)}",
                fontSize = 32.sp,
                color = if (price >= 100.0) Color.Green else Color.Red
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Updates every 1 second",
                fontSize = 14.sp
            )
        }
    }
}

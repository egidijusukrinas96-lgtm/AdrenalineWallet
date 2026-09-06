package com.adrenaline.wallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AdrenalineWalletApp() }
    }
}

@Composable
fun AdrenalineWalletApp() {
    var balance by remember { mutableDoubleStateOf(100.0) }
    var cycle by remember { mutableIntStateOf(1) }
    var status by remember { mutableStateOf("READY") }
    var lastPnL by remember { mutableDoubleStateOf(0.0) }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("ADRENALINE WALLET", style = MaterialTheme.typography.headlineMedium)
                Text("BTC • ETH • USDT • XRP")
                Text("Game balance: %.2f USDT".format(balance))
                Text("Cycle: $cycle")
                Text("Status: $status")
                Text("Last simulated P/L: %.2f USDT".format(lastPnL))

                Button(onClick = {
                    val pnl = Random.nextDouble(-20.0, 40.0)
                    lastPnL = pnl
                    if (pnl > 0) {
                        balance *= 3.0
                        status = "VICTORY — x3"
                    } else {
                        balance = 0.0
                        status = "DEATH — SIMULATION RESET"
                    }
                    cycle++
                }) {
                    Text("RUN 24H SURVIVAL TEST")
                }

                Text("Demo/testnet game layer. Real crypto is never automatically destroyed or confiscated.")
            }
        }
    }
}

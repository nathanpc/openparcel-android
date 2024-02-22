package com.innoveworkshop.openparcel.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.innoveworkshop.openparcel.R
import com.innoveworkshop.openparcel.model.Carrier
import com.innoveworkshop.openparcel.model.Parcel
import com.innoveworkshop.openparcel.model.ParcelUpdate
import com.innoveworkshop.openparcel.ui.theme.OpenparcelTheme
import com.innoveworkshop.openparcel.utils.DateUtil
import java.net.URI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenparcelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView(parcels = listOf())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(parcels: List<Parcel>) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = ""
                    )
                },
                navigationIcon = {
                     IconButton(onClick = { /* TODO: Open navigation drawer. */ }) {
                         Icon(
                             imageVector = Icons.Filled.Menu,
                             contentDescription = stringResource(id = R.string.options)
                         )
                     }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Do something. */ }) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = stringResource(id = R.string.add_parcel)
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(parcels) { parcel ->
                ParcelCard(parcel = parcel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    OpenparcelTheme {
        MainView(
            parcels = listOf(
                Parcel(
                    id = 0,
                    name = "Panasonic Lumix DMC-GF1",
                    delivered = false,
                    carrier = Carrier(
                        id = "ctt",
                        name = "CTT"
                    ),
                    color = Color(0xFFDE0024).toArgb(),
                    trackingCode = "CA767344619DE",
                    trackingUrl = URI.create("https://www.dhl.com/pt-pt/home/tracking.html?tracking-id=CA767344619DE&submit=1"),
                    trackingHistory = arrayOf(
                        ParcelUpdate(
                            title = "Order data transmitted electronically",
                            timestamp = DateUtil.fromISO8601("2024-02-19T07:29:00.000Z")
                        )
                    ),
                    lastUpdated = DateUtil.fromISO8601("2024-02-19T07:29:00.000Z")
                ),
                Parcel(
                    id = 0,
                    name = "Panasonic Lumix DMC-GF1",
                    delivered = false,
                    carrier = Carrier(
                        id = "ctt",
                        name = "CTT"
                    ),
                    color = Color(0xFFFFCC00).toArgb(),
                    trackingCode = "CA767344619DE",
                    trackingUrl = URI.create("https://www.dhl.com/pt-pt/home/tracking.html?tracking-id=CA767344619DE&submit=1"),
                    trackingHistory = arrayOf(
                        ParcelUpdate(
                            title = "Order data transmitted electronically",
                            timestamp = DateUtil.fromISO8601("2024-02-19T07:29:00.000Z")
                        )
                    ),
                    lastUpdated = DateUtil.fromISO8601("2024-02-19T07:29:00.000Z")
                ),
                Parcel(
                    id = 0,
                    name = "Panasonic Lumix DMC-GF1",
                    delivered = false,
                    carrier = Carrier(
                        id = "ctt",
                        name = "CTT"
                    ),
                    color = Color(0xFFDC1332).toArgb(),
                    trackingCode = "CA767344619DE",
                    trackingUrl = URI.create("https://www.dhl.com/pt-pt/home/tracking.html?tracking-id=CA767344619DE&submit=1"),
                    trackingHistory = arrayOf(
                        ParcelUpdate(
                            title = "Order data transmitted electronically",
                            timestamp = DateUtil.fromISO8601("2024-02-19T07:29:00.000Z")
                        )
                    ),
                    lastUpdated = DateUtil.fromISO8601("2024-02-19T07:29:00.000Z")
                )
            )
        )
    }
}
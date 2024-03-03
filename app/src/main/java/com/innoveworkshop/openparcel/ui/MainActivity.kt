package com.innoveworkshop.openparcel.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.innoveworkshop.openparcel.R
import com.innoveworkshop.openparcel.examples.parcelExamples
import com.innoveworkshop.openparcel.model.Parcel
import com.innoveworkshop.openparcel.ui.content.ParcelCard
import com.innoveworkshop.openparcel.ui.content.ParcelDetailSheet
import com.innoveworkshop.openparcel.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainViewPreview()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(parcels: List<Parcel>) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedParcel: Parcel? = null

    Scaffold(
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
                        contentDescription = "Open Parcel",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                    )
                },
                navigationIcon = {
                     IconButton(onClick = { /* TODO: Open navigation drawer. */ }) {
                         Icon(
                             imageVector = Icons.Filled.Menu,
                             contentDescription = stringResource(id = R.string.options),
                             tint = MaterialTheme.colorScheme.onPrimaryContainer
                         )
                     }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Open the Add Parcel screen. */ }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_parcel)
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            // Parcel cards list.
            items(parcels) { parcel ->
                ParcelCard(
                    parcel = parcel,
                    onClick = {
                        // Opens up the parcel detail bottom sheet.
                        selectedParcel = parcel
                        showBottomSheet = true
                    }
                )
            }
        }

        // Bottom sheet with parcel details.
        ParcelDetailSheet(
            parcel = selectedParcel,
            isVisible = showBottomSheet,
            onDismissRequest = {
                showBottomSheet = false
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainViewPreview() {
    AppTheme {
        MainView(parcelExamples)
    }
}

package com.innoveworkshop.openparcel.ui.content

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.innoveworkshop.openparcel.model.Parcel

/**
 * Bottom sheet that provides details regarding a parcel.
 *
 * @param parcel           Parcel to have its details displayed.
 * @param isVisible        Defines if the bottom sheet is shown or not.
 * @param onDismissRequest Dismissed request event handler.
 * @param sheetState       State of the bottom sheet.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParcelDetailSheet(
    parcel: Parcel?,
    modifier: Modifier = Modifier,
    isVisible: Boolean = false,
    onDismissRequest: () -> Unit = {},
    sheetState: SheetState = SheetState( true, Density(LocalContext.current))
) {
    if (isVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState
        ) {
            if (parcel != null) {
                ParcelDetail(
                    parcel = parcel,
                    modifier = Modifier.padding(horizontal = 16.dp).then(modifier)
                )
            }
        }
    }
}

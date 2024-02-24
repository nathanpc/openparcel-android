package com.innoveworkshop.openparcel.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ExitToApp
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

/**
 * Provides an action button that is commonly used in Bottom Sheets.
 *
 * @param text     Text that is displayed at the bottom of the button.
 * @param icon     Icon that is displayed at the top of the button.
 * @param onClick  Will be called when the user clicks the button.
 * @param modifier Component modifier attributes.
 */
@Composable
fun SheetActionButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .padding(vertical = 12.dp)
            .width(76.dp)
            .then(modifier)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = color,
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                color = color,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

/**
 * Row of sheet action buttons properly managed to be used in an actual bottom sheet.
 *
 * @param modifier Component modifier attributes.
 * @param content  Content of the row, ideally only composed of SheetActionButton components.
 */
@Composable
fun SheetActionButtonRow(
    modifier: Modifier = Modifier,
    content: @Composable (RowScope.() -> Unit)
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceEvenly,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun SingleSheetActionButtonPreview() {
    SheetActionButton(
        text = "Edit",
        icon = Icons.Rounded.Edit,
        onClick = { }
    )
}

@Preview(showBackground = true)
@Composable
fun SingleLongSheetActionButtonPreview() {
    SheetActionButton(
        text = "Move to archive",
        icon = Icons.AutoMirrored.Rounded.ExitToApp,
        onClick = { },
        modifier = Modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, heightDp = 300)
@Composable
fun SheetActionButtonPreview() {
    ModalBottomSheet(
        onDismissRequest = { },
        sheetState = SheetState(
            skipPartiallyExpanded = true,
            density = Density(LocalContext.current),
            initialValue = SheetValue.Expanded
        )
    ) {
        SheetActionButtonRow {
            SheetActionButton(
                text = "Add",
                icon = Icons.Rounded.Add,
                onClick = { }
            )
            SheetActionButton(
                text = "Edit",
                icon = Icons.Rounded.Edit,
                onClick = { }
            )
            SheetActionButton(
                text = "Delete",
                icon = Icons.Rounded.Delete,
                onClick = { }
            )
            SheetActionButton(
                text = "Share",
                icon = Icons.Rounded.Share,
                onClick = { }
            )
            SingleLongSheetActionButtonPreview()
        }
    }
}

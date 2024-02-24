package com.innoveworkshop.openparcel.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.innoveworkshop.openparcel.R
import com.innoveworkshop.openparcel.model.Parcel
import com.innoveworkshop.openparcel.model.ParcelUpdate

/**
 * Icon inside a circular surface.
 *
 * @param imageVector  Icon to be shown in the middle.
 * @param description  Content description for accessibility.
 * @param iconColor    Tint of the icon inside the bubble.
 * @param surfaceColor Color of the bubble's surface.
 * @param modifier     Component modifier attributes.
 */
@Composable
fun BubbleIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    description: String? = null,
    iconColor: Color = MaterialTheme.colorScheme.primary,
    surfaceColor: Color = MaterialTheme.colorScheme.primaryContainer
) {
    Surface(
        modifier = modifier,
        color = surfaceColor,
        shape = RoundedCornerShape(50),
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = description,
            tint = iconColor,
            modifier = Modifier.padding(8.dp)
        )
    }
}

/**
 * Parcel tracking history update icon bubble.
 *
 * @param parcel   The parcel to be displayed.
 * @param update   Tracking history update item.
 * @param modifier Component modifier attributes.
 */
@Composable
fun ParcelUpdateBubbleIcon(
    parcel: Parcel,
    update: ParcelUpdate,
    modifier: Modifier = Modifier
) {
    BubbleIcon(
        imageVector = update.status.getIcon(),
        description = update.status.label,
        iconColor = Color(parcel.onSurfaceColor()),
        surfaceColor = Color(parcel.onSurfaceDividerColor()),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun BubbleIconPreview() {
    Column {
        BubbleIcon(
            imageVector = ImageVector.vectorResource(R.drawable.local_shipping),
            modifier = Modifier.padding(8.dp)
        )

        BubbleIcon(
            imageVector = ImageVector.vectorResource(R.drawable.deployed_code_alert),
            modifier = Modifier.padding(8.dp)
        )

        BubbleIcon(
            imageVector = ImageVector.vectorResource(R.drawable.package_1),
            modifier = Modifier.padding(8.dp)
        )
    }
}

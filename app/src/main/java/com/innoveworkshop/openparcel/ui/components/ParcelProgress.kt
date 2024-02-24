package com.innoveworkshop.openparcel.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.innoveworkshop.openparcel.R
import com.innoveworkshop.openparcel.model.Carrier
import com.innoveworkshop.openparcel.model.Parcel
import com.innoveworkshop.openparcel.model.ParcelStatus
import com.innoveworkshop.openparcel.model.ParcelUpdate
import com.innoveworkshop.openparcel.utils.DateUtil
import java.net.URI

/**
 * A simple horizontal progress bar that shows the progress of a parcel.
 *
 * @param parcel     The parcel related to the progress to be displayed.
 * @param modifier   Component modifier attributes.
 * @param color      Color of the progress bar itself.
 * @Param trackColor Color of the background of the progress bar.
 */
@Composable
fun SimpleParcelProgress(
    parcel: Parcel,
    modifier: Modifier = Modifier,
    color: Color = Color(parcel.onSurfaceColor()),
    trackColor: Color = Color(parcel.onSurfaceDividerColor())
) {
    LinearProgressIndicator(
        progress = { parcel.progress },
        modifier = modifier,
        color = color,
        trackColor = trackColor,
        strokeCap = StrokeCap.Round,
    )
}

@Preview(showBackground = true)
@Composable
fun SimpleParcelProgressPreview() {
    SimpleParcelProgress(sampleParcel())
}

/**
 * A detailed progress bar with bubble caps with labels that show the progress of a parcel.
 *
 * @param parcel     The parcel related to the progress to be displayed.
 * @param modifier   Component modifier attributes.
 * @param color      Color of the progress bar itself.
 * @Param trackColor Color of the background of the progress bar.
 */
@Composable
fun DetailedParcelProgress(
    parcel: Parcel,
    modifier: Modifier = Modifier,
    color: Color = Color(parcel.onSurfaceDividerColor()),
    trackColor: Color = Color(parcel.surfaceColor())
) {
    Column(
        modifier = Modifier.then(modifier)
    ) {
        // Compound progress bar component.
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            // Left progress bubble cap.
            BubbleIcon(
                imageVector = ParcelStatus.POSTED.getIcon(),
                description = ParcelStatus.POSTED.label,
                iconColor = Color(parcel.onSurfaceColor()),
                surfaceColor = color,
                modifier = Modifier.offset(x = 6.dp)
            )

            // Actual progress bar.
            SimpleParcelProgress(
                parcel = parcel,
                color = color,
                trackColor = trackColor,
                modifier = Modifier
                    .weight(1f)
                    .height(12.dp)
            )

            // Right progress bubble cap.
            BubbleIcon(
                imageVector = ImageVector.vectorResource(id = R.drawable.home),
                description = ParcelStatus.DELIVERED.label,
                iconColor = Color(parcel.onSurfaceColor()),
                surfaceColor = if (parcel.progress == 1f) color else trackColor,
                modifier = Modifier.offset(x = -(6.dp))
            )
        }

        // Bottom labels.
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 2.dp)
        ) {
            val locations = getLocationStrings(parcel)

            Text(
                text = locations[0],
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 6.dp)
            )

            Text(
                text = locations[1],
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Right,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 6.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailedParcelProgressPreview() {
    Column(
        modifier = Modifier.fillMaxWidth(1f)
    ) {
        DetailedParcelProgress(
            parcel = sampleParcel(ParcelStatus.CREATED)
        )
        DetailedParcelProgress(
            parcel = sampleParcel(ParcelStatus.POSTED)
        )
        DetailedParcelProgress(
            parcel = sampleParcel(ParcelStatus.IN_TRANSIT)
        )
        DetailedParcelProgress(
            parcel = sampleParcel(ParcelStatus.CUSTOMS_CLEARED)
        )
        DetailedParcelProgress(
            parcel = sampleParcel(ParcelStatus.DELIVERY_ATTEMPT)
        )
        DetailedParcelProgress(
            parcel = sampleParcel(ParcelStatus.DELIVERING)
        )
        DetailedParcelProgress(
            parcel = sampleParcel(ParcelStatus.DELIVERED)
        )
    }
}

/**
 * Tries to get the origin and destination location strings.
 *
 * @return Array where the first element is the origin location and the second element is the
 *         destination.
 */
private fun getLocationStrings(parcel: Parcel): Array<String> {
    val locations = arrayOf("Origin", "Destination")

    // Get locations from the parcel.
    var origin = parcel.origin?.shortLocation()
    var destination = parcel.destination?.shortLocation()

    if (origin != null) {
        if (origin == destination) {
            // Try again but going from a different direction, this may be a proper full address.
            origin = parcel.origin?.shortLocation(false)
            destination = parcel.destination?.shortLocation(false)

            if (origin != destination)
                locations[0] = origin!!
        } else {
            // Perfect! The locations are already different.
            locations[0] = origin
        }
    }

    if (destination != null)
        locations[1] = destination

    return locations
}

/**
 * Creates a sample parcel to use in the previews.
 *
 * @param status Which status should this sample parcel be mimicking.
 *
 * @return An example of a Parcel object.
 */
private fun sampleParcel(status: ParcelStatus = ParcelStatus.POSTED): Parcel {
    return Parcel(
        id = 0,
        name = "Panasonic Lumix DMC-GF1",
        delivered = false,
        carrier = Carrier(
            id = "dhl",
            name = "DHL"
        ),
        color = Color(0xFFFFCC00).toArgb(),
        trackingCode = "CA767344619DE",
        trackingUrl = URI.create("https://www.dhl.com/pt-pt/home/tracking.html?tracking-id=CA767344619DE&submit=1"),
        trackingHistory = arrayOf(
            ParcelUpdate(
                title = "Order data transmitted electronically",
                timestamp = DateUtil.fromISO8601("2024-02-19T07:29:00.000Z"),
                status = status
            )
        ),
        lastUpdated = DateUtil.fromISO8601("2024-02-19T07:29:00.000Z")
    )
}

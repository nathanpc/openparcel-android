package com.innoveworkshop.openparcel.ui.components

import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
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
        strokeCap = StrokeCap.Round
    )
}

@Preview(showBackground = true)
@Composable
fun SimpleParcelProgressPreview() {
    SimpleParcelProgress(sampleParcel())
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

package com.innoveworkshop.openparcel.model

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.android.material.color.MaterialColors
import com.innoveworkshop.openparcel.utils.DateUtil
import org.json.JSONObject
import java.net.URI
import java.util.Date

/**
 * Full information about a shipped item.
 */
data class Parcel(
    val id: Int,
    val name: String,
    val delivered: Boolean,
    val carrier: Carrier,
    @ColorInt val color: Int,
    val trackingCode: String,
    val trackingUrl: URI,
    val trackingHistory: Array<ParcelUpdate>,
    val creationDate: Date? = null,
    val lastUpdated: Date,
    val origin: Location? = null,
    val destination: Location? = null
) {
    companion object {
        /**
         * Constructs the parcel object from a JSON string.
         *
         * @param jsonStr Parcel JSON encoded as a string.
         *
         * @return Fully populated parcel object.
         */
        fun fromJson(jsonStr: String): Parcel {
            val json: JSONObject = JSONObject(jsonStr)

            // Check if we have a creation date.
            var creationDate: Date? = null
            if (!json.isNull("creationDate"))
                creationDate = DateUtil.fromISO8601(json.getString("creationDate"))

            // Build up the parcel object.
            return Parcel(
                id = json.getInt("id"),
                name = json.getString("name"),
                delivered = json.optBoolean("delivered"),
                carrier = Carrier.fromJson(json.getJSONObject("carrier")),
                color = Color.parseColor(json.getString("accentColor")),
                trackingCode = json.getString("trackingCode"),
                trackingUrl = URI(json.getString("trackingUrl")),
                trackingHistory = ParcelUpdate.fromJsonList(json.getJSONArray("history")),
                creationDate = creationDate,
                lastUpdated = DateUtil.fromISO8601(json.getString("lastUpdated")),
                origin = Location.fromJson(json.getJSONObject("origin")),
                destination = Location.fromJson(json.getJSONObject("destination"))
            )
        }
    }

    /**
     * The current progress of the parcel, from creation to delivery.
     */
    val progress: Float get() {
        for (update in trackingHistory) {
            if (update.status != null)
                return update.status.progress
        }

        return 0f
    }

    /**
     * Gets the Material surface color associated with this parcel's carrier.
     */
    @Composable
    @ColorInt
    fun surfaceColor(): Int {
        return MaterialColors.getColorRoles(
            LocalContext.current,
            this.color
        ).accentContainer
    }

    /**
     * Gets the Material on surface accent color associated with this parcel's carrier.
     */
    @Composable
    @ColorInt
    fun onSurfaceColor(): Int {
        return MaterialColors.getColorRoles(
            LocalContext.current,
            this.color
        ).onAccentContainer
    }

    /**
     * Gets the Material surface divider color associated with this parcel's carrier.
     */
    @Composable
    @ColorInt
    fun onSurfaceDividerColor(): Int {
        return MaterialColors.layer(
            this.surfaceColor(),
            MaterialColors.getColorRoles(LocalContext.current, this.color).onAccentContainer,
            .2f
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Parcel

        return trackingCode == other.trackingCode
    }

    override fun hashCode(): Int {
        return trackingCode.hashCode()
    }
}

package com.innoveworkshop.openparcel.model

import com.innoveworkshop.openparcel.utils.DateUtil
import com.innoveworkshop.openparcel.utils.JsonUtil
import org.json.JSONArray
import org.json.JSONObject
import java.time.Instant
import java.util.Date

/**
 * Tracking history update item.
 */
data class ParcelUpdate(
    val title: String,
    val description: String? = null,
    val timestamp: Date,
    val location: Location? = null,
    val status: ParcelStatus = ParcelStatus.IN_TRANSIT
) {
    companion object {
        /**
         * Constructs the parcel update history item object from a JSON object.
         *
         * @param json              Parcel update history item object JSON.
         * @param lastProgressValue Last progress value to be used as filler.
         *
         * @return Fully populated parcel update history item object.
         */
        fun fromJson(json: JSONObject, lastProgressValue: Float = 0f): ParcelUpdate = ParcelUpdate(
            title = json.getString("title"),
            description = JsonUtil.getStringOrNull(json, "description"),
            timestamp = DateUtil.fromISO8601(json.getString("timestamp")),
            location = Location.fromJson(json.getJSONObject("location")),
            status = if (json.isNull("status"))
                ParcelStatus.IN_TRANSIT.withProgress(lastProgressValue) else
                ParcelStatus.fromJson(json.getJSONObject("status"))
        )

        /**
         * Constructs a list of parcel update history items from a JSON array.
         *
         * @param jsonArray JSON array of parcel update history item objects.
         *
         * @return List of fully populated parcel update history item objects.
         */
        fun fromJsonList(jsonArray: JSONArray): Array<ParcelUpdate> {
            val array = ArrayList<ParcelUpdate>()
            var lastProgressValue: Float = .7f

            for (index in 0 until jsonArray.length()) {
                // Set the last progress value for the In Transit status.
                if (index > 0) {
                    val status = array.last().status

                    if (status == ParcelStatus.IN_TRANSIT) {
                        // Should copy the last In Transit progress value.
                        lastProgressValue = status.progress
                    } else if (status.progress <= .8f) {
                        // It's a new In Transit status, so decrease 10% from the progress.
                        lastProgressValue = status.progress - .1f
                    }

                    // Don't allow an In Transit progress value to be less than its default.
                    if (status.progress < ParcelStatus.IN_TRANSIT.progress) {
                        lastProgressValue = ParcelStatus.IN_TRANSIT.progress
                    }
                }

                // Add the update history item to the array.
                array.add(fromJson(jsonArray.getJSONObject(index), lastProgressValue))
            }

            return array.toArray(arrayOf(ParcelUpdate(title = "",
                timestamp = Date.from(Instant.now()))))
        }
    }
}

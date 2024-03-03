package com.innoveworkshop.openparcel.model

import android.content.Context
import android.icu.text.SimpleDateFormat
import com.innoveworkshop.openparcel.utils.DateUtil
import com.innoveworkshop.openparcel.utils.JsonUtil.Companion.getObjectOrNull
import com.innoveworkshop.openparcel.utils.JsonUtil.Companion.getStringOrNull
import org.json.JSONArray
import org.json.JSONObject
import java.time.Instant
import java.util.Date
import java.util.Locale

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
            description = json.getStringOrNull("description"),
            timestamp = DateUtil.fromISO8601(json.getString("timestamp")),
            location = Location.fromJson(json.getObjectOrNull("location")),
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
            var lastProgressValue = .7f

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

    /**
     * Extracts the hours and minutes from the timestamp and constructs an HH:MM string.
     *
     * @param context Current application context.
     *
     * @return The usual HH:MM string from the timestamp.
     */
    fun getTimeString(context: Context? = null): String {
        // Get the locale.
        var locale = Locale.ENGLISH
        if (context != null)
            locale = DateUtil.getCurrentLocale(context)

        return SimpleDateFormat("HH:mm", locale).format(timestamp)
    }

    /**
     * Gets a string that contains the day as a number and the month as text.
     *
     *
     * @param context Current application context.
     *
     * @return The usual "dd MMMMM" string from the timestamp.
     */
    fun getDayMonthString(context: Context? = null): String {
        // Get the locale.
        var locale = Locale.ENGLISH
        if (context != null)
            locale = DateUtil.getCurrentLocale(context)

        return SimpleDateFormat("dd MMMM", locale).format(timestamp)
    }
}

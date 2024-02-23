package com.innoveworkshop.openparcel.model

import com.innoveworkshop.openparcel.utils.JsonUtil
import org.json.JSONObject

/**
 * Abstraction of the location of an item.
 */
data class Location(
    val addressLine: String? = null,
    val city: String? = null,
    val state: String? = null,
    val postalCode: String? = null,
    val country: String? = null,
    val searchQuery: String? = null,
    val coords: GeoCoords? = null
) {
    companion object {
        /**
         * Constructs the location object from a JSON object.
         *
         * @param json Location object encoded as a JSON object.
         *
         * @return Fully populated location object.
         */
        fun fromJson(json: JSONObject?): Location? {
            if (json == null)
                return null

            val location = Location(
                addressLine = JsonUtil.getStringOrNull(json, "addressLine"),
                city = JsonUtil.getStringOrNull(json, "city"),
                state = JsonUtil.getStringOrNull(json, "state"),
                postalCode = JsonUtil.getStringOrNull(json, "postalCode"),
                country = JsonUtil.getStringOrNull(json, "country"),
                searchQuery = JsonUtil.getStringOrNull(json, "searchQuery"),
                coords = GeoCoords.fromJson(json.getJSONObject("coords"))
            )

            if (location.isNull())
                return null

            return location
        }
    }

    /**
     * Progressively tries to find a short location string that represents this object. This is
     * usually used for small labels that must identify where the parcel is at.
     *
     * @param ascendingPriority Prioritize the micro scale before the macro scale locations?
     *
     * @return Short location string or null if all fields are null.
     */
    fun shortLocation(ascendingPriority: Boolean = true): String? {
        // Check if the entire location is contained in the address line.
        if ((addressLine != null) and (city == null) and (state == null) and (country == null))
            return addressLine

        // Progressively try to find a suitable location string.
        if (ascendingPriority) {
            // Micro to macro scale.
            if (city != null) {
                return city
            } else if (state != null) {
                return state
            } else if (country != null) {
                return country
            }
        } else {
            // Macro to micro scale.
            if (country != null) {
                return country
            } else if (state != null) {
                return state
            } else if (city != null) {
                return city
            }
        }

        return null
    }

    /**
     * Checks if the entire object is null and essentially useless.
     *
     * @return True if the object is essentially useless.
     */
    fun isNull(): Boolean {
        return (addressLine == null) and (city == null) and (state == null) and
                (postalCode == null) and (country == null) and (searchQuery == null) and
                (coords == null)
    }

    /**
     * Representation of geographical coordinates.
     */
    data class GeoCoords(
        val latitude: Float,
        val longitude: Float
    ) {
        companion object {
            /**
             * Constructs the geographical coordinates object from a JSON object.
             *
             * @param json Geographical coordinates object encoded as a JSON object.
             *
             * @return Fully populated geographical coordinates object.
             */
            fun fromJson(json: JSONObject): GeoCoords? {
                // Check if we even have the data to build an object from.
                if (json.isNull("lat") or json.isNull("lng"))
                    return null

                return GeoCoords(
                    latitude = json.getDouble("lat").toFloat(),
                    longitude = json.getDouble("lng").toFloat()
                )
            }
        }
    }
}

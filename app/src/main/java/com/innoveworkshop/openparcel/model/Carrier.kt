package com.innoveworkshop.openparcel.model

import org.json.JSONObject

/**
 * Abstraction of the carrier information of a parcel.
 */
data class Carrier(
    val id: String,
    val name: String
) {
    companion object {
        /**
         * Constructs the carrier object from a JSON object.
         *
         * @param json Carrier object as JSON.
         *
         * @return Fully populated carrier object.
         */
        fun fromJson(json: JSONObject): Carrier {
            return Carrier(
                id = json.getString("id"),
                name = json.getString("name")
            )
        }
    }
}

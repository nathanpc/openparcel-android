package com.innoveworkshop.openparcel.utils

import org.json.JSONObject

/**
 * A collection of utilities to work with JSON objects.
 */
class JsonUtil {
    companion object {
        /**
         * Gets a string value from a JSON object or null if it's null or doesn't exist.
         *
         * @param json JSON object to get the value from.
         * @param key  Key to the value in the object.
         *
         * @return A string value associated with the key or null if it's null or doesn't exist.
         */
        fun getStringOrNull(json: JSONObject, key: String): String? {
            if (json.has(key) or json.isNull(key))
                return null

            return json.getString(key)
        }

        /**
         * Gets an object value from a JSON object or null if it's null or doesn't exist.
         *
         * @param json JSON object to get the value from.
         * @param key  Key to the value in the object.
         *
         * @return An object value associated with the key or null if it's null or doesn't exist.
         */
        fun getObjectOrNull(json: JSONObject, key: String): JSONObject? {
            if (json.has(key) or json.isNull(key))
                return null

            return json.getJSONObject(key)
        }
    }
}

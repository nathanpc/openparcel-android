package com.innoveworkshop.openparcel.utils

import org.json.JSONObject

/**
 * A collection of utilities to work with JSON objects.
 */
class JsonUtil {
    companion object {
        /**
         * Gets the string value from the object or null if it's null or doesn't exist.
         *
         * @param key  Key to the value in the object.
         *
         * @return String value associated with the key or null if it's null or doesn't exist.
         */
        fun JSONObject.getStringOrNull(key: String): String? {
            if (!this.has(key) or this.isNull(key))
                return null

            return this.getString(key)
        }

        /**
         * Gets the object value from the parent object or null if it's null or doesn't exist.
         *
         * @param key  Key to the value in the object.
         *
         * @return Object value associated with the key or null if it's null or doesn't exist.
         */
        fun JSONObject.getObjectOrNull(key: String): JSONObject? {
            if (!this.has(key) or this.isNull(key))
                return null

            return this.getJSONObject(key)
        }
    }
}

package com.innoveworkshop.openparcel.utils

import android.text.format.DateUtils
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Date

/**
 * A collection of simple utilities for working with dates.
 */
class DateUtil {
    companion object {
        /**
         * Gets a date object from an ISO8601 string.
         *
         * @param dateStr ISO8601 string.
         *
         * @return Date object.
         */
        fun fromISO8601(dateStr: String): Date {
            return Date.from(Instant.from(DateTimeFormatter.ISO_INSTANT.parse(dateStr)))
        }

        /**
         * Gets a human-readable representation of a relative timeframe.
         *
         * @param date      The timestamp to compare with the reference.
         * @param reference The reference used for the relative comparison. Defaults to now.
         *
         * @return A human-readable representation of a relative timeframe.
         */
        fun getRelativeTimeString(date: Date, reference: Date = Date.from(Instant.now())): String {
            return DateUtils.getRelativeTimeSpanString(
                date.time, reference.time, 0).toString()
        }
    }
}

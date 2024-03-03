package com.innoveworkshop.openparcel.utils

import android.content.Context
import android.text.format.DateUtils
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import java.util.Calendar
import java.util.Date
import java.util.Locale

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
        fun fromISO8601(dateStr: String): Date = Date.from(Instant.parse(dateStr).toJavaInstant())

        /**
         * Gets a human-readable representation of a relative timeframe.
         *
         * @param date      The timestamp to compare with the reference.
         * @param reference The reference used for the relative comparison. Defaults to now.
         *
         * @return A human-readable representation of a relative timeframe.
         */
        fun getRelativeTimeString(date: Date, reference: Date = Date.from(Clock.System.now().toJavaInstant())): String =
            DateUtils.getRelativeTimeSpanString(date.time, reference.time, 0).toString()

        /**
         * Gets the current default locale set by the user.
         *
         * @param context Current application context.
         *
         * @return The current default locale set by the user.
         */
        fun getLocale(context: Context): Locale = context.resources.configuration.locales[0]

        /**
         * Checks if a date is different from another.
         *
         * @param calendar Calendar object to compare with.
         * @param date     Date object to compare with.
         *
         * @return True if the day, month, or year are different between the two objects.
         */
        fun isDateDifferent(calendar: Calendar, date: Date): Boolean {
            val other: Calendar = Calendar.getInstance()
            other.time = date

            return (calendar.get(Calendar.DAY_OF_MONTH) != other.get(Calendar.DAY_OF_MONTH)) or
                    (calendar.get(Calendar.MONTH) != other.get(Calendar.MONTH)) or
                    (calendar.get(Calendar.YEAR) != other.get(Calendar.YEAR))
        }
    }
}

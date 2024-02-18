package com.innoveworkshop.openparcel.model

import androidx.annotation.ColorInt
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.android.material.color.MaterialColors
import java.net.URI

data class Parcel(
    val name: String,
    val delivered: Boolean,
    @ColorInt val color: Int,
    val trackingCode: String,
    val trackingUrl: URI,
    val trackingHistory: Array<ParcelUpdate>
) {
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

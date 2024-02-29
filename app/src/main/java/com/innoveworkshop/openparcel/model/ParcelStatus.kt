package com.innoveworkshop.openparcel.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.innoveworkshop.openparcel.R
import com.innoveworkshop.openparcel.ui.theme.AppTheme
import com.innoveworkshop.openparcel.ui.theme.ExtendedTheme
import com.innoveworkshop.openparcel.ui.theme.HueBasedTheme
import com.innoveworkshop.openparcel.utils.JsonUtil
import org.json.JSONObject

enum class ParcelStatus(
    val type: String,
    var progress: Float,
    val label: String,
    var data: JSONObject? = null,
    val importance: Importance = Importance.IMPORTANT
) {
    CREATED("created", .1f, "Tracking code created") {
        @Composable
        override fun getIcon(): ImageVector =
            ImageVector.vectorResource(id = R.drawable.deployed_code_update)

        @Composable
        override fun CardTheme(content: @Composable() () -> Unit): Unit =
            HueBasedTheme(hue = 50f, content = content)
    },
    POSTED("posted", .2f, "Item posted") {
        @Composable
        override fun getIcon(): ImageVector =
            ImageVector.vectorResource(id = R.drawable.local_post_office)

        @Composable
        override fun CardTheme(content: @Composable() () -> Unit): Unit =
            HueBasedTheme(hue = 50f, content = content)
    },
    IN_TRANSIT(
        "in-transit", .35f, "In transit",
        importance = Importance.REGULAR
    ) {
        @Composable
        override fun getIcon(): ImageVector =
            ImageVector.vectorResource(id = R.drawable.local_shipping)
    },
    CUSTOMS_CLEARED("customs-cleared", .5f, "Cleared customs") {
        @Composable
        override fun getIcon(): ImageVector =
            ImageVector.vectorResource(id = R.drawable.local_police)

        @Composable
        override fun CardTheme(content: @Composable() () -> Unit): Unit =
            HueBasedTheme(hue = 220f, content = content)
    },
    DELIVERY_ATTEMPT("delivery-attempt", .8f, "Attempted delivery") {
        @Composable
        override fun getIcon(): ImageVector =
            ImageVector.vectorResource(id = R.drawable.deployed_code_alert)

        @Composable
        override fun CardTheme(content: @Composable() () -> Unit): Unit =
            HueBasedTheme(hue = 0f, content = content)
    },
    WAITING_PICKUP("pickup", .9f, "Waiting for pickup") {
        @Composable
        override fun getIcon(): ImageVector = ImageVector.vectorResource(id = R.drawable.pallet)

        @Composable
        override fun CardTheme(content: @Composable() () -> Unit): Unit =
            HueBasedTheme(hue = 185f, content = content)
    },
    DELIVERING("delivering", .9f, "Delivery in progress") {
        @Composable
        override fun getIcon(): ImageVector =
            ImageVector.vectorResource(id = R.drawable.deployed_code_account)

        @Composable
        override fun CardTheme(content: @Composable() () -> Unit): Unit =
            HueBasedTheme(hue = 65f, content = content)
    },
    DELIVERED("delivered", 1f, "Delivered") {
        @Composable
        override fun getIcon(): ImageVector = ImageVector.vectorResource(id = R.drawable.package_1)

        @Composable
        override fun CardTheme(content: @Composable() () -> Unit): Unit =
            HueBasedTheme(hue = 110f, content = content)
    },
    ISSUE(
        "issue", .5f, "Problem occurred",
        importance = Importance.URGENT
    ) {
        @Composable
        override fun getIcon(): ImageVector = Icons.Rounded.Warning

        @Composable
        override fun CardTheme(content: @Composable() () -> Unit): Unit =
            HueBasedTheme(hue = 335f, content = content)
    };

    companion object {
        /**
         * Constructs the parcel update status object from a JSON object.
         *
         * @param json Parcel update status object JSON.
         *
         * @return Fully populated parcel update status object.
         */
        fun fromJson(json: JSONObject): ParcelStatus = fromType(
            type = json.getString("type"),
            data = JsonUtil.getObjectOrNull(json, "data")
        )

        /**
         * Gets the appropriate item in the enum based on a type string.
         *
         * @param type Type ID string related to an item in the enum.
         * @param data Optional complex data structure related to the status.
         *
         * @return Appropriate item in the enum.
         */
        fun fromType(type: String, data: JSONObject? = null): ParcelStatus {
            var status = IN_TRANSIT
            try {
                status = entries.first { it.type == type }
            } catch (_: NoSuchElementException) { }

            if (data != null)
                status.data = data

            return status
        }
    }

    /**
     * Gets the icon associated with this parcel status.
     *
     * @return Icon representing this parcel status.
     */
    @Composable
    abstract fun getIcon(): ImageVector

    /**
     * Gets the Card colors associated with this parcel status.
     *
     * @return Card color for this parcel status.
     */
    @Composable
    open fun CardTheme(content: @Composable() () -> Unit): Unit = AppTheme(content = content)

    /**
     * Gets a parcel status with a specific progress value set.
     *
     * @param progress Progress value to override the default one.
     *
     * @return Parcel status object with a specific progress value.
     */
    fun withProgress(progress: Float): ParcelStatus {
        this.progress = progress
        return this
    }

    override fun toString(): String {
        return this.label
    }

    /**
     * Level of importance of an update status.
     */
    enum class Importance(
        val level: Int
    ) {
        IGNORED(0),
        REGULAR(1),
        IMPORTANT(2),
        URGENT(3);
    }
}

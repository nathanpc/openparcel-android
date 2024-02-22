package com.innoveworkshop.openparcel.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.innoveworkshop.openparcel.R
import com.innoveworkshop.openparcel.utils.JsonUtil
import org.json.JSONObject

enum class ParcelStatus(
    val type: String,
    var progress: Float,
    val label: String,
    var data: JSONObject? = null
) {
    CREATED("created", .1f, "Tracking code created") {
        @Composable
        override fun getIcon(): ImageVector {
            return ImageVector.vectorResource(id = R.drawable.deployed_code_update)
        }
    },
    POSTED("posted", .2f, "Item posted") {
        @Composable
        override fun getIcon(): ImageVector {
            return ImageVector.vectorResource(id = R.drawable.local_post_office)
        }
    },
    IN_TRANSIT("in-transit", .35f, "In transit") {
        @Composable
        override fun getIcon(): ImageVector {
            return ImageVector.vectorResource(id = R.drawable.local_shipping)
        }
    },
    CUSTOMS_CLEARED("customs-cleared", .5f, "Cleared customs") {
        @Composable
        override fun getIcon(): ImageVector {
            return ImageVector.vectorResource(id = R.drawable.local_police)
        }
    },
    DELIVERY_ATTEMPT("delivery-attempt", .8f, "Attempted delivery") {
        @Composable
        override fun getIcon(): ImageVector {
            return ImageVector.vectorResource(id = R.drawable.deployed_code_alert)
        }
    },
    WAITING_PICKUP("pickup", .9f, "Waiting for pickup") {
        @Composable
        override fun getIcon(): ImageVector {
            return ImageVector.vectorResource(id = R.drawable.pallet)
        }
    },
    DELIVERING("delivering", .9f, "Delivery in progress") {
        @Composable
        override fun getIcon(): ImageVector {
            return ImageVector.vectorResource(id = R.drawable.deployed_code_account)
        }
    },
    DELIVERED("delivered", 1f, "Delivered") {
        @Composable
        override fun getIcon(): ImageVector {
            return ImageVector.vectorResource(id = R.drawable.package_1)
        }
    },
    ISSUE("issue", .5f, "Problem occurred") {
        @Composable
        override fun getIcon(): ImageVector {
            return Icons.Rounded.Warning
        }
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
}

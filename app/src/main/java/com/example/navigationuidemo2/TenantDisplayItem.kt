package com.example.navigationuidemo2

/**
 * Data class for displaying tenant information in the dashboard
 */
data class TenantDisplayItem(
    val name: String,
    val unit: String,
    val rent: String
) {
    fun getFormattedRent(): String {
        return try {
            val rentValue = rent.toDouble()
            "$${String.format("%.2f", rentValue)}"
        } catch (e: NumberFormatException) {
            "$$rent"
        }
    }
}

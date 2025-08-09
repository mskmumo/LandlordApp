package com.example.navigationuidemo2

import com.google.firebase.firestore.DocumentId
import java.util.Date

data class Tenant(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val propertyId: String = "",
    val rentAmount: Double = 0.0,
    val leaseStartDate: Date? = null,
    val leaseEndDate: Date? = null,
    val securityDeposit: Double = 0.0,
    val isActive: Boolean = true,
    val createdAt: Date = Date()
)

data class Property(
    @DocumentId
    val id: String = "",
    val address: String = "",
    val city: String = "",
    val state: String = "",
    val zipCode: String = "",
    val propertyType: String = "", // apartment, house, condo, etc.
    val bedrooms: Int = 0,
    val bathrooms: Double = 0.0,
    val squareFeet: Int = 0,
    val monthlyRent: Double = 0.0,
    val isOccupied: Boolean = false,
    val currentTenantId: String = "",
    val createdAt: Date = Date()
)

data class Payment(
    @DocumentId
    val id: String = "",
    val tenantId: String = "",
    val propertyId: String = "",
    val amount: Double = 0.0,
    val paymentDate: Date = Date(),
    val dueDate: Date = Date(),
    val paymentType: String = "", // rent, deposit, late_fee, etc.
    val paymentMethod: String = "", // cash, check, bank_transfer, etc.
    val status: String = "", // paid, pending, overdue
    val notes: String = "",
    val createdAt: Date = Date()
)

data class DashboardStats(
    val totalProperties: Int = 0,
    val occupiedProperties: Int = 0,
    val totalTenants: Int = 0,
    val monthlyRentCollected: Double = 0.0,
    val pendingPayments: Double = 0.0,
    val overduePayments: Double = 0.0
)

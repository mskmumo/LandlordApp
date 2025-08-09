package com.example.navigationuidemo2

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirestoreService {
    private val db: FirebaseFirestore = Firebase.firestore

    // Add a new tenant
    suspend fun addTenant(tenant: Tenant): Result<String> {
        return try {
            val documentRef = db.collection("tenants").add(tenant).await()
            Result.success(documentRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Get all tenants
    suspend fun getAllTenants(): Result<List<Tenant>> {
        return try {
            val snapshot = db.collection("tenants").get().await()
            val tenants = snapshot.documents.mapNotNull { document ->
                document.toObject(Tenant::class.java)?.copy(id = document.id)
            }
            Result.success(tenants)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Update tenant
    suspend fun updateTenant(tenantId: String, tenant: Tenant): Result<Unit> {
        return try {
            db.collection("tenants").document(tenantId).set(tenant).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Delete tenant
    suspend fun deleteTenant(tenantId: String): Result<Unit> {
        return try {
            db.collection("tenants").document(tenantId).delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Add a property
    suspend fun addProperty(property: Property): Result<String> {
        return try {
            val documentRef = db.collection("properties").add(property).await()
            Result.success(documentRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Get all properties
    suspend fun getAllProperties(): Result<List<Property>> {
        return try {
            val snapshot = db.collection("properties").get().await()
            val properties = snapshot.documents.mapNotNull { document ->
                document.toObject(Property::class.java)?.copy(id = document.id)
            }
            Result.success(properties)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Add a payment record
    suspend fun addPayment(payment: Payment): Result<String> {
        return try {
            val documentRef = db.collection("payments").add(payment).await()
            Result.success(documentRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Get payments for a specific tenant
    suspend fun getPaymentsByTenant(tenantId: String): Result<List<Payment>> {
        return try {
            val snapshot = db.collection("payments")
                .whereEqualTo("tenantId", tenantId)
                .get()
                .await()
            val payments = snapshot.documents.mapNotNull { document ->
                document.toObject(Payment::class.java)?.copy(id = document.id)
            }
            Result.success(payments)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Get all payments
    suspend fun getAllPayments(): Result<List<Payment>> {
        return try {
            val snapshot = db.collection("payments").get().await()
            val payments = snapshot.documents.mapNotNull { document ->
                document.toObject(Payment::class.java)?.copy(id = document.id)
            }
            Result.success(payments)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

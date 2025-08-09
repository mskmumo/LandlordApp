package com.example.navigationuidemo2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.util.Date

class FirestoreTestActivity : AppCompatActivity() {
    private lateinit var firestoreService: FirestoreService
    private lateinit var statusText: TextView
    private lateinit var testButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Create simple layout programmatically for testing
        val layout = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }
        
        statusText = TextView(this).apply {
            text = "Ready to test Firestore connection"
            textSize = 16f
            setPadding(0, 0, 0, 32)
        }
        
        testButton = Button(this).apply {
            text = "Test Firestore Connection"
            setOnClickListener { testFirestoreConnection() }
        }
        
        layout.addView(statusText)
        layout.addView(testButton)
        setContentView(layout)
        
        firestoreService = FirestoreService()
    }
    
    private fun testFirestoreConnection() {
        statusText.text = "Testing Firestore connection..."
        testButton.isEnabled = false
        
        lifecycleScope.launch {
            try {
                // Test 1: Add a test tenant
                val testTenant = Tenant(
                    name = "Test Tenant",
                    email = "test@example.com",
                    phone = "123-456-7890",
                    rentAmount = 1200.0,
                    isActive = true,
                    createdAt = Date()
                )
                
                val addResult = firestoreService.addTenant(testTenant)
                if (addResult.isSuccess) {
                    val tenantId = addResult.getOrNull()
                    var resultText = "✅ Successfully added test tenant with ID: $tenantId\n"
                    Log.d("FirestoreTest", "Added tenant with ID: $tenantId")
                    
                    // Test 2: Retrieve all tenants
                    val getAllResult = firestoreService.getAllTenants()
                    if (getAllResult.isSuccess) {
                        val tenants = getAllResult.getOrNull() ?: emptyList()
                        resultText += "✅ Successfully retrieved ${tenants.size} tenant(s)\n"
                        Log.d("FirestoreTest", "Retrieved ${tenants.size} tenants")
                        
                        // Test 3: Add a test property
                        val testProperty = Property(
                            address = "123 Test Street",
                            city = "Test City",
                            state = "TS",
                            zipCode = "12345",
                            propertyType = "apartment",
                            bedrooms = 2,
                            bathrooms = 1.0,
                            monthlyRent = 1200.0,
                            isOccupied = false,
                            createdAt = Date()
                        )
                        
                        val propertyResult = firestoreService.addProperty(testProperty)
                        if (propertyResult.isSuccess) {
                            resultText += "✅ Successfully added test property\n"
                            resultText += "\n🎉 All Firestore tests passed!\n"
                            resultText += "Your database is ready to use."
                        } else {
                            resultText += "❌ Failed to add property: ${propertyResult.exceptionOrNull()?.message}"
                        }
                    } else {
                        resultText += "❌ Failed to retrieve tenants: ${getAllResult.exceptionOrNull()?.message}"
                    }
                    statusText.text = resultText
                } else {
                    statusText.text = "❌ Failed to add tenant: ${addResult.exceptionOrNull()?.message}"
                }
            } catch (e: Exception) {
                statusText.text = "❌ Error: ${e.message}"
                Log.e("FirestoreTest", "Error testing Firestore", e)
            } finally {
                testButton.isEnabled = true
            }
        }
    }
}

package com.example.navigationuidemo2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TenantViewModel : ViewModel() {

    private val _tenantName = MutableLiveData<String>("")
    val tenantName: LiveData<String> = _tenantName

    private val _unitNumber = MutableLiveData<String>("")
    val unitNumber: LiveData<String> = _unitNumber

    private val _monthlyRent = MutableLiveData<String>("")
    val monthlyRent: LiveData<String> = _monthlyRent

    private val _tenantCount = MutableLiveData<Int>(0)
    val tenantCount: LiveData<Int> = _tenantCount

    private val _errorMessages = MutableLiveData<List<String>>(emptyList())
    val errorMessages: LiveData<List<String>> = _errorMessages

    private val _successMessage = MutableLiveData<String>("")
    val successMessage: LiveData<String> = _successMessage

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun addTenant(name: String, unit: String, rent: String) {
        // Simple validation
        if (name.isBlank() || unit.isBlank() || rent.isBlank()) {
            _errorMessages.value = listOf("All fields are required")
            _successMessage.value = ""
            return
        }

        try {
            val rentValue = rent.toDouble()
            if (rentValue <= 0) {
                _errorMessages.value = listOf("Rent must be greater than zero")
                _successMessage.value = ""
                return
            }
            
            // Simulate successful tenant addition
            _tenantName.value = name
            _unitNumber.value = unit
            _monthlyRent.value = rent
            _tenantCount.value = (_tenantCount.value ?: 0) + 1
            _successMessage.value = "Tenant '$name' added successfully!"
            _errorMessages.value = emptyList()
            
        } catch (e: NumberFormatException) {
            _errorMessages.value = listOf("Invalid rent amount")
            _successMessage.value = ""
        } catch (e: Exception) {
            _errorMessages.value = listOf("Error adding tenant: ${e.message}")
            _successMessage.value = ""
        }
    }

    fun clearMessages() {
        _errorMessages.value = emptyList()
        _successMessage.value = ""
    }
}

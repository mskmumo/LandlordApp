package com.example.navigationuidemo2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.navigationuidemo2.databinding.AddtenantActivityBinding

class AddTenant : AppCompatActivity() {

    private lateinit var binding: AddtenantActivityBinding
    private val viewModel: TenantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.addtenant_activity)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        viewModel.errorMessages.observe(this) { errors ->
            if (errors.isNotEmpty()) {
                showErrors(errors)
            }
        }

        viewModel.successMessage.observe(this) { message ->
            if (message.isNotEmpty()) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                clearForm()
            }
        }
    }

    private fun setupListeners() {
        binding.button.setOnClickListener {
            val name = binding.fullName.text.toString()
            val unit = binding.unitNumber.text.toString()
            val rent = binding.fullRent.text.toString()

            hideKeyboard()
            viewModel.addTenant(name, unit, rent)
        }

        binding.btnBack.setOnClickListener {
            navigateBack()
        }

        // Clear messages when user starts typing
        binding.fullName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) viewModel.clearMessages()
        }
        binding.unitNumber.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) viewModel.clearMessages()
        }
        binding.fullRent.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) viewModel.clearMessages()
        }
    }

    private fun showErrors(errors: List<String>) {
        val errorMessage = errors.joinToString("\n")
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun clearForm() {
        binding.fullName.text?.clear()
        binding.unitNumber.text?.clear()
        binding.fullRent.text?.clear()
    }

    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        currentFocus?.let {
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun navigateBack() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigateBack()
    }
}

package com.example.navigationuidemo2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView adapter for displaying tenant information in the dashboard
 */
class TenantAdapter : RecyclerView.Adapter<TenantAdapter.TenantViewHolder>() {

    private var tenants = mutableListOf<TenantDisplayItem>()

    class TenantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.tvTenantName)
        val unitTextView: TextView = itemView.findViewById(R.id.tvTenantUnit)
        val rentTextView: TextView = itemView.findViewById(R.id.tvTenantRent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TenantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tenant_card, parent, false)
        return TenantViewHolder(view)
    }

    override fun onBindViewHolder(holder: TenantViewHolder, position: Int) {
        val tenant = tenants[position]
        holder.nameTextView.text = tenant.name
        holder.unitTextView.text = "Unit: ${tenant.unit}"
        holder.rentTextView.text = tenant.getFormattedRent()
    }

    override fun getItemCount(): Int = tenants.size

    fun updateTenants(newTenants: List<TenantDisplayItem>) {
        tenants.clear()
        tenants.addAll(newTenants)
        notifyDataSetChanged()
    }
}

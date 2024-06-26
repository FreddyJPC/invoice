package com.example.invoice.repository

import com.example.invoice.entity.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface InvoiceRepository : JpaRepository<Invoice, Long> {


    @Query(nativeQuery = true)
    fun findTotal(value: Double): List<Invoice>
}

package com.example.invoice.service

import com.example.invoice.entity.Invoice
import com.example.invoice.entity.InvoiceView
import com.example.invoice.repository.InvoiceRepository
import com.example.invoice.repository.InvoiceViewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InvoiceService {

    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    @Autowired
    lateinit var invoiceViewRepository: InvoiceViewRepository

    fun list(): List<Invoice> {
        return invoiceRepository.findAll()
    }

    fun save(invoice: Invoice): Invoice {
        return invoiceRepository.save(invoice)
    }

    fun update(invoice: Invoice): Invoice {
        return invoiceRepository.save(invoice)
    }

    fun delete(id: Long) {
        invoiceRepository.deleteById(id)
    }

    fun getById(id: Long): Invoice? {
        return invoiceRepository.findById(id).orElse(null)
    }

    fun partialUpdate(id: Long, invoice: Invoice): Invoice? {
        val existingInvoice = invoiceRepository.findById(id).orElse(null) ?: return null
        existingInvoice.code = invoice.code ?: existingInvoice.code
        existingInvoice.createAt = invoice.createAt ?: existingInvoice.createAt
        existingInvoice.total = invoice.total ?: existingInvoice.total
        existingInvoice.client = invoice.client ?: existingInvoice.client
        return invoiceRepository.save(existingInvoice)
    }

    fun getTotal(value:Double): List<Invoice> {
        return invoiceRepository.findTotal(value)
    }

    fun listView(): List<InvoiceView> {
        return invoiceViewRepository.findAll()
    }


}

package com.example.demo.service

import com.example.demo.entity.Invoice
import com.example.demo.repository.InvoiceRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class InvoiceService {

    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    fun list(): List<Invoice> {
        return invoiceRepository.findAll()
    }

    fun save(invoice: Invoice): Invoice {
        return invoiceRepository.save(invoice)
    }

    fun update(id: Long, invoice: Invoice): Invoice {
        val existingInvoice = invoiceRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró la factura con el ID: $id") }

        existingInvoice.code = invoice.code
        existingInvoice.createAt = invoice.createAt ?: LocalDate.now()
        existingInvoice.total = invoice.total
        existingInvoice.clientId = invoice.clientId

        return invoiceRepository.save(existingInvoice)
    }

    fun delete(id: Long) {
        val existingInvoice = invoiceRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró la factura con el ID: $id") }

        invoiceRepository.delete(existingInvoice)
    }

    fun getById(id: Long): Invoice {
        return invoiceRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró la factura con el ID: $id") }
    }

    fun partialUpdate(id: Long, partialInvoice: Map<String, Any>): Invoice {
        val existingInvoice = invoiceRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró la factura con el ID: $id") }

        partialInvoice.forEach { (key, value) ->
            when (key) {
                "code" -> existingInvoice.code = value as String
                "createAt" -> existingInvoice.createAt = LocalDate.parse(value as String)
                "total" -> existingInvoice.total = (value as Number).toDouble()
                "clientId" -> existingInvoice.clientId = (value as Number).toLong()
                else -> throw IllegalArgumentException("Campo no válido para actualización parcial: $key")
            }
        }

        return invoiceRepository.save(existingInvoice)
    }
}

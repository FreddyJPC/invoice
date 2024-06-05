package com.example.invoice.controller

import com.example.invoice.entity.Invoice
import com.example.invoice.entity.InvoiceView
import com.example.invoice.service.InvoiceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/invoices")
class InvoiceController {

    @Autowired
    lateinit var invoiceService: InvoiceService

    @GetMapping
    fun list(): List<Invoice> {
        return invoiceService.list()
    }

    @PostMapping
    fun save(@RequestBody invoice: Invoice): Invoice {
        return invoiceService.save(invoice)
    }

    @PutMapping
    fun update(@RequestBody invoice: Invoice): ResponseEntity<Invoice> {
        val updatedInvoice = invoiceService.update(invoice)
        return ResponseEntity.ok(updatedInvoice)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        invoiceService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Invoice> {
        val invoice = invoiceService.getById(id)
        return if (invoice != null) {
            ResponseEntity.ok(invoice)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun partialUpdate(@PathVariable id: Long, @RequestBody invoice: Invoice): ResponseEntity<Invoice> {
        val updatedInvoice = invoiceService.partialUpdate(id, invoice)
        return if (updatedInvoice != null) {
            ResponseEntity.ok(updatedInvoice)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/{value}/get-total")
    fun getTotal (@PathVariable value: Double): List<Invoice>{
      return  invoiceService.getTotal(value)
    }
    @GetMapping("/with-client")
    fun listView(): List<InvoiceView> {
        return invoiceService.listView()
    }

}

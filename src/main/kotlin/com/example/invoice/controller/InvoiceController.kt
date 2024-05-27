package com.example.demo.controller

import com.example.demo.entity.Invoice
import com.example.demo.service.InvoiceService
import org.springframework.beans.factory.annotation.Autowired
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

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody invoice: Invoice): Invoice {
        return invoiceService.update(id, invoice)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        invoiceService.delete(id)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Invoice {
        return invoiceService.getById(id)
    }

    @PatchMapping("/{id}")
    fun partialUpdate(@PathVariable id: Long, @RequestBody partialInvoice: Map<String, Any>): Invoice {
        return invoiceService.partialUpdate(id, partialInvoice)
    }
}

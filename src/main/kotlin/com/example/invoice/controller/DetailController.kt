package com.example.invoice.controller

import com.example.invoice.entity.Detail
import com.example.invoice.service.DetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/details")
class DetailController {

    @Autowired
    lateinit var detailService: DetailService

    @GetMapping
    fun list(): List<Detail> {
        return detailService.list()
    }

    @PostMapping
    fun save(@RequestBody detail: Detail): Detail {
        return detailService.save(detail)
    }

    @PutMapping
    fun update(@RequestBody detail: Detail): ResponseEntity<Detail> {
        val updatedDetail = detailService.update(detail)
        return ResponseEntity.ok(updatedDetail)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        detailService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Detail> {
        val detail = detailService.getById(id)
        return if (detail != null) {
            ResponseEntity.ok(detail)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}")
    fun partialUpdate(@PathVariable id: Long, @RequestBody detail: Detail): ResponseEntity<Detail> {
        val updatedDetail = detailService.partialUpdate(id, detail)
        return if (updatedDetail != null) {
            ResponseEntity.ok(updatedDetail)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}

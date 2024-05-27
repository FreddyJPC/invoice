package com.example.demo.controller

import com.example.demo.entity.Detail
import com.example.demo.service.DetailService
import org.springframework.beans.factory.annotation.Autowired
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

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody detail: Detail): Detail {
        return detailService.update(id, detail)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        detailService.delete(id)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Detail {
        return detailService.getById(id)
    }

    @PatchMapping("/{id}")
    fun partialUpdate(@PathVariable id: Long, @RequestBody partialDetail: Map<String, Any>): Detail {
        return detailService.partialUpdate(id, partialDetail)
    }
}

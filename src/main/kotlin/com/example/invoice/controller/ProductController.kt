package com.example.demo.controller

import com.example.demo.entity.Product
import com.example.demo.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController {

    @Autowired
    lateinit var productService: ProductService

    @GetMapping
    fun list(): List<Product> {
        return productService.list()
    }

    @PostMapping
    fun save(@RequestBody product: Product): Product {
        return productService.save(product)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody product: Product): Product {
        return productService.update(id, product)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        productService.delete(id)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Product {
        return productService.getById(id)
    }

    @PatchMapping("/{id}")
    fun partialUpdate(@PathVariable id: Long, @RequestBody partialProduct: Map<String, Any>): Product {
        return productService.partialUpdate(id, partialProduct)
    }
}

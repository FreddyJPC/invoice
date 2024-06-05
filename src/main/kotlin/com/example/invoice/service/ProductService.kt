package com.example.invoice.service

import com.example.invoice.entity.Product
import com.example.invoice.repository.ProductRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService {

    @Autowired
    lateinit var productRepository: ProductRepository

    fun list(): List<Product> {
        return productRepository.findAll()
    }

    fun save(product: Product): Product {
        return productRepository.save(product)
    }

    fun update(id: Long, product: Product): Product {
        val existingProduct = productRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el producto con el ID: $id") }

        existingProduct.description = product.description
        existingProduct.brand = product.brand
        existingProduct.price = product.price
        existingProduct.stock = product.stock

        return productRepository.save(existingProduct)
    }

    fun delete(id: Long) {
        val existingProduct = productRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el producto con el ID: $id") }

        productRepository.delete(existingProduct)
    }

    fun getById(id: Long): Product {
        return productRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el producto con el ID: $id") }
    }

    fun partialUpdate(id: Long, partialProduct: Map<String, Any>): Product {
        val existingProduct = productRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el producto con el ID: $id") }

        partialProduct.forEach { (key, value) ->
            when (key) {
                "description" -> existingProduct.description = value as String
                "brand" -> existingProduct.brand = value as String
                "price" -> existingProduct.price = (value as Number).toDouble()
                "stock" -> existingProduct.stock = (value as Number).toInt()
                else -> throw IllegalArgumentException("Campo no válido para actualización parcial: $key")
            }
        }

        return productRepository.save(existingProduct)
    }
}

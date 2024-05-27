package com.example.demo.service

import com.example.demo.entity.Detail
import com.example.demo.repository.DetailRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DetailService {

    @Autowired
    lateinit var detailRepository: DetailRepository

    fun list(): List<Detail> {
        return detailRepository.findAll()
    }

    fun save(detail: Detail): Detail {
        return detailRepository.save(detail)
    }

    fun update(id: Long, detail: Detail): Detail {
        val existingDetail = detailRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el detalle con el ID: $id") }

        existingDetail.quantity = detail.quantity
        existingDetail.price = detail.price
        existingDetail.invoiceId = detail.invoiceId
        existingDetail.productId = detail.productId

        return detailRepository.save(existingDetail)
    }

    fun delete(id: Long) {
        val existingDetail = detailRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el detalle con el ID: $id") }

        detailRepository.delete(existingDetail)
    }

    fun getById(id: Long): Detail {
        return detailRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el detalle con el ID: $id") }
    }

    fun partialUpdate(id: Long, partialDetail: Map<String, Any>): Detail {
        val existingDetail = detailRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el detalle con el ID: $id") }

        partialDetail.forEach { (key, value) ->
            when (key) {
                "quantity" -> existingDetail.quantity = (value as Number).toInt()
                "price" -> existingDetail.price = (value as Number).toDouble()
                "invoiceId" -> existingDetail.invoiceId = (value as Number).toLong()
                "productId" -> existingDetail.productId = (value as Number).toLong()
                else -> throw IllegalArgumentException("Campo no válido para actualización parcial: $key")
            }
        }

        return detailRepository.save(existingDetail)
    }
}

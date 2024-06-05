package com.example.invoice.service

import com.example.invoice.entity.Detail
import com.example.invoice.repository.DetailRepository
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

    fun update(detail: Detail): Detail {
        return detailRepository.save(detail)
    }

    fun delete(id: Long) {
        detailRepository.deleteById(id)
    }

    fun getById(id: Long): Detail? {
        return detailRepository.findById(id).orElse(null)
    }

    fun partialUpdate(id: Long, detail: Detail): Detail? {
        val existingDetail = detailRepository.findById(id).orElse(null) ?: return null
        existingDetail.quantity = detail.quantity ?: existingDetail.quantity
        existingDetail.price = detail.price ?: existingDetail.price
        return detailRepository.save(existingDetail)
    }
}

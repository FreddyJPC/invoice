package com.example.invoice.entity

import jakarta.persistence.*

@Entity
@Table(name = "product")
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null

    @Column(name = "description", nullable = false)
    var description: String? = null

    @Column(name = "brand")
    var brand: String? = null

    @Column(name = "price", nullable = false)
    var price: Double? = 0.00

    @Column(name = "stock")
    var stock: Int? = null

}

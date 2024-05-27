package com.example.demo.entity

import jakarta.persistence.*

@Entity
@Table(name = "detail", uniqueConstraints = [UniqueConstraint(columnNames = ["invoice_id", "product_id"])])
class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null

    @Column(name = "quantity", nullable = false)
    var quantity: Int? = null

    @Column(name = "price")
    var price: Double? = null

    @Column(name = "subtotal", insertable = false, updatable = false)
    var subtotal: Double? = null

    @Column(name = "invoice_id")
    var invoiceId: Long? = null

    @Column(name = "product_id")
    var productId: Long? = null

}

package com.example.demo.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "invoice", uniqueConstraints = [UniqueConstraint(columnNames = ["code"])])
class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null

    @Column(name = "code", nullable = false, unique = true)
    var code: String? = null

    @Column(name = "create_at")
    var createAt: LocalDate? = null

    @Column(name = "total")
    var total: Double? = null

    @Column(name = "client_id")
    var clientId: Long? = null

}

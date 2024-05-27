package com.example.demo.entity

import jakarta.persistence.*

@Entity
@Table(name = "client", uniqueConstraints = [UniqueConstraint(columnNames = ["nui"])])
class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null

    @Column(name = "nui", nullable = false, unique = true)
    var nui: String? = null

    @Column(name = "fullname", nullable = false)
    var fullname: String? = null

    @Column(name = "address")
    var address: String? = null

    @Column(name = "email")
    var email: String? = null

}

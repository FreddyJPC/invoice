package com.example.demo.service

import com.example.demo.entity.Client
import com.example.demo.repository.ClientRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientService {

    @Autowired
    lateinit var clientRepository: ClientRepository

    fun list(): List<Client> {
        return clientRepository.findAll()
    }

    fun save(client: Client): Client {
        return clientRepository.save(client)
    }

    fun update(id: Long, client: Client): Client {
        val existingClient = clientRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el cliente con el ID: $id") }

        existingClient.nui = client.nui
        existingClient.fullname = client.fullname
        existingClient.address = client.address
        existingClient.email = client.email

        return clientRepository.save(existingClient)
    }

    fun delete(id: Long) {
        val existingClient = clientRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el cliente con el ID: $id") }

        clientRepository.delete(existingClient)
    }

    fun getById(id: Long): Client {
        return clientRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el cliente con el ID: $id") }
    }

    fun partialUpdate(id: Long, partialClient: Map<String, Any>): Client {
        val existingClient = clientRepository.findById(id)
            .orElseThrow { EntityNotFoundException("No se encontró el cliente con el ID: $id") }

        partialClient.forEach { (key, value) ->
            when (key) {
                "nui" -> existingClient.nui = value as String
                "fullname" -> existingClient.fullname = value as String
                "address" -> existingClient.address = value as String
                "email" -> existingClient.email = value as String
                else -> throw IllegalArgumentException("Campo no válido para actualización parcial: $key")
            }
        }

        return clientRepository.save(existingClient)
    }
}

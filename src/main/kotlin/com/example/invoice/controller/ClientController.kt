package com.example.invoice.controller

import com.example.invoice.entity.Client
import com.example.invoice.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/client")
class ClientController {

    @Autowired
    lateinit var clientService: ClientService

    @GetMapping
    fun list(): List<Client> {
        return clientService.list()
    }

    @PostMapping
    fun save(@RequestBody client: Client): Client {
        return clientService.save(client)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody client: Client): Client {
        return clientService.update(id, client)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        clientService.delete(id)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Client {
        return clientService.getById(id)
    }

    @PatchMapping("/{id}")
    fun partialUpdate(@PathVariable id: Long, @RequestBody partialClient: Map<String, Any>): Client {
        return clientService.partialUpdate(id, partialClient)
    }
}

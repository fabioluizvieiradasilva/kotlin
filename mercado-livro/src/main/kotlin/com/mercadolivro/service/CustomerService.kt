package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.enums.Profile
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val bookService: BookService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    fun getAll(name:String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(name)
        }
        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerModel){
        val customerCopy = customer.copy(
            role = setOf(Profile.CUSTOMER),
            password = bCryptPasswordEncoder.encode(customer.password)
        )
        customerRepository.save(customerCopy)
    }

    fun getById(id:Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow{NotFoundException(
            Errors.ML201.message.format(id),
            Errors.ML201.code
        )}
    }

    fun update(customer: CustomerModel) {
        if(!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun delete(id:Int) {
        if(!customerRepository.existsById(id)){
            throw Exception()
        }
        val customer = getById(id)
        this.bookService.deleteByCustomer(customer)
        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }
}
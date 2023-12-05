package com.mercadolivro.repository

import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.enums.BookStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface BookRepository:JpaRepository<BookModel, Int> {
    abstract fun findByNameContaining(pageable: Pageable,name: String?): Page<BookModel>
    abstract fun findByCustomer(customer: CustomerModel): List<BookModel>
    abstract fun findByStatus(ativo: BookStatus, pageable: Pageable): Page<BookModel>
}

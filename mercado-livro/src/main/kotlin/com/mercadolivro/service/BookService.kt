package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun create(book: BookModel) {

        bookRepository.save(book)
    }

    fun getAll(pageable: Pageable, name: String?): Page<BookModel> {
        name?.let {
            return bookRepository.findByNameContaining(pageable,name)
        }
        return bookRepository.findAll(pageable)
    }

    fun getById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow()
    }

    fun update(book: BookModel) {
        if(!bookRepository.existsById(book.id!!)){
            throw Exception()
        }
        bookRepository.save(book)
    }

    fun delete(id: Int) {
        if(!this.bookRepository.existsById(id)){
            throw Exception()
        }
        val book = getById(id)
        book.status = BookStatus.CANCELADO
        update(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = this.bookRepository.findByCustomer(customer)
        for (book in books){
            book.status = BookStatus.DELETADO
        }

        this.bookRepository.saveAll(books)

    }

    fun getActives(pageable: Pageable,):Page<BookModel> {
        return this.bookRepository.findByStatus(BookStatus.ATIVO, pageable)
    }


}


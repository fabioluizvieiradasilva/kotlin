package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.extension.toBookModel
import com.mercadolivro.extension.toResponse
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/book")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {

    @GetMapping
    fun getAll(@PageableDefault(page = 0, size = 10) pageable: Pageable, @RequestParam name:String?): Page<BookResponse> {
        return this.bookService.getAll(pageable, name).map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): BookResponse {
        return this.bookService.getById(id).toResponse()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid book: PostBookRequest){
        val customer = customerService.getById(book.customerId)
        this.bookService.create(book.toBookModel(customer))
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody @Valid book: PutBookRequest) {
        val bookUpdate = bookService.getById(id)
        this.bookService.update(book.toBookModel(bookUpdate))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int){
        this.bookService.delete(id)
    }

    @GetMapping("/active/{active}")
    fun getActives(@PageableDefault(page = 0, size = 10) pageable: Pageable):Page<BookResponse>{
        return bookService.getActives(pageable).map { it.toResponse() }
    }

}
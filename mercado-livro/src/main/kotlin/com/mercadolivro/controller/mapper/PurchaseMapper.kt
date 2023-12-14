package com.mercadolivro.controller.mapper

import com.mercadolivro.controller.request.PostPurchaseRequest
import com.mercadolivro.model.PurchaseModel
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
    val bookService: BookService,
    val customerService: CustomerService
) {
    fun toModel(request: PostPurchaseRequest): PurchaseModel{
        val customer = customerService.getById(request.customerId)
        val books = bookService.getAllById(request.bookId)

        return PurchaseModel(
            customer = customer,
            books = books.toMutableList(),
            price = books.sumOf { it.price }
        )
    }
}
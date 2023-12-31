package com.mercadolivro.extension

import com.mercadolivro.controller.request.*
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.controller.response.CustomerResponse
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.model.PurchaseModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel{
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO, password = this.password)
}

fun PutCustomerRequest.toCustomerModel(customer: CustomerModel): CustomerModel{
    return CustomerModel(id = customer.id, name = this.name, email = this.email, status = customer.status, password = customer.password)
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel{
    return BookModel(
        name = this.name,
        status = BookStatus.ATIVO,
        price = this.price,
        customer = customer
    )
}

fun PutBookRequest.toBookModel(bookModel: BookModel): BookModel{
    return BookModel(
        id = bookModel.id,
        name = this.name?:bookModel.name,
        price = this.price?:bookModel.price,
        status = bookModel.status,
        customer = bookModel.customer
    )
}

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id,
        name = this.name,
        email = this.email,
        status = this.status
    )
}

fun BookModel.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        customer = this.customer,
        status = this.status
    )
}
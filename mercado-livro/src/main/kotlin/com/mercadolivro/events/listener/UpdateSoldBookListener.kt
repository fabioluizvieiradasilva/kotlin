package com.mercadolivro.events.listener

import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.service.BookService
import com.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UpdateSoldBookListener (
    val bookService: BookService
){

    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent){
        println("Atualizando status do livro para vendido")
        bookService.purchase(purchaseEvent.purchaseModel.books)
    }
}
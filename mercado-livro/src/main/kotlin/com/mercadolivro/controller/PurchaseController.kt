package com.mercadolivro.controller

import com.mercadolivro.controller.mapper.PurchaseMapper
import com.mercadolivro.controller.request.PostPurchaseRequest
import com.mercadolivro.model.PurchaseModel
import com.mercadolivro.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("purchase")
class PurchaseController(
    val purchaseService: PurchaseService,
    val purchaseMapper: PurchaseMapper
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PostPurchaseRequest){

        this.purchaseService.create(purchaseMapper.toModel(request))

    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int):PurchaseModel{
        return purchaseService.getById(id)
    }
}
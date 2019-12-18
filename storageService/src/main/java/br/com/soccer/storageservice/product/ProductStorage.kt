package br.com.soccer.storageservice.product

import br.com.soccer.storageservice.product.model.ProductStorageModel

interface ProductStorage {
    fun getProducts(): ProductStorageModel?
}
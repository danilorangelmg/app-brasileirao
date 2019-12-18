package br.com.soccer.storageservice.product

import br.com.soccer.storageservice.local.Json
import br.com.soccer.storageservice.product.model.ProductStorageModel

class ProductStorageImpl(val json: Json): ProductStorage {
    override fun getProducts() = json.fromJson("products.json", ProductStorageModel::class.java)
}
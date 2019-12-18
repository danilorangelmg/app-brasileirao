package br.com.soccer.storageservice.product.model

data class ProductStorageModel(val products: List<ProductStorage>)

data class ProductStorage(
    val code: String,
    val name: String,
    val price: Double
)
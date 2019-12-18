package br.com.soccer.storageservice.local

interface Json {
    fun <T> fromJson(fileName: String, clazz: Class<T>) : T?
}
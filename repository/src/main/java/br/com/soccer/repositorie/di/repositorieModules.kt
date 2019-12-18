package br.com.soccer.repositorie.di

import br.com.soccer.networkservice.soccer.SoccerService
import br.com.soccer.networkservice.soccer.SoccerServiceImpl
import br.com.soccer.repositorie.soccer.SoccerRepository
import br.com.soccer.repositorie.soccer.SoccerRepositoryImpl
import br.com.soccer.storageservice.local.Json
import br.com.soccer.storageservice.local.JsonImpl
import br.com.soccer.storageservice.product.ProductStorage
import br.com.soccer.storageservice.product.ProductStorageImpl
import org.koin.dsl.module

val repositoryModules by lazy {
    listOf(movies, storage)
}

val movies = module {
    single<SoccerRepository> { SoccerRepositoryImpl(soccerService = get(), storage = get()) }
    factory<SoccerService> { SoccerServiceImpl() }
    factory<ProductStorage> { ProductStorageImpl(json = get())}
}

val storage = module {
    single<Json> { JsonImpl(context = get()) }
}



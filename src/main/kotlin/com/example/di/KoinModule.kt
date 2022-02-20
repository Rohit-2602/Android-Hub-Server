package com.example.di

import com.example.controller.user.UserRepository
import com.example.controller.user.UserRepositoryImpl
import com.example.service.UserService
import com.example.util.Constants
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val koinModule = module {

    single<CoroutineDatabase> {
        val client = KMongo.createClient().coroutine
        client.getDatabase(Constants.DATABASE_NAME)
    }

    single<UserRepository> {
        UserRepositoryImpl(get())
    }

    single<UserService> {
        UserService(get())
    }

}
package com.example.di

import com.example.controller.user.UserController
import com.example.controller.user.UserControllerImpl
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

    single<UserController> {
        UserControllerImpl(get())
    }

}
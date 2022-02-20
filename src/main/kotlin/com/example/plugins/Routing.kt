package com.example.plugins

import com.example.route.getUserProfile
import com.example.route.userRoutes
import com.example.service.UserService
import io.ktor.application.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val userService: UserService by inject()
    routing {
        userRoutes(userService)
        getUserProfile(userService)
    }
}

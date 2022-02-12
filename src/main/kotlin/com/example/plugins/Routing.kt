package com.example.plugins

import com.example.route.userRoutes
import io.ktor.application.*
import io.ktor.routing.*

fun Application.configureRouting() {
    routing {
        userRoutes()
    }
}

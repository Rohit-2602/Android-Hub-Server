package com.example.route

import com.example.data.requests.CreateAccountRequest
import com.example.data.response.BasicApiResponse
import com.example.service.UserService
import com.example.util.ApiResponseMessages.FIELD_EMPTY
import com.example.util.ApiResponseMessages.USERNAME_ALREADY_EXIST
import com.example.util.ApiResponseMessages.USER_ALREADY_EXIST
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.userRoutes(userService: UserService) {

    post("/api/user/verify") {
        val request = call.receiveOrNull<CreateAccountRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }
        if (userService.doesUserWithEmailExist(request.email)) {
            call.respond(
                BasicApiResponse<Unit>(
                    message = USER_ALREADY_EXIST,
                    successful = false
                )
            )
            return@post
        }
        if (userService.doesUserWithUsernameExist(request.username)) {
            call.respond(
                BasicApiResponse<Unit>(
                    message = USERNAME_ALREADY_EXIST,
                    successful = false
                )
            )
            return@post
        }
        if (request.email.isBlank() || request.username.isBlank()) {
            call.respond(
                BasicApiResponse<Unit>(
                    message = FIELD_EMPTY,
                    successful = false
                )
            )
            return@post
        }
        call.respond(
            BasicApiResponse<Unit>(
                message = "User can be created with these Fields",
                successful = true,
            )
        )
    }

    post("/api/user/create") {
        val request = call.receiveOrNull<CreateAccountRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }
        if (userService.doesUserWithEmailExist(request.email)) {
            call.respond(
                BasicApiResponse<Unit>(
                    message = USER_ALREADY_EXIST,
                    successful = false
                )
            )
            return@post
        }
        if (userService.doesUserWithUsernameExist(request.username)) {
            call.respond(
                BasicApiResponse<Unit>(
                    message = USERNAME_ALREADY_EXIST,
                    successful = false
                )
            )
            return@post
        }
        if (request.email.isBlank() || request.username.isBlank()) {
            call.respond(
                BasicApiResponse<Unit>(
                    message = FIELD_EMPTY,
                    successful = false
                )
            )
            return@post
        }
        userService.createUser(request)
        call.respond(
            BasicApiResponse<Unit>(
                message = "Successfully created User",
                successful = true
            )
        )
    }

}
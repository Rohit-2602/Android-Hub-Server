package com.example.route

import com.example.data.response.BasicApiResponse
import com.example.service.UserService
import com.example.util.ApiResponseMessages
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getUserProfile(userService: UserService) {
    get("/api/user/profile") {
        val userId = call.parameters["userId"]
        val callerId = call.parameters["callerId"]
        if (userId == null || userId.isBlank()) {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }
        if (callerId == null || callerId.isBlank()) {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }
        val profileResponse = userService.getUserProfile(userId, callerId)
        if (profileResponse == null) {
            call.respond(
                HttpStatusCode.OK,
                BasicApiResponse<Unit>(
                    successful = false,
                    message = ApiResponseMessages.USER_NOT_FOUND
                )
            )
            return@get
        }
        call.respond(
            HttpStatusCode.OK,
            BasicApiResponse(
                successful = true,
                data = profileResponse
            )
        )
    }
}
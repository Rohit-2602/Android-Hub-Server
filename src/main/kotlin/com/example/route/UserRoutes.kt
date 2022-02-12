package com.example.route

import com.example.controller.user.UserController
import com.example.data.models.User
import com.example.data.requests.CreateAccountRequest
import com.example.data.response.BasicApiResponse
import com.example.util.ApiResponseMessages.FIELD_EMPTY
import com.example.util.ApiResponseMessages.USER_ALREADY_EXIST
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.userRoutes() {

    val userController: UserController by inject()

    route("/api/user/create") {
        post {
            val request = call.receiveOrNull<CreateAccountRequest>()?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            val userExist = userController.getUserByEmail(request.email) != null
            if (userExist) {
                call.respond(
                    BasicApiResponse(
                        message = USER_ALREADY_EXIST,
                        successful = false
                    )
                )
                return@post
            }
            if (request.email.isBlank() || request.username.isBlank() || request.password.isBlank()) {
                call.respond(
                    BasicApiResponse(
                        message = FIELD_EMPTY,
                        successful = false
                    )
                )
                return@post
            }
            userController.createUser(
                User(
                    username = request.username,
                    email = request.email,
                    password = request.password,
                    profileImageUrl = "",
                    bio = "",
                    skills = listOf(),
                    gitHubUrl = null,
                    linkedInUrl = null
                )
            )

            call.respond(
                BasicApiResponse(
                    message = "Successfully created User",
                    successful = true
                )
            )
        }
    }

}
package com.example.service

import com.example.controller.user.UserRepository
import com.example.data.models.User
import com.example.data.requests.CreateAccountRequest
import com.example.data.response.ProfileResponse

class UserService(
    private val userRepository: UserRepository,
) {

    suspend fun doesUserWithEmailExist(email: String): Boolean {
        return userRepository.getUserByEmail(email) != null
    }

    suspend fun doesUserWithUsernameExist(username: String): Boolean {
        return userRepository.getUserByUserName(username) != null
    }

    suspend fun createUser(request: CreateAccountRequest) {
        userRepository.createUser(
            User(
                id = request.id,
                email = request.email,
                username = request.username,
                profileImageUrl = "",
                bannerUrl = null,
                bio = "",
                gitHubUrl = null,
                linkedInUrl = null
            )
        )
    }

    suspend fun getUserProfile(userId: String, callerUserId: String): ProfileResponse? {
        val user = userRepository.getUserById(userId) ?: return null
        return ProfileResponse(
            userId = user.id,
            username = user.username,
            email = user.email,
            profilePictureUrl = user.profileImageUrl,
            bio = user.bio,
            bannerUrl = user.bannerUrl,
            followerCount = user.followerCount,
            followingCount = user.followingCount,
            postCount = user.postCount,
            articleCount = user.articleCount,
            topSkills = user.skills,
            gitHubUrl = user.gitHubUrl,
            linkedInUrl = user.linkedInUrl,
            isOwnProfile = userId == callerUserId,
            isFollowing = false
        )
    }

    suspend fun getUserByEmail(email: String): User? {
        return userRepository.getUserByEmail(email)
    }

}
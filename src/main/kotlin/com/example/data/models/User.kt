package com.example.data.models

data class User(
    val id: String,
    val username: String,
    val email: String,
    val profileImageUrl: String,
    val bannerUrl: String?,
    val bio: String,
    val gitHubUrl: String?,
    val linkedInUrl: String?,
    val followerCount: Int = 0,
    val followingCount: Int = 0,
    val postCount: Int = 0,
    val articleCount: Int = 0,
    val skills: List<String> = listOf(),
)

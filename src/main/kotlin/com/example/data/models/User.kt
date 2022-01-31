package com.example.data.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class User(
    @BsonId
    val id: String = ObjectId().toString(),
    val username: String,
    val password: String,
    val profileImageUrl: String,
    val bio: String,
    val skills: List<String> = listOf(),
    val gitHubUrl: String?,
    val linkedInUrl: String,
)

package com.example.data.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Post(
    val creatorId: String,
    val imageUrls: List<String> = listOf(),
    val description: String,
    val timeStamp: Long,
    @BsonId
    val id: String = ObjectId().toString(),
)
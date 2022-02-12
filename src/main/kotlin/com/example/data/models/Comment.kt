package com.example.data.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Comment(
    val userId: String,
    // This Id define either Post or Article
    val parentId: String,
    val comment: String,
    val timestamp: Long,
    @BsonId
    val id: String = ObjectId().toString(),
)

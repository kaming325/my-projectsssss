package com.example.money_change_backend.order

import lombok.Data
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date

@Document(collection = "order")
public data class Order(
        @Id
        val _id: ObjectId,
        val username: String,
        val totalAmount: Int,
        val payment: Int,
        val changeAmount: Int,
        val date:String
        )
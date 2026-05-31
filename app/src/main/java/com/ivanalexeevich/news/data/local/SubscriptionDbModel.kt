package com.ivanalexeevich.news.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "subscriptions"
)
data class SubscriptionDbModel (
    @PrimaryKey val topic: String
)
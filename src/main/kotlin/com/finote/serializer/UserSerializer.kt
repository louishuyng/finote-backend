package com.finote.serializer

import com.finote.models.User
import kotlinx.serialization.Serializable

@Serializable data class UserResource(val username: String, val email: String)

object UserSerializer {
  fun toUserResource(user: User): UserResource {
    return UserResource(username = user.username, email = user.email)
  }
}
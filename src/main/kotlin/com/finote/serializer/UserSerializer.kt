package com.finote.serializer

import com.finote.models.User
import kotlinx.serialization.Serializable

@Serializable data class UserResource(val id: Int?, val username: String, val email: String)

object UserSerializer {
  fun toUserResource(user: User): UserResource {
    return UserResource(id = user.id, username = user.username, email = user.email)
  }
}

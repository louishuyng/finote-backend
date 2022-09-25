package com.finote.services

import com.finote.dao.user.userDAO
import com.finote.models.User
import com.finote.utils.Hasher
import io.ktor.server.plugins.*

class UserService {
  suspend fun createUser(user: User): User? {
    val hashedPassword = Hasher.hashPassword(user.password)
    val newUser = User(username = user.username, email = user.email, password = hashedPassword)
    return userDAO.createUser(newUser)
  }

  suspend fun loginUser(username: String, password: String): User? {
    val user = userDAO.findByUsername(username)
    if (user != null) {
      if (Hasher.checkPassword(password, user)) return user
      else {
        return null
      }
    } else return null
  }
}

val userService = UserService()

package com.finote.services

import com.finote.dao.user.userDAO
import com.finote.models.User
import com.finote.utils.Hasher
import io.ktor.server.plugins.*

class UserService {
  suspend fun createUser(user: User): User? {
    checkExistUser(user.username, user.email)

    val hashedPassword = Hasher.hashPassword(user.password)
    val newUser = User(username = user.username, email = user.email, password = hashedPassword)

    return userDAO.createUser(newUser)
  }

  suspend fun loginUser(username: String, password: String): User? {
    val user = userDAO.findByUsername(username)
    if (user != null) {
      if (Hasher.checkPassword(password, user)) return user
      else {
        throw AssertionError("Wrong Password")
      }
    } else throw NotFoundException("user not found")
  }

  private suspend fun checkExistUser(username: String, email: String) {
    val existUser = userDAO.findByUsername(username)
    val existEmailUser = userDAO.findByEmail(email)

    if (existUser != null || existEmailUser != null) {
      throw AssertionError("This user has been created before")
    }
  }
}

val userService = UserService()

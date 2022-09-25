package com.finote.dao.user

import com.finote.models.User

interface UserDaoFacade {
  suspend fun createUser(user: User): User?
  suspend fun findByUsername(username: String): User?
}

package com.finote.dao.user

import com.finote.dao.DatabaseFactory.dbQuery
import com.finote.models.User
import com.finote.models.Users
import org.jetbrains.exposed.sql.*

class UserDaoImpl : UserDaoFacade {
  private fun resultRowToNode(row: ResultRow) =
      User(
          id = row[Users.id],
          username = row[Users.username],
          email = row[Users.email],
          password = row[Users.password]
      )
  override suspend fun createUser(user: User): User? = dbQuery {
    val insertStatement =
        Users.insert {
          it[Users.username] = user.username
          it[Users.email] = user.email
          it[Users.password] = user.password
        }

    insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToNode)
  }

  override suspend fun findByUsername(username: String): User? = dbQuery {
    println(username)
    Users.select { Users.username eq username }.map(::resultRowToNode).singleOrNull()
  }

  override suspend fun findByEmail(email: String): User? = dbQuery {
    Users.select { Users.email eq email }.map(::resultRowToNode).singleOrNull()
  }
}

val userDAO = UserDaoImpl()

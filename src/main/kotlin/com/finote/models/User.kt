package com.finote.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable
data class User(val id: Int? = 0, val username: String, val email: String, val password: String)

object Users : Table() {
  val id = integer("id").autoIncrement()

  val username = varchar("username", 128).uniqueIndex()
  val email = varchar("email", 128).uniqueIndex()
  val password = varchar("password", 128)

  override val primaryKey = PrimaryKey(id)
}

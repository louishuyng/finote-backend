package com.finote.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable data class Income(val id: Int? = 0, val amount: Float, val user_id: Int)

object Incomes : Table() {
  val id = integer("id").autoIncrement()

  val amount = float("amount")
  val user_id = reference("user", Users.id)

  override val primaryKey = PrimaryKey(id)
}

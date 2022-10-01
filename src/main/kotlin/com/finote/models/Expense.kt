package com.finote.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable
data class Expense(
    val id: Int? = 0,
    val amount: Float,
    val category_id: Int,
    val user_id: Int,
    val custom_category: String?,
    val custom_category_priority: Priority?,
)

object Expenses : Table() {
  val id = integer("id").autoIncrement()

  val amount = float("amount")
  val category_id = reference("category", Categories.id)
  val user_id = reference("user", Users.id).nullable()
  val custom_category = varchar("custom_category", 127).nullable()
  val custom_category_priority = varchar("custom_category_priority", 127).nullable()

  override val primaryKey = PrimaryKey(id)
}

package com.finote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*

@Serializable
enum class Priority(val id: Int) {
  @SerialName("must_have") MUST_HAVE(1),
  @SerialName("nice_to_have") NICE_TO_HAVE(2),
  @SerialName("wasted") WASTED(3);

  companion object {
    fun valueOf(value: Int) = values().find { it.id == value }
  }
}

@Serializable data class Category(val id: Int? = 0, val name: String, val priority: Priority)

object Categories : Table() {
  val id = integer("id").autoIncrement()

  val name = varchar("name", 128).uniqueIndex()
  val priority = varchar("priority", 127)

  override val primaryKey = PrimaryKey(id)
}

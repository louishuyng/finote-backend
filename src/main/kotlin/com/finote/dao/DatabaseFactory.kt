package com.finote.dao

import com.finote.models.*
import com.finote.plugins.Env
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.jetbrains.exposed.sql.transactions.experimental.*

object DatabaseFactory {
  fun init() {
    val host = Env.get("DB_HOST") ?: "localhost"
    val port = Env.get("DB_PORT")?.toIntOrNull() ?: 5432
    val dbName = Env.get("DB_NAME") ?: "user_db"
    val dbUser = Env.get("DB_USER") ?: "postgres"
    val dbPassword = Env.get("DB_PASSWORD") ?: "postgres"

    val database =
        Database.connect(
            url = "jdbc:postgresql://$host:$port/$dbName",
            driver = "org.postgresql.Driver",
            user = dbUser,
            password = dbPassword,
        )
    transaction(database) {
      SchemaUtils.create(Users)
      SchemaUtils.create(Categories)
      SchemaUtils.create(Incomes)
      SchemaUtils.create(Expenses)
    }
  }

  suspend fun <T> dbQuery(block: suspend () -> T): T =
      newSuspendedTransaction(Dispatchers.IO) { block() }
}

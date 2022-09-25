package com.finote

import com.finote.dao.DatabaseFactory
import com.finote.plugins.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
  embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        DatabaseFactory.init()
        configureSerialization()
        configureMonitoring()
        configureHTTP()
        configureSecurity()
        configureRouting()
      }
      .start(wait = true)
}

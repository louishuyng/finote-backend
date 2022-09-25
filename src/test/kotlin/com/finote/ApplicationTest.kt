package com.finote

import com.finote.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.cachingheaders.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import io.ktor.util.*
import kotlin.test.*
import org.slf4j.event.*

class ApplicationTest {
  @Test
  fun testRoot() = testApplication {
    application { configureRouting() }
    client.get("/").apply {
      assertEquals(HttpStatusCode.OK, status)
      assertEquals("Hello World!", bodyAsText())
    }
  }
}

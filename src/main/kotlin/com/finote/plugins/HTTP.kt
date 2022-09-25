package com.finote.plugins

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cachingheaders.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*

fun Application.configureHTTP() {
  install(CORS) {
    allowMethod(HttpMethod.Options)
    allowMethod(HttpMethod.Put)
    allowMethod(HttpMethod.Delete)
    allowMethod(HttpMethod.Patch)
    allowHeader(HttpHeaders.Authorization)
    allowHeader("MyCustomHeader")
    anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
  }
  install(CachingHeaders) {
    options { _, outgoingContent ->
      when (outgoingContent.contentType?.withoutParameters()) {
        ContentType.Text.CSS -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 24 * 60 * 60))
        else -> null
      }
    }
  }
}

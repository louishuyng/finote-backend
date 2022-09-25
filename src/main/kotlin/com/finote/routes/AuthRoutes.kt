package com.finote.routes

import com.finote.models.User
import com.finote.serializer.UserSerializer
import com.finote.services.jwtService
import com.finote.services.userService
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.lang.Exception
import java.sql.SQLIntegrityConstraintViolationException

@Serializable data class LoginParams(val username: String, val password: String)

fun Route.authRouting() {
  post("/login") {
    val payloadUser = call.receive<LoginParams>()
    val user = userService.loginUser(payloadUser.username, payloadUser.password)

    if (user != null) {
      val token = jwtService.create(user.username)
      val response = mapOf("token" to token, "user" to UserSerializer.toUserResource(user))

      val gson = Gson()
      val json: String = gson.toJson(response)
      call.respondText(text = json, contentType = ContentType("application", "json"))
    } else call.respond(HttpStatusCode.Unauthorized, "username/password is wrong")
  }

  post("/signup") {
    val user = call.receive<User>()

    try {
      userService.createUser(user)
      call.respond(HttpStatusCode.Created, "User signed up")
    } catch (err: Exception)  {
      call.respond(HttpStatusCode.BadRequest, "Can not create this user")
    }
  }
}

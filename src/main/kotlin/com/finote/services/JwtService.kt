package com.finote.services

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import com.finote.plugins.Env
import io.ktor.server.application.*
import java.util.*

class JwtService {
  private val secret = Env.get("JWT_SECRET")
  private val issuer = Env.get("JWT_ISSUER")
  private val expiration: Int = Env.get("JWT_EXPIRATION").toInt()

  fun create(username: String): String {
    return JWT.create()
        .withIssuer(issuer)
        .withClaim("username", username)
        .withExpiresAt(Date(System.currentTimeMillis() + expiration))
        .sign(Algorithm.HMAC256(secret))
  }
}

val jwtService = JwtService()

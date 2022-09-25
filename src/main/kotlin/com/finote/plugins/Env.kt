package com.finote.plugins

import io.github.cdimascio.dotenv.dotenv

object Env {
  private val dotenv = dotenv()

  fun get(key: String): String {
    return dotenv[key]
  }
}

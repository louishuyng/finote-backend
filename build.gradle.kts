val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val gson_version: String by project

val exposed_version: String by project
val postgresql_version: String by project

plugins {
  application
  kotlin("jvm") version "1.7.10"
  id("io.ktor.plugin") version "2.1.1"
  id("org.jetbrains.kotlin.plugin.serialization") version "1.7.10"
}

group = "com.finote"

version = "0.0.1"

application {
  mainClass.set("com.finote.ApplicationKt")

  val isDevelopment: Boolean = project.ext.has("development")
  applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories { mavenCentral() }

dependencies {
  implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
  implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
  implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
  implementation("io.ktor:ktor-server-call-logging-jvm:$ktor_version")
  implementation("io.ktor:ktor-server-cors-jvm:$ktor_version")
  implementation("io.ktor:ktor-server-caching-headers-jvm:$ktor_version")
  implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")
  implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
  implementation("ch.qos.logback:logback-classic:$logback_version")
  implementation("io.github.cdimascio:dotenv-kotlin:6.3.1")
  implementation("io.ktor:ktor-server-auth:$ktor_version")
  implementation("io.ktor:ktor-server-auth-jwt:$ktor_version")
  implementation("org.mindrot", "jbcrypt", "0.4")
  implementation("com.google.code.gson:gson:$gson_version")
  implementation("io.ktor:ktor-server-status-pages:$ktor_version")

  // Database Connection
  implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
  implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
  implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
  implementation("org.postgresql:postgresql:$postgresql_version")

  testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

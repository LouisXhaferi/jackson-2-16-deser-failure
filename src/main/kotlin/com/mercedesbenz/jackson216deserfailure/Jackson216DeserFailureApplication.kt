package com.mercedesbenz.jackson216deserfailure

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class Jackson216DeserFailureApplication

fun main(args: Array<String>) {
    runApplication<Jackson216DeserFailureApplication>(*args)
}

@RestController
object Controller {
    data class NonPolymorphic(val value: String)

    @PostMapping("/non-polymorphic")
    fun nonPolymorphic(@RequestBody body: NonPolymorphic) = body
}

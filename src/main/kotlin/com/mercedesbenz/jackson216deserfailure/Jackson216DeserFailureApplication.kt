package com.mercedesbenz.jackson216deserfailure

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME
import com.mercedesbenz.jackson216deserfailure.Controller.PolymorphicType.A
import com.mercedesbenz.jackson216deserfailure.Controller.PolymorphicType.B
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

    @JsonTypeInfo(use = NAME, include = PROPERTY, property = "type")
    @JsonSubTypes(
        Type(value = A::class, name = "TypeA"),
        Type(value = B::class, name = "TypeB")
    )

    /**
     * This works in 2.15.3, but fails in 2.16.0
     */
    sealed interface PolymorphicType {

        data class A(val value: String) : PolymorphicType
        data class B(val value: Int) : PolymorphicType
    }

    @PostMapping("/polymorphic")
    fun polymorphic(@RequestBody body: PolymorphicType) = body
}

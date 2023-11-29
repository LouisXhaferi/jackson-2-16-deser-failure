package com.mercedesbenz.jackson216deserfailure

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ControllerTest(
    @LocalServerPort private val port: Int,
    @Autowired private val testRestTemplate: TestRestTemplate
) {
    @Test
    fun `non polymorphic should return successfully`() {
        val request = Controller.NonPolymorphic("Hello World")

        val response = testRestTemplate.postForEntity(
            "http://localhost:$port/non-polymorphic",
            request,
            Controller.NonPolymorphic::class.java
        )

        assertThat(response.statusCode.value()).isEqualTo(200)
        assertThat(response.body).isEqualTo(request)
    }
}

package com.mercedesbenz.jackson216deserfailure

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ObjectMapperTest(
    @Autowired private val objectMapper: ObjectMapper
) {
    @Test
    fun `should deserialize polymorphic type`() {
        val json = """
            {
              "type": "TypeA",
              "value": "Hello World"
            }
        """.trimIndent()

        val result = objectMapper.readValue(json, Controller.PolymorphicType::class.java)

        assertThat(result is Controller.PolymorphicType.A).isTrue
    }
}

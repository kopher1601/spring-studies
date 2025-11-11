package jp.kopher.springsecurityinaction

import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class SpringSecurityInActionApplicationTests(
    private val mockMvc: MockMvc,
) {

    @Test
    fun contextLoads() {
    }

    @Test
    fun helloUnauthenticated() {
        mockMvc.perform(get("/hello"))
            .andExpect(status().isUnauthorized)
    }

    @Test
    @WithMockUser
    fun `helloAuthenticated`() {
        mockMvc.perform(get("/hello"))
            .andExpect(content().string("Hello World!"))
            .andExpect(status().isOk)
    }
}

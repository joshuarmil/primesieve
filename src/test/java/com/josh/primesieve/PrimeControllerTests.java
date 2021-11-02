package com.josh.primesieve;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import javax.validation.ConstraintViolationException;

@SpringBootTest
@AutoConfigureMockMvc
class PrimeControllerTests {
    @Autowired
    private MockMvc mvc;

    @Test
	public void GivenPositiveInteger_ExpectValidArrayList() throws Exception {
        String validArg = "10";
        int expectedPrimes = 4;
        int expectedLength = 2 * expectedPrimes - 1 + 2; // A comma for each prime, minus 1, and two brackets

        MvcResult result = mvc.perform(get("/primes?value={arg}", validArg))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        String responseString = result.getResponse().getContentAsString();
        assertEquals(expectedLength, responseString.length());

    }

	@Test
	public void GivenNegativeInteger_ExpectException() throws Exception {
        String invalidArg = "-5";

        mvc.perform(get("/primes?value={arg}", invalidArg))
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof ConstraintViolationException));
    }

    @Test
	public void GivenNonInteger_ExpectException() throws Exception {
        String invalidArg = "test";

        mvc.perform(get("/primes?value={arg}", invalidArg))
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentTypeMismatchException));
    }
}

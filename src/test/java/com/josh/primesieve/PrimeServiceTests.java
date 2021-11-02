package com.josh.primesieve;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PrimeServiceTests {
    @Autowired
    private PrimeService primeService;

	@Test
	public void NegativeNumber() {
        assertThat(primeService.listPrimes(-5)).isNull();;
	}

    @Test
	public void ZeroNumber() {
        assertThat(primeService.listPrimes(0)).isNull();
	}

    @Test
	public void EndingOnPrime() {
        assertThat(primeService.listPrimes(11).size()).isEqualTo(5);
	}

    @Test
	public void EndingOffPrime() {
        assertThat(primeService.listPrimes(10).size()).isEqualTo(4);
	}

}

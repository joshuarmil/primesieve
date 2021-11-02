package com.josh.primesieve;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class PrimeService {

    public ArrayList<Integer> listPrimes(int maxNumber) {
        if (maxNumber < 2) {
            return null;
        }
        
        ArrayList<Integer> confirmedPrimes = new ArrayList<Integer>();
        boolean[] possiblePrimes = new boolean[maxNumber + 1];

        possiblePrimes[0] = false;
        possiblePrimes[1] = false;

        for (int i = 2; i <= maxNumber; i++) {
            possiblePrimes[i] = true;
        }

        for (int j = 2; j <= Math.sqrt(maxNumber) + 1; j++) {
            if (possiblePrimes[j] == true) {
                for (int k = 2*j; k <= maxNumber; k += j) {
                    possiblePrimes[k] = false;
                }
            }
        }

        for (int l = 2; l <= maxNumber; l++) {
            if (possiblePrimes[l] == true) {
                confirmedPrimes.add(l);
            }
        }

        return confirmedPrimes;
    }

}
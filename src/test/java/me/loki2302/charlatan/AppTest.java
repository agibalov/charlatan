package me.loki2302.charlatan;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class AppTest {
    @Test
    public void doesNotCrashAtAll() {
        Charlatan charlatan = new Charlatan();
        String firstName = charlatan.firstName();
        assertNotNull(firstName);
        assertFalse(firstName.isEmpty());
    }
    
    @Test
    public void randomOptionGeneratorWorks() {
        RandomOptionGenerator<Integer> rog = new RandomOptionGenerator<Integer>()
                .withOption(0, 1)
                .withOption(1, 10)
                .withOption(2, 100);
        
        int[] counts = new int[3];
        Arrays.fill(counts, 0);
        
        for(int i = 0; i < 10000; ++i) {
            int sample = rog.generate();
            ++counts[sample];
        }
        
        assertTrue(counts[0] <= counts[1]);
        assertTrue(counts[1] <= counts[2]);        
    }
}
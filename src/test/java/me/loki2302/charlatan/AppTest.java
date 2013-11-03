package me.loki2302.charlatan;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest {
    @Test
    public void doesNotCrashAtAll() {
        Charlatan charlatan = new Charlatan();
        String firstName = charlatan.firstName();
        assertNotNull(firstName);
        assertFalse(firstName.isEmpty());
    }    
}
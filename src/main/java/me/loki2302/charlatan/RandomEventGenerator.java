package me.loki2302.charlatan;

import java.util.List;

import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;

public class RandomEventGenerator<TEvent> {
    private final EnumeratedDistribution<TEvent> distribution;
    
    public RandomEventGenerator(List<Pair<TEvent, Double>> probabilities) {
        distribution = new EnumeratedDistribution<TEvent>(probabilities);
    }
    
    public TEvent makeEvent() {
        return distribution.sample();
    }
}
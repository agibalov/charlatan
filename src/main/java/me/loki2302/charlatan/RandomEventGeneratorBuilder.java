package me.loki2302.charlatan;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;

public class RandomEventGeneratorBuilder<TEvent> {
    private final List<Pair<TEvent, Double>> probabilities = new ArrayList<Pair<TEvent, Double>>();    
        
    public RandomEventGeneratorBuilder<TEvent> withEvent(TEvent event, double probability) {
        probabilities.add(new Pair<TEvent, Double>(event, probability));
        return this;
    }
    
    public RandomEventGenerator<TEvent> build() {
        return new RandomEventGenerator<TEvent>(probabilities);
    }    
}
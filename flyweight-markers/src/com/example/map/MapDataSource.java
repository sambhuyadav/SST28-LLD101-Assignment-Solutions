package com.example.map;

import java.util.ArrayList;
import java.util.List;

/**
 * Simulates loading map-marker data from a database or API.
 *
 * BEFORE the refactor:
 *   Each marker was constructed with `new MarkerStyle(...)` inline.
 *   1000 restaurant markers → 1000 identical MarkerStyle objects in RAM.
 *
 * AFTER the refactor:
 *   Each marker requests its style via MarkerStyleFactory.getStyle(...).
 *   1000 restaurant markers → 1 shared MarkerStyle object in RAM.
 *
 * The factory call looks like more work, but it is O(1) — just a HashMap
 * lookup — and the memory savings are dramatic on large datasets.
 */
public class MapDataSource {

    public static List<MapMarker> loadMarkers() {
        List<MapMarker> markers = new ArrayList<>();

        // --- Restaurants (100 markers, all sharing ONE MarkerStyle) ---
        for (int i = 0; i < 100; i++) {
            MarkerStyle style = MarkerStyleFactory.getStyle("red", "/icons/restaurant.png", 24);
            markers.add(new MapMarker(12.90 + i * 0.001, 77.60 + i * 0.001, "Restaurant " + i, style));
        }

        // --- Hospitals (50 markers, all sharing ONE MarkerStyle) ---
        for (int i = 0; i < 50; i++) {
            MarkerStyle style = MarkerStyleFactory.getStyle("blue", "/icons/hospital.png", 30);
            markers.add(new MapMarker(12.95 + i * 0.001, 77.65 + i * 0.001, "Hospital " + i, style));
        }

        // --- Schools (75 markers, all sharing ONE MarkerStyle) ---
        for (int i = 0; i < 75; i++) {
            MarkerStyle style = MarkerStyleFactory.getStyle("green", "/icons/school.png", 20);
            markers.add(new MapMarker(13.00 + i * 0.001, 77.70 + i * 0.001, "School " + i, style));
        }

        // --- Parks (60 markers, all sharing ONE MarkerStyle) ---
        for (int i = 0; i < 60; i++) {
            MarkerStyle style = MarkerStyleFactory.getStyle("darkgreen", "/icons/park.png", 22);
            markers.add(new MapMarker(13.05 + i * 0.001, 77.75 + i * 0.001, "Park " + i, style));
        }

        return markers;
    }
}

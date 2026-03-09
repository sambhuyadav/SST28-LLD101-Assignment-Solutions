package com.example.map;

import java.util.List;

/**
 * Entry point for the demo.
 */
public class App {

    public static void main(String[] args) {
        List<MapMarker> markers = MapDataSource.loadMarkers();

        MapRenderer.renderAll(markers);

        System.out.println("\nFactory created " + MarkerStyleFactory.getCacheSize()
                + " unique style object(s) for " + markers.size() + " markers.");
        System.out.println("Run QuickCheck to verify Flyweight sharing:");
        System.out.println("  java com.example.map.QuickCheck");
    }
}

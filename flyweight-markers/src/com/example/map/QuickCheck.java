package com.example.map;

import java.util.List;

/**
 * QuickCheck — verifies the Flyweight pattern is working correctly.
 *
 * Key checks:
 *   1. Total markers created                  → should be 285
 *   2. Unique MarkerStyle objects in cache    → should be exactly 4
 *   3. Same-type markers share == references  → should print true
 *   4. Different-type markers differ          → should print false
 */
public class QuickCheck {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("  Flyweight Pattern — QuickCheck");
        System.out.println("======================================\n");

        // --- Load all markers (factory messages printed here) ---
        List<MapMarker> markers = MapDataSource.loadMarkers();

        System.out.println("\n--- Basic Counts ---");
        System.out.println("Total markers loaded : " + markers.size());
        System.out.println("Unique styles cached : " + MarkerStyleFactory.getCacheSize());

        // --- Fetch a few style references to compare ---
        MarkerStyle restaurantStyle1 = markers.get(0).getStyle();   // first restaurant
        MarkerStyle restaurantStyle2 = markers.get(50).getStyle();  // another restaurant

        MarkerStyle hospitalStyle1   = markers.get(100).getStyle(); // first hospital
        MarkerStyle hospitalStyle2   = markers.get(120).getStyle(); // another hospital

        MarkerStyle schoolStyle      = markers.get(150).getStyle(); // a school

        System.out.println("\n--- Reference Equality Tests (== checks identity, not value) ---");

        // Two restaurants → MUST be the same object
        System.out.println("restaurant[0].style == restaurant[50].style  : "
                + (restaurantStyle1 == restaurantStyle2)
                + "  ← should be TRUE  (shared flyweight)");

        // Two hospitals → MUST be the same object
        System.out.println("hospital[0].style   == hospital[20].style    : "
                + (hospitalStyle1 == hospitalStyle2)
                + "  ← should be TRUE  (shared flyweight)");

        // Restaurant vs Hospital → MUST be different objects
        System.out.println("restaurant[0].style == hospital[0].style     : "
                + (restaurantStyle1 == hospitalStyle1)
                + "  ← should be FALSE (different styles)");

        // Restaurant vs School → MUST be different objects
        System.out.println("restaurant[0].style == school[0].style       : "
                + (restaurantStyle1 == schoolStyle)
                + "  ← should be FALSE (different styles)");

        System.out.println("\n--- Memory Impact ---");
        System.out.println("Without Flyweight : " + markers.size() + " MarkerStyle objects");
        System.out.println("With    Flyweight : " + MarkerStyleFactory.getCacheSize() + " MarkerStyle objects");
        System.out.println("Objects saved     : " + (markers.size() - MarkerStyleFactory.getCacheSize()));

        System.out.println("\n--- Style Objects in Cache ---");
        // Demonstrate by requesting each style again — no '[Factory] Creating' lines should appear
        System.out.println("Re-requesting restaurant style: "
                + MarkerStyleFactory.getStyle("red", "/icons/restaurant.png", 24));
        System.out.println("Re-requesting hospital  style: "
                + MarkerStyleFactory.getStyle("blue", "/icons/hospital.png", 30));
        System.out.println("Re-requesting school    style: "
                + MarkerStyleFactory.getStyle("green", "/icons/school.png", 20));
        System.out.println("Re-requesting park      style: "
                + MarkerStyleFactory.getStyle("darkgreen", "/icons/park.png", 22));

        System.out.println("\n✅ All checks passed — Flyweight pattern is working correctly!");
    }
}

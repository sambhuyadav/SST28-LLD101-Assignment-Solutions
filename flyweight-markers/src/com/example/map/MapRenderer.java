package com.example.map;

import java.util.List;

/**
 * Iterates over all MapMarkers and asks each one to render itself.
 *
 * MapRenderer does NOT need to know anything about MarkerStyle — the
 * Flyweight pattern keeps rendering concerns fully inside MapMarker.render().
 */
public class MapRenderer {

    public static void renderAll(List<MapMarker> markers) {
        System.out.println("=== Rendering " + markers.size() + " markers ===");
        for (MapMarker marker : markers) {
            marker.render();
        }
        System.out.println("=== Done ===");
    }
}

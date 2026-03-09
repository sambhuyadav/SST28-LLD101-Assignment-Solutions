package com.example.map;

/**
 * Flyweight Object — holds INTRINSIC (shared, immutable) state.
 *
 * Why immutable?
 *   Because this single object will be shared by potentially thousands of
 *   MapMarker instances. If any one marker could mutate it, every marker
 *   sharing the same style would be silently corrupted.
 *
 * Immutability is enforced by:
 *   1. All fields are declared `final`.
 *   2. No setters are provided.
 *   3. The class itself is declared `final` so no subclass can re-open it.
 */
public final class MarkerStyle {

    // --- Intrinsic State (shared across many MapMarkers) ---
    private final String color;
    private final String iconPath;
    private final int size;

    public MarkerStyle(String color, String iconPath, int size) {
        this.color    = color;
        this.iconPath = iconPath;
        this.size     = size;
    }

    // Read-only accessors — no setters!
    public String getColor()    { return color;    }
    public String getIconPath() { return iconPath; }
    public int    getSize()     { return size;     }

    @Override
    public String toString() {
        return "MarkerStyle{color='" + color + "', iconPath='" + iconPath + "', size=" + size + "}";
    }
}

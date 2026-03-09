package com.example.map;

// Context object — holds extrinsic (per-instance) state.
// Intrinsic state (style) is injected, not created here.
public class MapMarker {

    private final double latitude;
    private final double longitude;
    private final String label;
    private final MarkerStyle style; // shared flyweight

    public MapMarker(double latitude, double longitude, String label, MarkerStyle style) {
        this.latitude  = latitude;
        this.longitude = longitude;
        this.label     = label;
        this.style = style; // injected — NOT created here
    }

    public void render() {
        System.out.println("Rendering '" + label + "' at (" + latitude + ", " + longitude + ")"
                + " | style=" + style
                + " | styleRef=@" + Integer.toHexString(System.identityHashCode(style)));
    }

    // --- Accessors (needed by MapRenderer / QuickCheck) ---
    public double     getLatitude()  { return latitude;  }
    public double     getLongitude() { return longitude; }
    public String     getLabel()     { return label;     }
    public MarkerStyle getStyle()    { return style;     }
}

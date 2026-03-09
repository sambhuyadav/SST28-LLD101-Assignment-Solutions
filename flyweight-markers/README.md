# Flyweight Design Pattern — Map Markers

## 🗺️ Problem Statement

A mapping application renders **285 markers** on a map (restaurants, hospitals, schools, parks).  
Before the fix, every single `MapMarker` created its own `MarkerStyle` object even though all markers of the same category share identical visual properties (color, icon, size).

**Result without Flyweight:** 285 `MarkerStyle` objects in memory — mostly duplicates.  
**Result with Flyweight:** 4 `MarkerStyle` objects in memory — one per unique category.

---

## 🧠 Core Concept: Intrinsic vs Extrinsic State

| | Intrinsic State | Extrinsic State |
|---|---|---|
| **Definition** | Shared, immutable, context-free | Unique per object, passed at runtime |
| **Stored in** | `MarkerStyle` (the Flyweight) | `MapMarker` (the Context object) |
| **Examples** | `color`, `iconPath`, `size` | `latitude`, `longitude`, `label` |
| **Created by** | `MarkerStyleFactory` (once, cached) | `MapDataSource` (per marker) |

**The rule:** Anything that is the same across many objects → move it into the Flyweight.  
Anything that is different per object → keep it in the Context (MapMarker).

---

## 🏗️ Architecture

```
MapDataSource
    │
    │ requests style via
    ▼
MarkerStyleFactory ──── HashMap cache ────► MarkerStyle (Flyweight)
    │                                            ▲
    │ returns shared reference                   │ shared reference
    ▼                                            │
MapMarker (Context) ─────────────────────────────┘
    │ lat, lng, label (extrinsic)
    │ style ref       (intrinsic, injected)
    ▼
MapRenderer.renderAll()
```

---

## 📁 Class Responsibilities

### `MarkerStyle` — The Flyweight Object
- Holds **intrinsic state** only: `color`, `iconPath`, `size`
- Declared `final` (no subclassing)
- All fields `final` (no mutation after construction)
- No setters — completely immutable
- Safe to share across thousands of `MapMarker` instances

### `MarkerStyleFactory` — The Flyweight Factory
- Maintains a `static HashMap<String, MarkerStyle>` as the flyweight pool
- Cache key = `"color|iconPath|size"` — unique per visual style
- Uses `computeIfAbsent` — creates the object only on first request
- Subsequent requests for the same style return the **same object reference**
- `getCacheSize()` helper exposed for verification

### `MapMarker` — The Context Object
- Holds **extrinsic state**: `latitude`, `longitude`, `label`
- Receives `MarkerStyle` via constructor injection — never creates one itself
- `render()` prints identity hash code to prove sharing

### `MapDataSource` — The Client
- Calls `MarkerStyleFactory.getStyle(...)` instead of `new MarkerStyle(...)`
- Passes the returned (possibly cached) style into each `new MapMarker(...)`

### `MapRenderer` — The Renderer
- Simply iterates and calls `marker.render()`
- Has zero knowledge of `MarkerStyle` — clean separation of concerns

### `QuickCheck` — The Verifier
- Uses `==` (reference equality) to prove two markers of the same type
  share the **exact same object** in memory
- Prints before/after object counts to show memory improvement

---

## ✅ Expected QuickCheck Output

```
======================================
  Flyweight Pattern — QuickCheck
======================================

[Factory] Creating NEW MarkerStyle for key: red|/icons/restaurant.png|24
[Factory] Creating NEW MarkerStyle for key: blue|/icons/hospital.png|30
[Factory] Creating NEW MarkerStyle for key: green|/icons/school.png|20
[Factory] Creating NEW MarkerStyle for key: darkgreen|/icons/park.png|22

--- Basic Counts ---
Total markers loaded : 285
Unique styles cached : 4

--- Reference Equality Tests (== checks identity, not value) ---
restaurant[0].style == restaurant[50].style  : true  ← should be TRUE  (shared flyweight)
hospital[0].style   == hospital[20].style    : true  ← should be TRUE  (shared flyweight)
restaurant[0].style == hospital[0].style     : false ← should be FALSE (different styles)
restaurant[0].style == school[0].style       : false ← should be FALSE (different styles)

--- Memory Impact ---
Without Flyweight : 285 MarkerStyle objects
With    Flyweight : 4   MarkerStyle objects
Objects saved     : 281

--- Style Objects in Cache ---
Re-requesting restaurant style: MarkerStyle{color='red', iconPath='/icons/restaurant.png', size=24}
Re-requesting hospital  style: MarkerStyle{color='blue', iconPath='/icons/hospital.png', size=30}
Re-requesting school    style: MarkerStyle{color='green', iconPath='/icons/school.png', size=20}
Re-requesting park      style: MarkerStyle{color='darkgreen', iconPath='/icons/park.png', size=22}

✅ All checks passed — Flyweight pattern is working correctly!
```

**Key observations:**
1. `[Factory] Creating NEW` appears exactly **4 times** — once per unique style, despite 285 markers
2. The `==` checks return `true` for same-category markers — they are literally the **same Java object**
3. Re-requesting styles at the bottom produces **no** `[Factory] Creating` lines — the cache is hit
4. **281 objects saved** (98.6% reduction in style object count)

---

## 💾 Memory Improvement Analysis

Assume each `MarkerStyle` object takes ~100 bytes (3 fields + object header):

| Scenario | Style Objects | Memory for Styles |
|---|---|---|
| **Without Flyweight** | 285 | ~27,750 bytes |
| **With Flyweight** | 4 | ~400 bytes |
| **Savings** | 281 | ~27,350 bytes (98.6%) |

In a real map app with **1,000,000 markers** and 20 categories:

| | Without | With |
|---|---|---|
| Style Objects | 1,000,000 | 20 |
| Memory | ~95 MB | ~1.9 KB |

---

## 🔑 Why This Is the Flyweight Pattern

1. **Shared object pool** — `MarkerStyleFactory` ensures at most one instance per unique style
2. **Immutable flyweight** — `MarkerStyle` cannot be modified, making sharing safe
3. **Intrinsic/extrinsic separation** — visual properties live in the flyweight; positional/label data stays in the context
4. **Transparent to the renderer** — `MapRenderer` doesn't know or care about sharing; it just calls `render()`

---

## 🚀 How to Run

```bash
# Compile
javac -d out src/com/example/map/*.java

# Run QuickCheck
java -cp out com.example.map.QuickCheck

# Run full app
java -cp out com.example.map.App
```

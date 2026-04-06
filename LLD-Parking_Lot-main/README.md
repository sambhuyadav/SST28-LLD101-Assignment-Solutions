# Multilevel Parking Lot System – Design & Implementation

## Overview

This project implements a **Multilevel Parking Lot System** using clean object-oriented design principles and industry-standard patterns.

It supports:

* Multiple levels
* Multiple entry gates
* Different slot types (SMALL, MEDIUM, LARGE)
* Nearest slot allocation
* Pricing strategies
* Thread-safe slot assignment
* Optimized lookup using TreeSet

---

# Design Goals

* Scalability (multiple levels & gates)
* Extensibility (new slot types, pricing, strategies)
* Performance (O(log n) slot allocation)
* Thread Safety (no double allocation)
* Clean architecture (SOLID principles)

---

# Core Components

## 1. ParkingLot

* Acts as the **main container**
* Holds:

  * Levels
  * Entry Gates
* Responsibilities:

  * Delegate parking to EntryGate
  * Handle exit & pricing

---

## 2. EntryGate

* Entry point for vehicles
* Responsibilities:

  * Find nearest slot using strategy
  * Assign slot
  * Generate ticket (via factory)

---

## 3. Level

* Contains multiple parking slots
* Maintains:

  ```
  Map<SlotType, Map<GateId, TreeSet<ParkingSlot>>>
  ```
* Responsibilities:

  * Provide nearest slot per gate & type
  * Maintain sorted available slots

---

## 4. ParkingSlot

* Represents a single parking space
* Fields:

  * id
  * type
  * availability
  * distance from gates
* Responsibilities:

  * Thread-safe allocation (locking)
  * Freeing slot

---

## 5. Vehicle

* Represents incoming vehicle
* Contains:

  * number
  * slot type requirement

---

## 6. Ticket

* Generated on parking
* Contains:

  * entry time
  * slot
  * vehicle
  * gate id

---

## 7. TicketFactory

* Responsible for creating Ticket objects
* Helps maintain SRP and extensibility

---

## 8. SlotAllocationStrategy (Strategy Pattern)

* Determines how slot is allocated

### Current Implementation:

* `NearestSlotStrategy`

### Future Extensions:

* VIPPriorityStrategy
* FirstAvailableStrategy
* ElectricVehicleStrategy

---

## 9. PricingStrategy (Strategy Pattern)

* Determines pricing logic

### Implementations:

* SmallPricing
* MediumPricing
* LargePricing

---

# Key Design Decisions

---

## 1. TreeSet for Optimization

* Each level maintains slots in a **TreeSet sorted by distance**
* Complexity:

  * Insert: O(log n)
  * Remove: O(log n)
  * Nearest lookup: O(1)

---

## 2. Slot-Level Locking (Concurrency)

* Each slot has its own lock
* Ensures:

  * No double allocation
  * Thread safety

---

## 3. Strategy Pattern

Used for:

* Slot allocation
* Pricing

Enables:

* Easy extension
* No modification of existing code

---

## 4. Factory Pattern

Used for:

* Ticket creation

Keeps creation logic separate and clean

---

# UML Class Diagram

```
+---------------------+
|    ParkingLot       |
+---------------------+
| levels              |
| gates               |
+---------------------+
| park()              |
| exit()              |
+---------------------+

        ◆
        |
+---------------------+
|       Level         |
+---------------------+
| slotMap             |
+---------------------+
| getNearestSlot()    |
| addSlot()           |
| removeSlot()        |
+---------------------+

        ◆
        |
+---------------------+
|   ParkingSlot       |
+---------------------+
| id                  |
| type                |
| isAvailable         |
| distanceMap         |
+---------------------+
| assignVehicle()     |
| freeSlot()          |
+---------------------+

+---------------------+
|     EntryGate       |
+---------------------+
| id                  |
| strategy            |
+---------------------+
| park()              |
+---------------------+

+---------------------+
|      Vehicle        |
+---------------------+

+---------------------+
|      Ticket         |
+---------------------+

+-----------------------------+
| SlotAllocationStrategy      |
+-----------------------------+
| findSlot()                 |
+-----------------------------+

        ▲
        |
+-----------------------------+
| NearestSlotStrategy         |
+-----------------------------+

+-----------------------------+
| PricingStrategy             |
+-----------------------------+
| calculate()                |
+-----------------------------+

        ▲
        |
+---------------------+   +---------------------+   +---------------------+
| SmallPricing        |   | MediumPricing       |   | LargePricing        |
+---------------------+   +---------------------+   +---------------------+

+---------------------+
|   TicketFactory     |
+---------------------+
| create()            |
+---------------------+
```

---

# System Flow

---

## Parking Flow

```
User → ParkingLot.park(vehicle, gateId)
     → EntryGate.park(vehicle)
     → SlotAllocationStrategy.findSlot()
     → Level.getNearestSlot()
     → ParkingSlot.assignVehicle()
     → TicketFactory.create()
     → Ticket returned
```

---

## Exit Flow

```
User → ParkingLot.exit(ticket)
     → PricingStrategyFactory.get()
     → calculate price
     → ParkingSlot.freeSlot()
     → slot added back to TreeSet
```
---

# Getting Started

## Compilation and Execution

To compile and run the `Main.java` class, open your terminal, navigate to the directory containing the `MultiLevel_Parking_Lot` folder, and execute the following commands:

```bash
# Compile all Java files
javac MultiLevel_Parking_Lot/*.java

# Run the Main class
java MultiLevel_Parking_Lot.Main
```

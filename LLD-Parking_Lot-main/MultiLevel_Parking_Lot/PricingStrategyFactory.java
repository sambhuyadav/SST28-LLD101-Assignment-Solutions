package MultiLevel_Parking_Lot;

public class PricingStrategyFactory {
    public static PricingStrategy get(SlotType type) {
        switch (type) {
            case SMALL: return new SmallPricing();
            case MEDIUM: return new MediumPricing();
            case LARGE: return new LargePricing();
            default: throw new RuntimeException("Invalid type");
        }
    }    
}
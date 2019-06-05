package Adventurer.relics.Novice;

import Adventurer.relics.AdventurerRelic;

public class MerchantNovice  extends AdventurerRelic {
    public static final String ID = "Adventurer:MerchantNovice";
    private static final String IMG = "AdventurerResources/images/relics/Merchant.png";
    private static final String IMG_OTL = "AdventurerResources/images/relics/outline/ClassRelic.png";

    public MerchantNovice() {
        super(ID,  IMG, IMG_OTL, RelicTier.SHOP, LandingSound.MAGICAL);
    }
}

package Adventurer.relics.Novice;

import Adventurer.relics.AdventurerRelic;

public class AdventurerNovice extends AdventurerRelic {

    public static final String ID = "Adventurer:AdventurerNovice";
    private static final String IMG = "AdventurerResources/images/relics/Novice.png";
    private static final String IMG_OTL = "AdventurerResources/images/relics/outline/ClassRelic.png";

    public AdventurerNovice() {
        super(ID,  IMG, IMG_OTL, RelicTier.STARTER, LandingSound.MAGICAL);
    }


}


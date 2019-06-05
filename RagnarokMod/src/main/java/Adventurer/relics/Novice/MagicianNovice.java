package Adventurer.relics.Novice;

import Adventurer.relics.AdventurerRelic;

public class MagicianNovice  extends AdventurerRelic {

    public static final String ID = "Adventurer:MagicianNovice";
    private static final String IMG = "AdventurerResources/images/relics/Magician.png";
    private static final String IMG_OTL = "AdventurerResources/images/relics/outline/ClassRelic.png";

    public MagicianNovice() {
        super(ID,  IMG, IMG_OTL, RelicTier.BOSS, LandingSound.MAGICAL);
    }
}

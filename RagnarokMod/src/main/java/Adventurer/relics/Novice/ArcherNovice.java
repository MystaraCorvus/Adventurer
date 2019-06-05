package Adventurer.relics.Novice;

import Adventurer.relics.AdventurerRelic;

public class ArcherNovice  extends AdventurerRelic {

    public static final String ID = "Adventurer:ArcherNovice";
    private static final String IMG = "AdventurerResources/images/relics/Archer.png";
    private static final String IMG_OTL = "AdventurerResources/images/relics/outline/ClassRelic.png";

    public ArcherNovice() {
        super(ID,  IMG, IMG_OTL, RelicTier.BOSS, LandingSound.MAGICAL);
    }
}

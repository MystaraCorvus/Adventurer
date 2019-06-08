package Adventurer.relics.Novice;

import Adventurer.relics.AdventurerRelic;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class SwordsmanNovice extends AdventurerRelic {

    public static final String ID = "Adventurer:SwordsmanNovice";
    private static final String IMG = "AdventurerResources/images/relics/Swordsman.png";
    private static final String IMG_OTL = "AdventurerResources/images/relics/outline/ClassRelic.png";

    public SwordsmanNovice() {
        super(ID,  IMG, IMG_OTL, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    public void onEquip() {
        super.onEquip();
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new SwordsmanNovice();
    }

}

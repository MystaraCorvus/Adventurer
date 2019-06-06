package Adventurer.relics.Novice;

import Adventurer.relics.AdventurerRelic;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class AcolyteNovice  extends AdventurerRelic {

    public static final String ID = "Adventurer:AcolyteNovice";
    private static final String IMG = "AdventurerResources/images/relics/Acolyte.png";
    private static final String IMG_OTL = "AdventurerResources/images/relics/outline/ClassRelic.png";

    public AcolyteNovice() {
        super(ID,  IMG, IMG_OTL, RelicTier.SPECIAL, LandingSound.MAGICAL);
    }


    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new AcolyteNovice();
    }
}

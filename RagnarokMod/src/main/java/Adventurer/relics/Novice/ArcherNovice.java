package Adventurer.relics.Novice;

import Adventurer.relics.AdventurerRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ArcherNovice  extends AdventurerRelic {

    public static final String ID = "Adventurer:ArcherNovice";
    private static final String IMG = "AdventurerResources/images/relics/Archer.png";
    private static final String IMG_OTL = "AdventurerResources/images/relics/outline/ClassRelic.png";

    public ArcherNovice() {
        super(ID,  IMG, IMG_OTL, RelicTier.BOSS, LandingSound.MAGICAL);
    }
    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new ArcherNovice();
    }

}

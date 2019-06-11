package Adventurer.relics.Novice;

import Adventurer.cards.*;
import Adventurer.relics.AdventurerRelic;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class AcolyteNovice  extends AdventurerRelic {

    public static final String ID = "Adventurer:AcolyteNovice";
    private static final String IMG = "AdventurerResources/images/relics/Acolyte.png";
    private static final String IMG_OTL = "AdventurerResources/images/relics/outline/ClassRelic.png";

    public CardGroup DECK = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    public AcolyteNovice() {
        super(ID, IMG, IMG_OTL, RelicTier.SPECIAL, LandingSound.MAGICAL);
        this.DECK.group.add(new Strike_Acolyte());
        this.DECK.group.add(new Strike_Acolyte());
        this.DECK.group.add(new Strike_Acolyte());
        this.DECK.group.add(new Strike_Acolyte());
        this.DECK.group.add(new Defend_Acolyte());
        this.DECK.group.add(new Defend_Acolyte());
        this.DECK.group.add(new Defend_Acolyte());
        this.DECK.group.add(new Defend_Acolyte());
    }

    public void onEquip() {
        super.onEquip();
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new AcolyteNovice();
    }
}

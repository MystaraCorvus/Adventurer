package Adventurer.relics.Novice;

import Adventurer.cards.*;
import Adventurer.relics.AdventurerRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class ArcherNovice  extends AdventurerRelic {

    public static final String ID = "Adventurer:ArcherNovice";
    private static final String IMG = "AdventurerResources/images/relics/Archer.png";
    private static final String IMG_OTL = "AdventurerResources/images/relics/outline/ClassRelic.png";

    public CardGroup DECK = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    public ArcherNovice() {
        super(ID, IMG, IMG_OTL, RelicTier.BOSS, LandingSound.MAGICAL);
        this.DECK.group.add(new Strike_Archer());
        this.DECK.group.add(new Strike_Archer());
        this.DECK.group.add(new Strike_Archer());
        this.DECK.group.add(new Strike_Archer());
        this.DECK.group.add(new Defend_Archer());
        this.DECK.group.add(new Defend_Archer());
        this.DECK.group.add(new Defend_Archer());
        this.DECK.group.add(new Defend_Archer());
    }

    public void onEquip() {
        super.onEquip();
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new ArcherNovice();
    }

}

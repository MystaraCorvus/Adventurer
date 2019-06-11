package Adventurer.relics.Novice;

import Adventurer.cards.*;
import Adventurer.relics.AdventurerRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class ThiefNovice extends AdventurerRelic {

    public static final String ID = "Adventurer:ThiefNovice";
    private static final String IMG = "AdventurerResources/images/relics/Thief.png";
    private static final String IMG_OTL = "AdventurerResources/images/relics/outline/ClassRelic.png";

    public CardGroup DECK = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    public ThiefNovice() {
        super(ID, IMG, IMG_OTL, RelicTier.BOSS, LandingSound.MAGICAL);
        this.DECK.group.add(new Strike_Thief());
        this.DECK.group.add(new Strike_Thief());
        this.DECK.group.add(new Strike_Thief());
        this.DECK.group.add(new Strike_Thief());
        this.DECK.group.add(new Defend_Thief());
        this.DECK.group.add(new Defend_Thief());
        this.DECK.group.add(new Defend_Thief());
        this.DECK.group.add(new Defend_Thief());
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new ThiefNovice();
    }

    public void atTurnStart() {
        //AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 2));
    }

    public void onEquip() {
        super.onEquip();
    }

    public void onUnequip() {
       // --AbstractDungeon.player.energy.energyMaster;
    }

}

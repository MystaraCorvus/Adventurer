package Adventurer.relics.Novice;

import Adventurer.cards.ShieldBash;
import Adventurer.relics.AdventurerRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ThiefNovice extends AdventurerRelic {

    public static final String ID = "Adventurer:ThiefNovice";
    private static final String IMG = "AdventurerResources/images/relics/Thief.png";
    private static final String IMG_OTL = "AdventurerResources/images/relics/outline/ClassRelic.png";

    public CardGroup thiefDeck = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    //private int count = 0;

    public ThiefNovice() {
        super(ID,  IMG, IMG_OTL, RelicTier.BOSS, LandingSound.MAGICAL);


        thiefDeck.group.add(new ShieldBash().makeCopy());
        thiefDeck.group.add(new ShieldBash().makeCopy());
        thiefDeck.group.add(new ShieldBash().makeCopy());
        thiefDeck.group.add(new ShieldBash().makeCopy());
        thiefDeck.group.add(new ShieldBash().makeCopy());
        thiefDeck.group.add(new ShieldBash().makeCopy());
        thiefDeck.group.add(new ShieldBash().makeCopy());
        thiefDeck.group.add(new ShieldBash().makeCopy());
        thiefDeck.group.add(new ShieldBash().makeCopy());
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
        SwitchClassDeck(thiefDeck);
    }

    public void onUnequip() {
       // --AbstractDungeon.player.energy.energyMaster;
    }

}

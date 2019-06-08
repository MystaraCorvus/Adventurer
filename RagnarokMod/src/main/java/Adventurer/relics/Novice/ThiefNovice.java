package Adventurer.relics.Novice;

import Adventurer.cards.ShieldBash;
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

        DECK.group.add(new ShieldBash().makeCopy());
        DECK.group.add(new ShieldBash().makeCopy());
        DECK.group.add(new ShieldBash().makeCopy());
        DECK.group.add(new ShieldBash().makeCopy());
        DECK.group.add(new ShieldBash().makeCopy());
        DECK.group.add(new ShieldBash().makeCopy());
        DECK.group.add(new ShieldBash().makeCopy());
        DECK.group.add(new ShieldBash().makeCopy());
        DECK.group.add(new ShieldBash().makeCopy());
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
        for (int j = 0; j < DECK.group.size(); j++) {
            //group.group.get(j).upgrade();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(DECK.group.get(j), (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
        }
    }

    public void onUnequip() {
       // --AbstractDungeon.player.energy.energyMaster;
    }

}

package Adventurer.relics.Novice;

import Adventurer.cards.LesserLightningBolt;
import Adventurer.cards.ShieldBash;
import Adventurer.relics.AdventurerRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;

public class ThiefNovice extends AdventurerRelic {

    public static final String ID = "Adventurer:ThiefNovice";
    private static final String IMG = "AdventurerResources/images/relics/Thief.png";
    private static final String IMG_OTL = "AdventurerResources/images/relics/outline/ClassRelic.png";

    private static ArrayList<AbstractCard> thiefDeck;

    public ThiefNovice() {
        super(ID,  IMG, IMG_OTL, RelicTier.BOSS, LandingSound.MAGICAL);
        thiefDeck.add(new LesserLightningBolt());

    }
    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new ThiefNovice();
    }

    public void atTurnStart() {
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 2));
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new ShieldBash(), (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));

        AbstractDungeon.topLevelEffects.add(new PurgeCardEffect((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0), (float)Settings.WIDTH / 2.0F - 30.0F * Settings.scale - AbstractCard.IMG_WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        AbstractDungeon.topLevelEffects.add(new PurgeCardEffect((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(1), (float)Settings.WIDTH / 2.0F + 30.0F * Settings.scale + AbstractCard.IMG_WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;

    }

}

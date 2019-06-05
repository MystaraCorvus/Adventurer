package Adventurer.powers;

import Adventurer.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

import static Adventurer.AdventurerMod.makeID;
import static Adventurer.AdventurerMod.makePowerPath;

public class AdventurerFormPower
        extends AdventurerPower implements CloneablePowerInterface
{
    public static final String thisName = "AdventurerForm";

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath(thisName + "84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath(thisName + "32.png"));

    public AdventurerFormPower(final AbstractCreature owner, final AbstractCreature source, final int amount)
    {
        super(thisName, owner, source, amount, tex84, tex32);
    }

    public void updateDescription()
    {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }

    public void onInitialApplication()
    {
        flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new TemporarilyLevelUpPower(this.owner, this.owner, this.amount), this.amount));
    }

    public void atStartOfTurnPostDraw()
    {
        flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new TemporarilyLevelUpPower(this.owner, this.owner, this.amount), this.amount));
    }

    @Override
    public AbstractPower makeCopy() {
        return new AdventurerFormPower(owner, source, amount);
    }
}

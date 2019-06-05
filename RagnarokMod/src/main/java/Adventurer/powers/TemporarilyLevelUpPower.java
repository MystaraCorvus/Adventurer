package Adventurer.powers;

import Adventurer.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Adventurer.AdventurerMod.makeID;
import static Adventurer.AdventurerMod.makePowerPath;

public class TemporarilyLevelUpPower
        extends AdventurerPower
{
    public static final String thisNAME = "TemporarilyLevelUp";

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath(thisNAME + "84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath(thisNAME + "32.png"));

    public TemporarilyLevelUpPower (final AbstractCreature owner, final AbstractCreature source, final int amount)
    {
        super(thisNAME, owner, source, amount, tex84, tex32);
        this.canGoNegative = true;
    }

    public void playApplyPowerSfx()
    {
        CardCrawlGame.sound.play("POWER_DEXTERITY", 0.05F);
    }

    public void stackPower(int stackAmount)
    {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, makeID(TemporarilyLevelUpPower.ME)));
        }
        if (this.amount >= 999) {
            this.amount = 999;
        }
    }

    public void reducePower(int reduceAmount)
    {
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "TemporarilyLevelUpPower"));
        }
        if (this.amount >= 999) {
            this.amount = 999;
        }
    }

    public void updateDescription()
    {
        if (this.amount > 0)
        {
            this.description = (DESCRIPTIONS[0]);
            this.type = AbstractPower.PowerType.BUFF;
        }
        else
        {
            this.description = (DESCRIPTIONS[0]);
            this.type = AbstractPower.PowerType.DEBUFF;
        }
    }
}

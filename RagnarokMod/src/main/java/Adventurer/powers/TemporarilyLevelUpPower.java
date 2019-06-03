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
        extends AbstractPower
{
    public static final String POWER_ID = makeID("TemporarilyLevelUpPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public TemporarilyLevelUpPower(AbstractCreature owner, int amount)
    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        if (this.amount >= 999) {
            this.amount = 999;
        }

        this.canGoNegative = true;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
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
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, TemporarilyLevelUpPower.POWER_ID));
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
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Dexterity"));
        }
        if (this.amount >= 999) {
            this.amount = 999;
        }
        if (this.amount <= 64537) {
            this.amount = 64537;
        }
    }

    public void updateDescription()
    {
        if (this.amount > 0)
        {
            this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2]);
            this.type = AbstractPower.PowerType.BUFF;
        }
        else
        {
            int tmp = -this.amount;
            this.description = (DESCRIPTIONS[1] + tmp + DESCRIPTIONS[2]);
            this.type = AbstractPower.PowerType.DEBUFF;
        }
    }
}

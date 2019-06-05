package Adventurer.powers;

import Adventurer.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.EverythingFix;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Adventurer.AdventurerMod.makeID;
import static Adventurer.AdventurerMod.makePowerPath;

public abstract class AdventurerPower extends AbstractPower {
    public static String ME;
    public AbstractCreature source;

    private static PowerStrings powerStrings;
    public static String[] DESCRIPTIONS;

    public AdventurerPower(final String id, final AbstractCreature owner, final AbstractCreature source, final int amount, final Texture tex84, final Texture tex32) {
        this.ME = makeID(id);
        this.ID = makeID(id);
        this.powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
        this.name = powerStrings.NAME;
        this.DESCRIPTIONS = powerStrings.DESCRIPTIONS;

        this.owner = owner;
        this.amount = amount;

        if (this.amount >= 999) {
            this.amount = 999;
        }

        this.source = source;

        if (this.amount > 0)
        {
            this.type = AbstractPower.PowerType.BUFF;
        }
        else
        {
            this.type = AbstractPower.PowerType.DEBUFF;
        }

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    public static int GetTemporarilyLevel(){
        if((AbstractDungeon.player != null) && AbstractDungeon.player.hasPower(TemporarilyLevelUpPower.ME))
        {
            return AbstractDungeon.player.getPower(TemporarilyLevelUpPower.ME).amount;
        }
        return 0;
    }
}

package Adventurer.powers;

import Adventurer.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Adventurer.AdventurerMod.makeID;
import static Adventurer.AdventurerMod.makePowerPath;

public class TakeRestPower extends AdventurerPower implements CloneablePowerInterface {

    public static final String thisNAME = "TakeRest";

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath(thisNAME + "84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath(thisNAME + "32.png"));

    public TakeRestPower (final AbstractCreature owner, final AbstractCreature source, final int amount)
    {
        super(thisNAME, owner, source, amount, tex84, tex32);
    }
    public void updateDescription()
    {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }

    public void onVictory()
    {
        CardCrawlGame.playtime += 60;
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth > 0) {
            p.heal(this.amount);
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new TakeRestPower(owner, source, amount);
    }
}

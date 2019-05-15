package Adventurer.relics;

import Adventurer.AdventurerMod;
import basemod.abstracts.CustomRelic;
import basemod.interfaces.PostCampfireSubscriber;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import Adventurer.util.TextureLoader;

import static Adventurer.AdventurerMod.makeRelicOutlinePath;
import static Adventurer.AdventurerMod.makeRelicPath;

public class PlaceholderRelic extends CustomRelic
{
    // ID, images, text.
    public static final String ID = AdventurerMod.makeID("PlaceholderRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public PlaceholderRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    // Flash at the start of Battle.
    @Override
    public void atBattleStartPreDraw() {
        flash();
    }

    // Gain 1 energy on equip.
    @Override
    public void onEquip() {
        AbstractDungeon.player.energy.energyMaster += 1;
    }

    // Lose 1 energy on unequip.
    @Override
    public void onUnequip() {
        AbstractDungeon.player.energy.energyMaster -= 1;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    public void onEnterRestRoom() {}
}

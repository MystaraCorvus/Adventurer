package Adventurer.relics;

import Adventurer.AdventurerMod;
import Adventurer.characters.Adventurer;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class AdventurerNovice extends CustomRelic {

    public static final String ID = "Adventurer:AdventurerNovice";
    private static final String IMG = "AdventurerResources/images/relics/Novice.png";
    private static final String IMG_OTL = "AdventurerResources/images/relics/outline/ClassRelic.png";

    public AdventurerNovice() {
        super(ID,  ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void onEquip() {
        this.counter = 0;
    }
    @Override
    public void onEnterRestRoom() {
        AdventurerMod.curOptionsRestRoom = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }


    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new AdventurerNovice();
    }

}


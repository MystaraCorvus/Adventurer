package Adventurer.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import Adventurer.util.ClassManager;


public class AdventurerNovice extends CustomRelic {

    public static final String ID = "Adventurer:AdventurerNovice";
    private static final String IMG = "images/relics/AdventurerNovice.png";
    private static final String IMG_OTL = "images/relics/outline/ClassRelic.png";

    public AdventurerNovice() {
        super(ID,  ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }


    @Override
    public void onEquip()
    {
        ClassManager.maxRestTimes++;
        ClassManager.finishRest();
    }

    public void unEquip()
    {
        ClassManager.maxRestTimes--;
        ClassManager.finishRest();
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
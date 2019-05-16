package Adventurer.relics;

import basemod.abstracts.CustomRelic;
import basemod.interfaces.PostCampfireSubscriber;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import Adventurer.util.ClassManager;


public class AdventurerNovice extends CustomRelic
        implements PostCampfireSubscriber {

    public static final String ID = "Adventurer:AdventurerNovice";
    private static final String IMG = "AdventurerResources/images/relics/Novice.png";
    private static final String IMG_OTL = "AdventurerResources/images/relics/outline/ClassRelic.png";
    private static int restRoomRepeatsMax = 2;
    private static int restRoomRepeats = 2;

    public AdventurerNovice() {
        super(ID,  ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }


    @Override
    public void onEquip() { }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }


    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new AdventurerNovice();
    }


    @Override
    public boolean receivePostCampfire() {
        if (restRoomRepeats > 0)
        {
            restRoomRepeats--;
            return true;
        }
        else {
            restRoomRepeats = restRoomRepeatsMax;
            return false;
        }
    }
}


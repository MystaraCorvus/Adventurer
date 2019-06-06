package Adventurer.relics;

import Adventurer.AdventurerMod;
import Adventurer.relics.Novice.*;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public abstract class AdventurerRelic extends CustomRelic {

    public AdventurerRelic(final String ID, final String IMG, final String IMG_OTL, final AbstractRelic.RelicTier TIER, AbstractRelic.LandingSound SOUND) {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), TIER, SOUND);
    }

    @Override
    public void onEquip() {
        if (counter < 0) counter = 0;
    }

    @Override
    public void onEnterRestRoom() {
        AdventurerMod.curOptionsRestRoom = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    public static boolean HasClassRelic() {
        if (AbstractDungeon.player != null) {
            if(AbstractDungeon.player.hasRelic(AdventurerNovice.ID)) {
                return true;
            }
            if(AbstractDungeon.player.hasRelic(SwordsmanNovice.ID)) {
                return true;
            }
            if(AbstractDungeon.player.hasRelic(MerchantNovice.ID)) {
                return true;
            }
            if(AbstractDungeon.player.hasRelic(MagicianNovice.ID)) {
                return true;
            }
            if(AbstractDungeon.player.hasRelic(ArcherNovice.ID)) {
                return true;
            }
            if(AbstractDungeon.player.hasRelic(AcolyteNovice.ID)) {
                return true;
            }
        }
        return false;
    }

    public static String ClassRelicID() {
        if (AbstractDungeon.player != null) {
            if (AbstractDungeon.player.hasRelic(AdventurerNovice.ID)) {
                return AdventurerNovice.ID;
            }
            if (AbstractDungeon.player.hasRelic(SwordsmanNovice.ID)) {
                return SwordsmanNovice.ID;
            }
            if (AbstractDungeon.player.hasRelic(MerchantNovice.ID)) {
                return MerchantNovice.ID;
            }
            if (AbstractDungeon.player.hasRelic(MagicianNovice.ID)) {
                return MagicianNovice.ID;
            }
            if (AbstractDungeon.player.hasRelic(ArcherNovice.ID)) {
                return ArcherNovice.ID;
            }
            if (AbstractDungeon.player.hasRelic(AcolyteNovice.ID)) {
                return AcolyteNovice.ID;
            }
        }
        return null;
    }

    public static AdventurerRelic GetClassRelic() {
        if(AbstractDungeon.player.hasRelic(AdventurerNovice.ID)) {
            return (AdventurerRelic)AbstractDungeon.player.getRelic(AdventurerNovice.ID);
        }
        if(AbstractDungeon.player.hasRelic(SwordsmanNovice.ID)) {
            return (AdventurerRelic)AbstractDungeon.player.getRelic(SwordsmanNovice.ID);
        }
        if(AbstractDungeon.player.hasRelic(MerchantNovice.ID)) {
            return (AdventurerRelic)AbstractDungeon.player.getRelic(MerchantNovice.ID);
        }
        if(AbstractDungeon.player.hasRelic(MagicianNovice.ID)) {
            return (AdventurerRelic)AbstractDungeon.player.getRelic(MagicianNovice.ID);
        }
        if(AbstractDungeon.player.hasRelic(ArcherNovice.ID)) {
            return (AdventurerRelic)AbstractDungeon.player.getRelic(ArcherNovice.ID);
        }
        if(AbstractDungeon.player.hasRelic(AcolyteNovice.ID)) {
            return (AdventurerRelic)AbstractDungeon.player.getRelic(AcolyteNovice.ID);
        }
        return null;
    }

    public static int GetLevel() {
        if((AbstractDungeon.player != null)) {
            if (AbstractDungeon.player.hasRelic(AdventurerNovice.ID)) {
                return AbstractDungeon.player.getRelic(AdventurerNovice.ID).counter;
            }
            if (AbstractDungeon.player.hasRelic(SwordsmanNovice.ID)) {
                return AbstractDungeon.player.getRelic(SwordsmanNovice.ID).counter;
            }
            if (AbstractDungeon.player.hasRelic(MerchantNovice.ID)) {
                return AbstractDungeon.player.getRelic(MerchantNovice.ID).counter;
            }
            if (AbstractDungeon.player.hasRelic(MagicianNovice.ID)) {
                return AbstractDungeon.player.getRelic(MagicianNovice.ID).counter;
            }
            if (AbstractDungeon.player.hasRelic(ArcherNovice.ID)) {
                return AbstractDungeon.player.getRelic(ArcherNovice.ID).counter;
            }
            if (AbstractDungeon.player.hasRelic(AcolyteNovice.ID)) {
                return AbstractDungeon.player.getRelic(AcolyteNovice.ID).counter;
            }
        }
        return 0;
    }

}

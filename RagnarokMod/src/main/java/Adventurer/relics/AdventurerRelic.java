package Adventurer.relics;

import Adventurer.AdventurerMod;
import Adventurer.cards.Defend_Adventurer;
import Adventurer.cards.Strike_Adventurer;
import Adventurer.relics.Novice.*;
import Adventurer.util.AdventurerTag;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public abstract class AdventurerRelic extends CustomRelic {

    //private int count = 0;

    public AdventurerRelic(final String ID, final String IMG, final String IMG_OTL, final AbstractRelic.RelicTier TIER, AbstractRelic.LandingSound SOUND) {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), TIER, SOUND);
    }

    @Override
    public void onEquip() {
        if (counter < 0) counter = 0;
        AddCard();
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
            if (AbstractDungeon.player.hasRelic(ThiefNovice.ID)) {
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
            if (AbstractDungeon.player.hasRelic(ThiefNovice.ID)) {
                return ThiefNovice.ID;
            }
        }
        return null;
    }

    public static AdventurerRelic GetClassRelic() {
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
        if(AbstractDungeon.player.hasRelic(AdventurerNovice.ID)) {
            return (AdventurerRelic)AbstractDungeon.player.getRelic(AdventurerNovice.ID);
        }
        if (AbstractDungeon.player.hasRelic(ThiefNovice.ID)) {
            return (AdventurerRelic)AbstractDungeon.player.getRelic(ThiefNovice.ID);
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
            if (AbstractDungeon.player.hasRelic(ThiefNovice.ID)) {
                return AbstractDungeon.player.getRelic(ThiefNovice.ID).counter;
            }
        }
        return 0;
    }

    public static ArrayList<String> NotOwnedRelic() {
        ArrayList<String> relics = new ArrayList<String>();
        if(AbstractDungeon.player != null) {
            if (!AbstractDungeon.player.hasRelic(SwordsmanNovice.ID)) {
                relics.add(SwordsmanNovice.ID);
            }
            if (!AbstractDungeon.player.hasRelic(MagicianNovice.ID)) {
                relics.add(MagicianNovice.ID);
            }
            if (!AbstractDungeon.player.hasRelic(ArcherNovice.ID)) {
                relics.add(ArcherNovice.ID);
            }
            if (!AbstractDungeon.player.hasRelic(ThiefNovice.ID)) {
                relics.add(ArcherNovice.ID);
            }
        }
        return relics;
    }

    @Override
    public void obtain() {
        if(ClassRelicID() != null) {
            if (AbstractDungeon.player.hasRelic(ClassRelicID())) {
                this.counter = GetClassRelic().counter;
                for (Iterator i = AbstractDungeon.player.masterDeck.group.iterator(); i.hasNext(); ) {
                    AbstractCard e = (AbstractCard)i.next();
                    if (!(e.tags.contains(AdventurerTag.STAY))) {
                        i.remove();
                    }
                }
                for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                    if (AbstractDungeon.player.relics.get(i).relicId.equals(ClassRelicID())) {
                        this.instantObtain(AbstractDungeon.player, i, false);
                        break;
                    }
                }
            } else {
                super.obtain();
            }
        }
    }

    public CardGroup GetDeck() {
            if (AbstractDungeon.player.hasRelic(AdventurerNovice.ID)) {
                AdventurerNovice relic = (AdventurerNovice)AbstractDungeon.player.getRelic(AdventurerNovice.ID);
                return relic.DECK;
            }
            if (AbstractDungeon.player.hasRelic(SwordsmanNovice.ID)) {
                SwordsmanNovice relic = (SwordsmanNovice)AbstractDungeon.player.getRelic(SwordsmanNovice.ID);
                return relic.DECK;
            }
            if (AbstractDungeon.player.hasRelic(MerchantNovice.ID)) {
                MerchantNovice relic = (MerchantNovice)AbstractDungeon.player.getRelic(MerchantNovice.ID);
                return relic.DECK;
            }
            if (AbstractDungeon.player.hasRelic(MagicianNovice.ID)) {
                MagicianNovice relic = (MagicianNovice)AbstractDungeon.player.getRelic(MagicianNovice.ID);
                return relic.DECK;
            }
            if (AbstractDungeon.player.hasRelic(ArcherNovice.ID)) {
                ArcherNovice relic = (ArcherNovice)AbstractDungeon.player.getRelic(ArcherNovice.ID);
                return relic.DECK;
            }
            if (AbstractDungeon.player.hasRelic(AcolyteNovice.ID)) {
                AcolyteNovice relic = (AcolyteNovice)AbstractDungeon.player.getRelic(AcolyteNovice.ID);
                return relic.DECK;
            }
            if (AbstractDungeon.player.hasRelic(ThiefNovice.ID)) {
                ThiefNovice relic = (ThiefNovice)AbstractDungeon.player.getRelic(ThiefNovice.ID);
                return relic.DECK;
            }
        return null;
    }

    public void AddCard(){
        CardGroup DECK = GetDeck();
        for (int i = 0; i < DECK.group.size(); i++) {
            //group.group.get(j).upgrade();
            System.out.println("TESTT");
            System.out.println(DECK.group.get(i));
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(DECK.group.get(i), (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
        }
    }

    /*
    public void SwitchClassDeck(CardGroup newDeck){

        Iterator i = AbstractDungeon.player.masterDeck.group.iterator();
        while(true) {
            AbstractCard e;
            do {
                if (!i.hasNext()) {
                    if (this.count > 0) {
                        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                        for(int j = 0; j < this.count; ++j) {
                            AbstractCard card = AbstractDungeon.returnTrulyRandomCard().makeCopy();
                            UnlockTracker.markCardAsSeen(card.cardID);
                            card.isSeen = true;
                            group.addToBottom(card);
                        }
                        for (int j = 0 ; j < DECK.group.size() ; j++) {
                            group.group.get(j).upgrade();
                            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(DECK.group.get(j), (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                        }
                    }
                    return;
                }
                e = (AbstractCard)i.next();
            } while(e.tags.contains(AdventurerTag.STAY));
            i.remove();
            ++this.count;
        }
    }
    */
}

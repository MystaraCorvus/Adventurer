package Adventurer.relics.Novice;

import Adventurer.cards.*;
import Adventurer.relics.AdventurerRelic;
import Adventurer.util.AdventurerTag;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.Iterator;

public class MerchantNovice  extends AdventurerRelic {
    public static final String ID = "Adventurer:MerchantNovice";
    private static final String IMG = "AdventurerResources/images/relics/Merchant.png";
    private static final String IMG_OTL = "AdventurerResources/images/relics/outline/ClassRelic.png";

    public CardGroup DECK = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    public MerchantNovice() {
        super(ID, IMG, IMG_OTL, RelicTier.SHOP, LandingSound.MAGICAL);
        this.DECK.group.add(new Strike_Merchant());
        this.DECK.group.add(new Strike_Merchant());
        this.DECK.group.add(new Strike_Merchant());
        this.DECK.group.add(new Strike_Merchant());
        this.DECK.group.add(new Defend_Merchant());
        this.DECK.group.add(new Defend_Merchant());
        this.DECK.group.add(new Defend_Merchant());
        this.DECK.group.add(new Defend_Merchant());
    }
    public void onEquip() {
        super.onEquip();
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new MerchantNovice();
    }
}

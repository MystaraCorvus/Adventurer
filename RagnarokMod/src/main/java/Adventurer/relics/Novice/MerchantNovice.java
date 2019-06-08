package Adventurer.relics.Novice;

import Adventurer.cards.ShieldBash;
import Adventurer.relics.AdventurerRelic;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

public class MerchantNovice  extends AdventurerRelic {
    public static final String ID = "Adventurer:MerchantNovice";
    private static final String IMG = "AdventurerResources/images/relics/Merchant.png";
    private static final String IMG_OTL = "AdventurerResources/images/relics/outline/ClassRelic.png";

    public CardGroup DECK = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    public MerchantNovice() {
        super(ID, IMG, IMG_OTL, RelicTier.SHOP, LandingSound.MAGICAL);
        DECK.group.add(new ShieldBash().makeCopy());
        DECK.group.add(new ShieldBash().makeCopy());
        DECK.group.add(new ShieldBash().makeCopy());
        DECK.group.add(new ShieldBash().makeCopy());
        DECK.group.add(new ShieldBash().makeCopy());
        DECK.group.add(new ShieldBash().makeCopy());
        DECK.group.add(new ShieldBash().makeCopy());
        DECK.group.add(new ShieldBash().makeCopy());
        DECK.group.add(new ShieldBash().makeCopy());
    }
    public void onEquip() {

        for (int j = 0; j < DECK.group.size(); j++) {
            //group.group.get(j).upgrade();
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(DECK.group.get(j), (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
        }
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new MerchantNovice();
    }
}

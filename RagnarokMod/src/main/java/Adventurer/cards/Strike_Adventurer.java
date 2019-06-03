package Adventurer.cards;

import Adventurer.characters.Adventurer;
import Adventurer.relics.AdventurerNovice;
import Adventurer.util.AdventurerTags;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Adventurer.AdventurerMod.makeCardPath;
import static Adventurer.AdventurerMod.makeID;

// "How come this card extends CustomCard and not DynamicCard like all the rest?"
// Skip this question until you start figuring out the AbstractDefaultCard/AbstractDynamicCard and just extend DynamicCard
// for your own ones like all the other cards.

// Well every card, at the end of the day, extends CustomCard.
// Abstract Default Card extends CustomCard and builds up on it, adding a second magic number. Your card can extend it and
// bam - you can have a second magic number in that card (Learn Java inheritance if you want to know how that works).
// Abstract Dynamic Card builds up on Abstract Default Card even more and makes it so that you don't need to add
// the NAME and the DESCRIPTION into your card - it'll get it automatically. Of course, this functionality could have easily
// Been added to the default card rather than creating a new Dynamic one, but was done so to deliberately.
public class Strike_Adventurer extends AdventurerCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Strike Deal 7(9) damage.
     */

    // TEXT DECLARATION

    public static final String ID = makeID(Strike_Adventurer.class.getSimpleName());

    public static final String IMG = makeCardPath("Attack.png");
    // Setting the image as as easy as can possibly be now. You just need to provide the image name
    // and make sure it's in the correct folder. That's all.
    // There's makeCardPath, makeRelicPath, power, orb, event, etc..
    // The list of all of them can be found in the main DefaultMod.java file in the
    // ==INPUT TEXTURE LOCATION== section under ==MAKE IMAGE PATHS==

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Adventurer.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 3;

    // Hey want a second damage/magic/block/unique number??? Great!
    // Go check out DefaultAttackWithVariable and theDefault.variable.DefaultCustomVariable
    // that's how you get your own custom variable that you can use for anything you like.
    // Feel free to explore other mods to see what variabls they personally have and create your own ones.

    // /STAT DECLARATION/

    public Strike_Adventurer() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        // Aside from baseDamage/MagicNumber/Block there's also a few more.
        // Just type this.base and let intelliJ auto complete for you, or, go read up AbstractCard
        this.baseDamage = DAMAGE;

        this.tags.add(BaseModCardTags.BASIC_STRIKE); //Tag your strike, defend and form (Wraith form, Demon form, Echo form, etc.) cards so that they function correctly.
        this.tags.add(CardTags.STRIKE);
        this.tags.add(AdventurerTags.LESSER_STRIKE);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, this.damage, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

    }

    public void applyPowers()
    {
        if ((AbstractDungeon.player != null) && (AbstractDungeon.player.hasRelic(AdventurerNovice.ID))) {
            if (!upgraded) {
                this.baseDamage = (this.DAMAGE + (int) (AbstractDungeon.player.getRelic(AdventurerNovice.ID).counter * 0.6));
            } else {
                this.baseDamage = (this.DAMAGE + this.UPGRADE_PLUS_DMG + (int)(AbstractDungeon.player.getRelic(AdventurerNovice.ID).counter * 0.6));
            }
            super.applyPowers();
            initializeDescription();
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
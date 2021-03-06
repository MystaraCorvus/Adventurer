package Adventurer.cards;

import Adventurer.characters.Adventurer;
import Adventurer.patches.AdventurerColor;
import Adventurer.powers.TakeRestPower;
import Adventurer.util.AdventurerTag;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Adventurer.AdventurerMod.makeID;

public class TakeRest extends AdventurerCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * In-Progress Form At the start of your turn, play a TOUCH.
     */

    // TEXT DECLARATION

    public static final String ID = makeID(TakeRest.class.getSimpleName());

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;

    private static final CardColor COLOR = AdventurerColor.ADVENTURER;

    private static final int COST = 1;
    private static final int MAGIC = 7;

    // /STAT DECLARATION/


    public TakeRest() {

        super(ID, COST, TYPE, COLOR, RARITY, TARGET);

        this.baseMagicNumber = this.MAGIC;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractCard.CardTags.HEALING);

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new TakeRestPower(p, p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy()
    {
        return new TakeRest();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
            this.tags.add(AdventurerTag.STAY);
        }
    }
}

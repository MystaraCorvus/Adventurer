package Adventurer.cards;

import Adventurer.characters.Adventurer;
import Adventurer.patches.AdventurerColor;
import Adventurer.powers.AdventurerFormPower;
import Adventurer.powers.TemporarilyLevelUpPower;
import Adventurer.util.AdventurerTag;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Adventurer.AdventurerMod.makeID;

public class LevelBoost extends AdventurerCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * In-Progress Form At the start of your turn, play a TOUCH.
     */

    // TEXT DECLARATION

    public static final String ID = makeID(LevelBoost.class.getSimpleName());

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;

    private static final CardColor COLOR = AdventurerColor.ADVENTURER;

    private static final int COST = 1;

    private static final int MAGIC = 2;

    // /STAT DECLARATION/


    public LevelBoost() {

        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new TemporarilyLevelUpPower(p, p, this.magicNumber), this.magicNumber));
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

package Adventurer.cards;

import Adventurer.patches.AdventurerColor;
import Adventurer.util.AdventurerTag;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Adventurer.AdventurerMod.makeID;

public class Defend_Archer extends AdventurerCard {


    public static final String ID = makeID(Defend_Archer.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final CardColor COLOR = AdventurerColor.ARCHER;

    private static final int COST = 1;
    private static final int BLOCK = 5;

    private float Scaling = 0.6f;


    public Defend_Archer() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;

        this.tags.add(BaseModCardTags.BASIC_DEFEND); //Tag your strike, defend and form (Wraith form, Demon form, Echo form, etc.) cards so that they function correctly.
    }

    public void applyPowers()
    {
        this.baseBlock = (this.BLOCK + LevelScaling(Scaling));
        super.applyPowers();
        initializeDescription();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
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

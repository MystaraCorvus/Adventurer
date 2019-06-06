package Adventurer.cards;

import Adventurer.actions.DebuffRandomTargetAction;
import Adventurer.characters.Adventurer;
import Adventurer.util.DebuffType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Adventurer.AdventurerMod.makeCardPath;
import static Adventurer.AdventurerMod.makeID;

public class LesserWeak extends AdventurerCard{

    public static final String ID = makeID(LesserWeak.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;

    private float Scaling = 0.25f;

    private static final int NUM_APPLY = 3;
    private int modifiedValue = NUM_APPLY;

    private static final int AMOUNT = 2;
    private static final int COST = 1;

    public LesserWeak() {
        super(ID, COST, TYPE, RARITY, TARGET);


        this.baseMagicNumber = NUM_APPLY;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DebuffRandomTargetAction(
                AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng), p, DebuffType.WEAK, AMOUNT, this.magicNumber));
    }

    public void applyPowers()
    {
        this.magicNumber = (modifiedValue + LevelScaling(Scaling));
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}

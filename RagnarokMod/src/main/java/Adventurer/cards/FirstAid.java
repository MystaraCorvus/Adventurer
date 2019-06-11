package Adventurer.cards;

import Adventurer.patches.AdventurerColor;
import Adventurer.util.AdventurerTag;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;

import static Adventurer.AdventurerMod.makeID;

public class FirstAid extends AdventurerCard{

    public static final String ID = makeID(FirstAid.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final CardColor COLOR = AdventurerColor.ADVENTURER;

    private float Scaling = 0.25f;

    private static final int BASE = 1;

    private static final int COST = 1;

    public FirstAid() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);

        this.baseMagicNumber = BASE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RegenPower(p, this.baseMagicNumber)));
    }

    public void applyPowers()
    {
        this.baseMagicNumber = (this.BASE + LevelScaling(Scaling));
        super.applyPowers();
        initializeDescription();
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

package Adventurer.cards;

import Adventurer.patches.AdventurerColor;
import Adventurer.util.AdventurerTag;
import com.megacrit.cardcrawl.actions.defect.AnimateOrbAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeWithoutRemovingOrbAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;

import static Adventurer.AdventurerMod.makeID;

public class GlacialStrike extends AdventurerCard {

    public static final String ID = makeID(GlacialStrike.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final CardColor COLOR = AdventurerColor.ADVENTURER;

    private static final int COST = 1;

    public GlacialStrike() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;


        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractOrb orb = new Frost();
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));

        if(upgraded) {
            AbstractDungeon.actionManager.addToBottom(new AnimateOrbAction(1));
            AbstractDungeon.actionManager.addToBottom(new EvokeWithoutRemovingOrbAction(1));
        }

        AbstractDungeon.actionManager.addToBottom(new AnimateOrbAction(1));
        AbstractDungeon.actionManager.addToBottom(new EvokeOrbAction(1));
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

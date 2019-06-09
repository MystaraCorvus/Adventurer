package Adventurer.cards;

import Adventurer.actions.TargetedDarkEvokeAction;
import Adventurer.actions.TargetedLightningEvokeAction;
import Adventurer.patches.AdventurerColor;
import Adventurer.util.AdventurerTag;
import com.megacrit.cardcrawl.actions.defect.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.Lightning;

import static Adventurer.AdventurerMod.makeID;

public class LesserLightningBolt extends AdventurerCard {

    public static final String ID = makeID(LesserLightningBolt.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final CardColor COLOR = AdventurerColor.ADVENTURER;

    private static final int COST = 1;

    public LesserLightningBolt() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        AbstractOrb orb = new Lightning();
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
        AbstractDungeon.actionManager.addToBottom(new AnimateOrbAction(1));
        AbstractDungeon.actionManager.addToBottom(new TargetedLightningEvokeAction(abstractPlayer, abstractMonster, orb));

        if(!upgraded) {
            AbstractDungeon.actionManager.addToBottom(new RemoveNextOrbAction());
        }
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
//AbstractDungeon.actionManager.addToBottom(new AnimateOrbAction(1));
//AbstractDungeon.actionManager.addToBottom(new EvokeWithoutRemovingOrbAction(1));

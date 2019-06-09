package Adventurer.cards;

import Adventurer.actions.TargetedDarkEvokeAction;
import Adventurer.patches.AdventurerColor;
import com.megacrit.cardcrawl.actions.defect.AnimateOrbAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.RemoveNextOrbAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import org.lwjgl.Sys;

import static Adventurer.AdventurerMod.makeID;

public class DarkRampage extends AdventurerCard {

    public static final String ID = makeID(DarkRampage.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final CardColor COLOR = AdventurerColor.ADVENTURER;

    private static final int COST = 1;

    public DarkRampage() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        AbstractOrb orb = null;
        for (int i = 0; i < AbstractDungeon.player.orbs.size(); i++) {
            //System.out.println(new Dark().ID.contentEquals(AbstractDungeon.player.orbs.get(i).ID));
            //if(.ID.contentEquals(AbstractDungeon.player.orbs.get(i).ID)){

            orb = AbstractDungeon.player.orbs.get(i);
            System.out.println(orb.ID);

                //}
        }
        if (orb.ID == null) {
            System.out.println(orb.ID == null);
            orb = new Dark();
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(orb));
        }
        AbstractDungeon.actionManager.addToBottom(new AnimateOrbAction(1));

        AbstractDungeon.actionManager.addToBottom(new TargetedDarkEvokeAction(abstractPlayer, abstractMonster, orb));
        if(!upgraded) {
            AbstractDungeon.actionManager.addToBottom(new RemoveNextOrbAction());
        }
    }


    @Override
    public void upgrade() {
        upgradeName();
        initializeDescription();
    }
}
//AbstractDungeon.actionManager.addToBottom(new AnimateOrbAction(1));
//AbstractDungeon.actionManager.addToBottom(new EvokeWithoutRemovingOrbAction(1));

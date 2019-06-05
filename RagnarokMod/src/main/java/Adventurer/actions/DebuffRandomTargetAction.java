package Adventurer.actions;

import Adventurer.util.DebuffType;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class DebuffRandomTargetAction
        extends AbstractGameAction
{
    public AbstractCreature source;
    private int numAmount;
    private int numTimes;
    private DebuffType typ;


    public DebuffRandomTargetAction(AbstractCreature target, AbstractCreature source, DebuffType typ, int numAmount , int numTimes)
    {
        this.target = target;
        this.actionType = ActionType.DEBUFF;
        this.duration = 0.01F;
        this.numAmount = numAmount;
        this.numTimes = numTimes;
        this.typ = typ;
        this.source = source;
    }

    public void update()
    {
        if (this.target == null)
        {
            this.isDone = true;
            return;
        }
        if (this.target.currentHealth > 0)
        {
            if(this.typ == DebuffType.VULNERABILITY) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, AbstractDungeon.player, new VulnerablePower(this.target, this.numAmount, false), this.numAmount));
            }
            if(this.typ == DebuffType.WEAK) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, AbstractDungeon.player, new WeakPower(this.target, this.numAmount, false), this.numAmount));
            }
            if(this.typ == DebuffType.POISON) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, AbstractDungeon.player, new PoisonPower(this.target, this.source, this.numAmount), this.numAmount));
            }
            if ((this.numTimes > 1) && (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()))
            {
                this.numTimes -= 1;
                AbstractDungeon.actionManager.addToTop(new DebuffRandomTargetAction(
                        AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng), this.source, this.typ, this.numAmount, this.numTimes));
            }
            AbstractDungeon.actionManager.addToTop(new WaitAction(0.2F));
        }
        this.isDone = true;
    }
}

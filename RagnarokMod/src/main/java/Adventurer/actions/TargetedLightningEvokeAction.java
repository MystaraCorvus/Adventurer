package Adventurer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.defect.LightningOrbEvokeAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.Electrodynamics;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.ElectroPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import java.util.Iterator;

public class TargetedLightningEvokeAction extends AbstractGameAction {
    public TargetedLightningEvokeAction(AbstractCreature source, AbstractCreature target, AbstractOrb orb) {
        if(!(orb == null)) {
            DamageInfo info = new DamageInfo(source, orb.evokeAmount, DamageInfo.DamageType.NORMAL);
            if (!AbstractDungeon.player.hasPower(ElectroPower.POWER_ID)) {
                if (target != null) {
                    float speedTime = 0.2F / (float) AbstractDungeon.player.orbs.size();
                    if (Settings.FAST_MODE) {
                        speedTime = 0.0F;
                    }

                    info.output = AbstractOrb.applyLockOn(target, info.base);
                    AbstractDungeon.actionManager.addToTop(new DamageAction(target, info, AbstractGameAction.AttackEffect.NONE, true));
                    AbstractDungeon.actionManager.addToTop(new VFXAction(new LightningEffect(target.drawX, target.drawY), speedTime));
                    AbstractDungeon.actionManager.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE"));
                }
            } else {
                float speedTime = 0.2F / (float) AbstractDungeon.player.orbs.size();
                if (Settings.FAST_MODE) {
                    speedTime = 0.0F;
                }

                AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(info.base, true, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
                Iterator var5 = AbstractDungeon.getMonsters().monsters.iterator();

                while (var5.hasNext()) {
                    AbstractMonster m3 = (AbstractMonster) var5.next();
                    if (!m3.isDeadOrEscaped() && !m3.halfDead) {
                        AbstractDungeon.actionManager.addToTop(new VFXAction(new LightningEffect(m3.drawX, m3.drawY), speedTime));
                    }
                }

                AbstractDungeon.actionManager.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE"));
            }
        }
        this.isDone = true;
    }

    @Override
    public void update() {

    }
}

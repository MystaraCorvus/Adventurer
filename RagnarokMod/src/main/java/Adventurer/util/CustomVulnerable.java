package Adventurer.util;

import com.megacrit.cardcrawl.core.AbstractCreature;

public class CustomVulnerable extends com.megacrit.cardcrawl.actions.AbstractGameAction
{

    public static AbstractCreature targetTemp;

    @Override
    public void update() {
        if (this.target != null) {
            targetTemp = this.target;
        } else {
            targetTemp = null;
        }
    }

    public static float damageCalculat(float damage) {
        float BonusDamage = damage;
        if ((targetTemp != null) && (targetTemp.hasPower("Vulnerable"))) {
            BonusDamage = damage + damage/2;
        }
        return BonusDamage;
    }
}

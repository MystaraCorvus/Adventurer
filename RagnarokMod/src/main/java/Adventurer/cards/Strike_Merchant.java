package Adventurer.cards;

import Adventurer.patches.AdventurerColor;
import Adventurer.util.AdventurerTag;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Adventurer.AdventurerMod.makeID;

public class Strike_Merchant extends AdventurerCard {

    public static final String ID = makeID(Strike_Merchant.class.getSimpleName());
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final CardColor COLOR = AdventurerColor.MERCHANT;

    private static final int DAMAGE = 6;

    private float Scaling = 0.6f;

    private static final int COST = 1;

    public Strike_Merchant() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);

        this.baseDamage = DAMAGE;

        this.tags.add(BaseModCardTags.BASIC_STRIKE);
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, this.damage, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

    }

    public void applyPowers()
    {
        this.baseDamage = (this.DAMAGE + LevelScaling(Scaling));
        super.applyPowers();
        initializeDescription();
    }

    public boolean canUpgrade()
    {
        /*
        if (AbstractDungeon.player.gold > 20) {
            AbstractDungeon.player.loseGold(20);
            return true;
        }
        return false;

         */
        return true;
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

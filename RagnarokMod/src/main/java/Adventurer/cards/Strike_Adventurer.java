package Adventurer.cards;

import Adventurer.characters.Adventurer;
import Adventurer.powers.TemporarilyLevelUpPower;
import Adventurer.relics.AdventurerRelic;
import Adventurer.relics.Novice.AdventurerNovice;
import Adventurer.util.AdventurerTags;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Adventurer.AdventurerMod.makeCardPath;
import static Adventurer.AdventurerMod.makeID;

public class Strike_Adventurer extends AdventurerCard {

    public static final String ID = Strike_Adventurer.class.getSimpleName();
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Adventurer.Enums.COLOR_GRAY;

    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    private int modifiedValue = DAMAGE;

    private float Scaling = 0.6f;

    private static final int COST = 1;

    public Strike_Adventurer() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);

        this.baseDamage = DAMAGE;

        this.tags.add(BaseModCardTags.BASIC_STRIKE);
        this.tags.add(CardTags.STRIKE);
        this.tags.add(AdventurerTags.LESSER_STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, this.damage, damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

    }

    public void applyPowers()
    {
        this.baseDamage = (modifiedValue + LevelScaling(Scaling));
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            modifiedValue += UPGRADE_PLUS_DMG;
            initializeDescription();
        }
    }
}

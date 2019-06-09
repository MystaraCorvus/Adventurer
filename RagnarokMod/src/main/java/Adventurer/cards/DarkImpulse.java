package Adventurer.cards;

import Adventurer.patches.AdventurerColor;
import Adventurer.util.AdventurerTag;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.defect.DarkImpulseAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.SearingBlow;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;

import static Adventurer.AdventurerMod.makeID;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class DarkImpulse extends AdventurerCard {

    public static final String ID = makeID(DarkImpulse.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final CardColor COLOR = AdventurerColor.ADVENTURER;

    private static final int COST = 1;

    public DarkImpulse() {
        this(0);
    }

    public DarkImpulse(int upgrades) {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        this.showEvokeValue = true;

        this.timesUpgraded = upgrades;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i = 0; i < AbstractDungeon.player.orbs.size(); i++) {
            AbstractOrb orb = AbstractDungeon.player.orbs.get(i);
            if (orb instanceof Dark) {
                for (int j = 0; j < this.baseMagicNumber; j++)
                {
                    AbstractDungeon.actionManager.addToBottom(new DarkImpulseAction());
                }
                break;
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgraded = true;
            this.tags.add(AdventurerTag.STAY);
        }
        ++this.timesUpgraded;
        this.name = languagePack.getCardStrings(ID).NAME + "+" + (this.timesUpgraded);
        this.baseMagicNumber = this.timesUpgraded;
        initializeTitle();
        initializeDescription();
    }

    public boolean canUpgrade() {
        return true;
    }

    @Override
    public AbstractCard makeCopy() {
        return new DarkImpulse(this.timesUpgraded);
    }
}
//AbstractDungeon.actionManager.addToBottom(new AnimateOrbAction(1));
//AbstractDungeon.actionManager.addToBottom(new EvokeWithoutRemovingOrbAction(1));

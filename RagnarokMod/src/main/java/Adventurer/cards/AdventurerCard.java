package Adventurer.cards;

import Adventurer.powers.AdventurerPower;
import Adventurer.powers.TemporarilyLevelUpPower;
import Adventurer.relics.AdventurerRelic;
import Adventurer.relics.Novice.AdventurerNovice;
import Adventurer.util.AdventurerTags;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.HashMap;

import static Adventurer.AdventurerMod.makeCardPath;
import static Adventurer.AdventurerMod.makeID;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class AdventurerCard extends CustomCard {

    public int defaultSecondMagicNumber;        // Just like magic number, or any number for that matter, we want our regular, modifiable stat
    public int defaultBaseSecondMagicNumber;    // And our base stat - the number in it's base state. It will reset to that by default.
    public boolean upgradedDefaultSecondMagicNumber; // A boolean to check whether the number has been upgraded or not.
    public boolean isDefaultSecondMagicNumberModified; // A boolean to check whether the number has been modified or not, for coloring purposes. (red/green)

    public AdventurerCard(final String id,
                               final int cost,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {

        super(makeID(id), languagePack.getCardStrings(makeID(id)).NAME, makeCardPath(id + ".png"), cost, languagePack.getCardStrings(makeID(id)).DESCRIPTION, type, color, rarity, target);

        // Set all the things to their default values.
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        isDefaultSecondMagicNumberModified = false;
    }

    public void displayUpgrades() { // Display the upgrade - when you click a card to upgrade it
        super.displayUpgrades();
        if (upgradedDefaultSecondMagicNumber) { // If we set upgradedDefaultSecondMagicNumber = true in our card.
            defaultSecondMagicNumber = defaultBaseSecondMagicNumber; // Show how the number changes, as out of combat, the base number of a card is shown.
            isDefaultSecondMagicNumberModified = true; // Modified = true, color it green to highlight that the number is being changed.
        }

    }

    public void upgradeDefaultSecondMagicNumber(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.
        defaultBaseSecondMagicNumber += amount; // Upgrade the number by the amount you provide in your card.
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber; // Set the number to be equal to the base value.
        upgradedDefaultSecondMagicNumber = true; // Upgraded = true - which does what the above method does.
    }

    public boolean CompareLastCardPlayed(CardTags t){
        if(!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            ArrayList<AbstractCard> played = AbstractDungeon.actionManager.cardsPlayedThisTurn;
            if (played.get(played.size() - 1).tags.contains(t)) {
                return true;
            }
        }
        return false;
    }

    public boolean CompareLastCardPlayed(CardRarity t){
        if(!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            ArrayList<AbstractCard> played = AbstractDungeon.actionManager.cardsPlayedThisTurn;
            if (played.get(played.size() - 1).rarity == t) {
                return true;
            }
        }
        return false;
    }

    public boolean CompareLastCardPlayed(ArrayList<CardTags> CardToCheck){
        if(!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            for (CardTags t : CardToCheck) {
                if (CompareLastCardPlayed(t)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int LevelScaling (float scaling) {
        int Bonus = 0;
        Bonus += AdventurerRelic.GetLevel();
        Bonus += AdventurerPower.GetTemporarilyLevel();
        Bonus = (int) (Bonus * scaling);
        return Bonus;
    }

    public int ComboBonus (CardTags tag, int amount, float scale)
    {
        if(CompareLastCardPlayed(tag)){
            return amount + LevelScaling(scale);
        }
        return 0;
    }

    public int ComboBonus (CardRarity tag, int amount, float scale)
    {
        if(CompareLastCardPlayed(tag)){
            return amount + LevelScaling(scale);
        }
        return 0;
    }

    public int ComboBonus (ArrayList<CardTags> tags, int amount, float scale)
    {
        if(CompareLastCardPlayed(tags)){
            return amount + LevelScaling(scale);
        }
        return 0;
    }

    public AdventurerCard GetLastCard (){
        if(!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            ArrayList<AbstractCard> played = AbstractDungeon.actionManager.cardsPlayedThisTurn;
                return (AdventurerCard)played.get(played.size() - 1);
        }
        return null;
    }
}

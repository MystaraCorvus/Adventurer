package Adventurer.ui.campfire;

import Adventurer.characters.Adventurer;
import Adventurer.relics.AdventurerNovice;
import Adventurer.vfx.campfire.LevelUpAction;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import Adventurer.AdventurerMod;
import java.lang.reflect.Method;

import static Adventurer.AdventurerMod.logger;

public class LevelUpOption extends AbstractCampfireOption
{
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Adventurer:LevelUpOption");
    public static final String[] TEXT = uiStrings.TEXT;

    public LevelUpOption() {
        this.label = TEXT[0];
        this.description = TEXT[1];
        this.img = ImageMaster.loadImage("AdventurerResources/images/ui/campfire/LevelUp.png");
    }

    @Override
    public void useOption() {
        AdventurerNovice playerClass = (AdventurerNovice)AbstractDungeon.player.getRelic(AdventurerNovice.ID);
        playerClass.counter++;
        playerClass.tips.clear();
        playerClass.tips.add(new PowerTip(playerClass.name, playerClass.description));
        try {
            Method m = playerClass.getClass().getDeclaredMethod("initializeTips", new Class[0]);
            m.setAccessible(true);
            m.invoke(playerClass, new Object[0]);
        } catch (Exception e) {
            logger.info(e);
        }
        playerClass.flash();
        AbstractDungeon.effectList.add(new LevelUpAction());
    }
}
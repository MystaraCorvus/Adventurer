package Adventurer.ui.campfire;

import Adventurer.relics.AdventurerRelic;
import Adventurer.relics.Novice.AdventurerNovice;
import Adventurer.vfx.campfire.LevelUpAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

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
        if (AdventurerRelic.HasClassRelic()){
            AdventurerRelic curClass = AdventurerRelic.GetClassRelic();
            curClass.counter++;
            curClass.tips.clear();
            curClass.tips.add(new PowerTip(curClass.name, curClass.description));
            try {
                Method m = curClass.getClass().getDeclaredMethod("initializeTips", new Class[0]);
                m.setAccessible(true);
                m.invoke(curClass, new Object[0]);
            } catch (Exception e) {
                logger.info(e);
            }
            curClass.flash();
            AbstractDungeon.effectList.add(new LevelUpAction());
        }
    }
}
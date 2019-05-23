package Adventurer.ui.campfire;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.WingBoots;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import java.lang.reflect.Method;

import static Adventurer.AdventurerMod.logger;

public class LevelUpOption extends AbstractCampfireOption
{
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Adventurer:LevelUpOption");
    public static final String[] TEXT = uiStrings.TEXT;

    private static final int CHARGES = 3;

    public LevelUpOption() {
        this.label = TEXT[0];
        this.description = TEXT[1] + CHARGES + TEXT[2];
        this.img = ImageMaster.loadImage("AdventurerResources/images/ui/campfire/LevelUp.png");
    }

    @Override
    public void useOption() {
        WingBoots wb = (WingBoots)AbstractDungeon.player.getRelic(WingBoots.ID);
        if(wb.usedUp) {
            wb.usedUp = false;
            wb.description = RelicLibrary.getRelic(WingBoots.ID).description;
            wb.tips.clear();
            wb.tips.add(new PowerTip(wb.name, wb.description));
            try {
                Method m = wb.getClass().getDeclaredMethod("initializeTips");
                m.setAccessible(true);
                m.invoke(wb);
            } catch(Exception e) {
                logger.info(e);
            }

            //ReflectionHacks.setPrivate(wb, AbstractRelic.class, "img", ImageMaster.loadImage(Aspiration.assetPath("img/relics/winged.png")));
            wb.setCounter(CHARGES);
        } else if(wb.counter < 1) {
            wb.setCounter(CHARGES);
        } else {
            wb.counter += CHARGES;
        }
        wb.flash();
        AbstractDungeon.effectList.add(new Adventurer.vfx.campfire.LevelUpOption());
    }
}
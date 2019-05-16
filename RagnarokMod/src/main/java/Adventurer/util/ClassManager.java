package Adventurer.util;

import basemod.interfaces.PostCampfireSubscriber;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.RestRoom;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.getCurrRoom;

public class ClassManager
        implements PostCampfireSubscriber {
    public static int maxRestTimes = 1;
    public static int curRestTimes = 1;
    public static boolean again = true;

    public static void finishRest() {
        curRestTimes = maxRestTimes;
    }

    public static void onEnterRestRoom() {
        again = true;
    }

    public static void update() {
        if ((getCurrRoom() instanceof RestRoom) && (getCurrRoom().phase == AbstractRoom.RoomPhase.COMPLETE))
        {

        }
    }

    @Override
    public boolean receivePostCampfire() {

        if(ClassManager.curRestTimes > 0 && again == true) {
            ClassManager.curRestTimes--;

            AbstractRoom restSite = new RestRoom();
            ((RestRoom) AbstractDungeon.getCurrRoom()).campfireUI.somethingSelected = false;
            getCurrRoom().phase = AbstractRoom.RoomPhase.INCOMPLETE;
            restSite.onPlayerEntry();

        }
        else
            {
            ClassManager.finishRest();
            again = false;
        }
        return false;
    }
}

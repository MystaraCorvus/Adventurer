package Adventurer;

import Adventurer.characters.Adventurer;
import Adventurer.patches.AdventurerColor;
import Adventurer.patches.AdventurerEnum;
import Adventurer.relics.Novice.*;
import Adventurer.variables.LevelScalingOneSixth;
import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Adventurer.cards.*;
import Adventurer.events.IdentityCrisisEvent;
import Adventurer.potions.PlaceholderPotion;
import Adventurer.util.IDCheckDontTouchPls;
import Adventurer.util.TextureLoader;
import Adventurer.variables.DefaultCustomVariable;
import Adventurer.variables.DefaultSecondMagicNumber;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Properties;

//TODO: DON'T MASS RENAME/REFACTOR
//TODO: DON'T MASS RENAME/REFACTOR
//TODO: DON'T MASS RENAME/REFACTOR
//TODO: DON'T MASS RENAME/REFACTOR
// Please don't just mass replace "Adventurerlocalization" with "yourMod" everywhere.
// It'll be a bigger pain for you. You only need to replace it in 3 places.
// I comment those places below, under the place where you set your ID.

//TODO: FIRST THINGS FIRST: RENAME YOUR PACKAGE AND ID NAMES FIRST-THING!!!
// Right click the package (Open the project pane on the left. Folder with black dot on it. The name's at the very top) -> Refactor -> Rename, and name it whatever you wanna call your mod.
// Scroll down in this file. Change the ID from "Adventurerlocalization:" to "yourModName:" or whatever your heart desires (don't use spaces). Dw, you'll see it.
// In the JSON strings (resources>localization>eng>[all them files] make sure they all go "yourModName:" rather than "Adventurerlocalization". You can ctrl+R to replace in 1 file, or ctrl+shift+r to mass replace in specific files/directories (Be careful.).
// Start with the DefaultCommon cards - they are the most commented cards since I don't feel it's necessary to put identical comments on every card.
// After you sorta get the hang of how to make cards, check out the card template which will make your life easier

/*
 * With that out of the way:
 * Welcome to this super over-commented Slay the Spire modding base.
 * Use it to make your own mod of any type. - If you want to add any standard in-game content (character,
 * cards, relics), this is a good starting point.
 * It features 1 character with a minimal set of things: 1 card of each type, 1 debuff, couple of relics, etc.
 * If you're new to modding, you basically *need* the BaseMod wiki for whatever you wish to add
 * https://github.com/daviscook477/BaseMod/wiki - work your way through with this base.
 * Feel free to use this in any way you like, of course. MIT licence applies. Happy modding!
 *
 * And pls. Read the comments.
 */

@SpireInitializer
public class AdventurerMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        PostCampfireSubscriber,
        PostDungeonInitializeSubscriber {
    // Make sure to implement the subscribers *you* are using (read basemod wiki). Editing cards? EditCardsSubscriber.
    // Making relics? EditRelicsSubscriber. etc., etc., for a full list and how to make your own, visit the basemod wiki.
    public static final Logger logger = LogManager.getLogger(AdventurerMod.class.getName());
    private static String modID;

    // Mod-settings settings. This is if you want an on/off savable button
    public static Properties theDefaultDefaultSettings = new Properties();
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder";
    public static boolean enablePlaceholder = true; // The boolean we'll be setting on/off (true/false)

    //This is for the in-game mod settings panel.
    private static final String MODNAME = "Adventurer";
    private static final String AUTHOR = "Gremious, Satan"; // And pretty soon - You!
    private static final String DESCRIPTION = "A base for Slay the Spire to start your own mod from, feat. the Adventurerlocalization.";
    
    // =============== INPUT TEXTURE LOCATION =================
    
    // Colors (RGB)
    // Character Color
    public static final Color ADVENTURER_GRAY = CardHelper.getColor(191.0f, 213.0f, 215.0f);
    public static final Color THIEF_GREEN = CardHelper.getColor(155.0f, 234.0f, 203.0f);
    
    // Potion Colors in RGB
    public static final Color PLACEHOLDER_POTION_LIQUID = CardHelper.getColor(209.0f, 53.0f, 18.0f); // Orange-ish Red
    public static final Color PLACEHOLDER_POTION_HYBRID = CardHelper.getColor(255.0f, 230.0f, 230.0f); // Near White
    public static final Color PLACEHOLDER_POTION_SPOTS = CardHelper.getColor(100.0f, 25.0f, 10.0f); // Super Dark Red/Brown

    ///GRAY
    // Card backgrounds - The actual rectangular card.
    private static final String ATTACK_DEFAULT_GRAY = "AdventurerResources/images/512/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY = "AdventurerResources/images/512/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY = "AdventurerResources/images/512/bg_power_default_gray.png";

    private static final String ENERGY_ORB_DEFAULT_GRAY = "AdventurerResources/images/512/card_default_gray_orb.png";
    private static final String CARD_ENERGY_ORB = "AdventurerResources/images/512/card_small_orb.png";

    private static final String ATTACK_DEFAULT_GRAY_PORTRAIT = "AdventurerResources/images/1024/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_GRAY_PORTRAIT = "AdventurerResources/images/1024/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_GRAY_PORTRAIT = "AdventurerResources/images/1024/bg_power_default_gray.png";
    private static final String ENERGY_ORB_DEFAULT_GRAY_PORTRAIT = "AdventurerResources/images/1024/card_default_gray_orb.png";

    ///GREEN
    // Card backgrounds - The actual rectangular card.
    private static final String ATTACK_DEFAULT_GREEN = "AdventurerResources/images/512/bg_attack_green.png";
    private static final String SKILL_DEFAULT_GREEN = "AdventurerResources/images/512/bg_skill_green.png";
    private static final String POWER_DEFAULT_GREEN = "AdventurerResources/images/512/bg_power_green.png";

    private static final String ENERGY_ORB_DEFAULT_GREEN = "AdventurerResources/images/512/card_green_orb.png";
    private static final String CARD_ENERGY_ORB_GREEN = "AdventurerResources/images/512/card_small_orb_green.png";

    private static final String ATTACK_DEFAULT_GREEN_PORTRAIT = "AdventurerResources/images/1024/bg_attack_green.png";
    private static final String SKILL_DEFAULT_GREEN_PORTRAIT = "AdventurerResources/images/1024/bg_skill_green.png";
    private static final String POWER_DEFAULT_GREEN_PORTRAIT = "AdventurerResources/images/1024/bg_power_green.png";
    private static final String ENERGY_ORB_DEFAULT_GREEN_PORTRAIT = "AdventurerResources/images/1024/card_green_orb.png";

    // Character assets
    private static final String THE_DEFAULT_BUTTON = "AdventurerResources/images/charSelect/DefaultCharacterButton.png";
    private static final String THE_DEFAULT_PORTRAIT = "AdventurerResources/images/charSelect/DefaultCharacterPortraitBG.png";
    public static final String THE_DEFAULT_SHOULDER_1 = "AdventurerResources/images/char/defaultCharacter/shoulder.png";
    public static final String THE_DEFAULT_SHOULDER_2 = "AdventurerResources/images/char/defaultCharacter/shoulder2.png";
    public static final String THE_DEFAULT_CORPSE = "AdventurerResources/images/char/defaultCharacter/corpse.png";

    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = "AdventurerResources/images/Badge.png";

    // Atlas and JSON files for the Animations
    public static final String THE_DEFAULT_SKELETON_ATLAS = "AdventurerResources/images/char/defaultCharacter/skeleton.atlas";
    public static final String THE_DEFAULT_SKELETON_JSON = "AdventurerResources/images/char/defaultCharacter/skeleton.json";

    // =============== MAKE IMAGE PATHS =================

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/orbs/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }
    // =============== /MAKE IMAGE PATHS/ =================
    
    // =============== /INPUT TEXTURE LOCATION/ =================
    
    
    // =============== SUBSCRIBE, CREATE THE COLOR_GRAY, INITIALIZE =================
    
    public AdventurerMod() {
        logger.info("Subscribe to BaseMod hooks");
        
        BaseMod.subscribe(this);
        
      /*
           (   ( /(  (     ( /( (            (  `   ( /( )\ )    )\ ))\ )
           )\  )\()) )\    )\()))\ )   (     )\))(  )\()|()/(   (()/(()/(
         (((_)((_)((((_)( ((_)\(()/(   )\   ((_)()\((_)\ /(_))   /(_))(_))
         )\___ _((_)\ _ )\ _((_)/(_))_((_)  (_()((_) ((_|_))_  _(_))(_))_
        ((/ __| || (_)_\(_) \| |/ __| __| |  \/  |/ _ \|   \  |_ _||   (_)
         | (__| __ |/ _ \ | .` | (_ | _|  | |\/| | (_) | |) |  | | | |) |
          \___|_||_/_/ \_\|_|\_|\___|___| |_|  |_|\___/|___/  |___||___(_)
      */
      
        setModID("Adventurer");
        // cool
        // TODO: NOW READ THIS!!!!!!!!!!!!!!!:
        
        // 1. Go to your resources folder in the project panel, and refactor> rename Resources to
        // yourModIDResources.
        
        // 2. Click on the localization > eng folder and press ctrl+shift+r, then select "Directory" (rather than in Project)
        // replace all instances of Adventurer with yourModID.
        // Because your mod ID isn't the default. Your cards (and everything else) should have Your mod id. Not mine.
        
        // 3. FINALLY and most importantly: Scroll up a bit. You may have noticed the image locations above don't use getModID()
        // Change their locations to reflect your actual ID rather than Adventurer. They get loaded before getID is a thing.
        
        logger.info("Done subscribing");

        ///ADVENTURER
        logger.info("Creating the color " + AdventurerColor.ADVENTURER.toString());
        
        BaseMod.addColor(AdventurerColor.ADVENTURER, ADVENTURER_GRAY, ADVENTURER_GRAY, ADVENTURER_GRAY,
                ADVENTURER_GRAY, ADVENTURER_GRAY, ADVENTURER_GRAY, ADVENTURER_GRAY,
                ATTACK_DEFAULT_GRAY, SKILL_DEFAULT_GRAY, POWER_DEFAULT_GRAY, ENERGY_ORB_DEFAULT_GRAY,
                ATTACK_DEFAULT_GRAY_PORTRAIT, SKILL_DEFAULT_GRAY_PORTRAIT, POWER_DEFAULT_GRAY_PORTRAIT,
                ENERGY_ORB_DEFAULT_GRAY_PORTRAIT, CARD_ENERGY_ORB);
        
        logger.info("Done creating the color");

        ///THIEF
        logger.info("Creating the color " + AdventurerColor.THIEF.toString());

        BaseMod.addColor(AdventurerColor.THIEF, THIEF_GREEN, THIEF_GREEN, THIEF_GREEN,
                THIEF_GREEN, THIEF_GREEN, THIEF_GREEN, THIEF_GREEN,
                ATTACK_DEFAULT_GREEN, SKILL_DEFAULT_GREEN, POWER_DEFAULT_GREEN, ENERGY_ORB_DEFAULT_GREEN,
                ATTACK_DEFAULT_GREEN_PORTRAIT, SKILL_DEFAULT_GREEN_PORTRAIT, POWER_DEFAULT_GREEN_PORTRAIT,
                ENERGY_ORB_DEFAULT_GREEN_PORTRAIT, CARD_ENERGY_ORB_GREEN);

        logger.info("Done creating the color");

        logger.info("Adding mod settings");
        // This loads the mod settings.
        // The actual mod Button is added below in receivePostInitialize()
        theDefaultDefaultSettings.setProperty(ENABLE_PLACEHOLDER_SETTINGS, "FALSE"); // This is the default setting. It's actually set...
        try {
            SpireConfig config = new SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings); // ...right here
            // the "fileName" parameter is the name of the file MTS will create where it will save our setting.
            config.load(); // Load the setting and set the boolean to equal it
            enablePlaceholder = config.getBool(ENABLE_PLACEHOLDER_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Done adding mod settings");
        
    }
    
    // ====== NO EDIT AREA ======
    // DON'T TOUCH THIS STUFF. IT IS HERE FOR STANDARDIZATION BETWEEN MODS AND TO ENSURE GOOD CODE PRACTICES.
    // IF YOU MODIFY THIS I WILL HUNT YOU DOWN AND DOWNVOTE YOUR MOD ON WORKSHOP
    
    public static void setModID(String ID) { // DON'T EDIT
        Gson coolG = new Gson(); // EY DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i hate u Gdx.files
        InputStream in = AdventurerMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"); // DON'T EDIT THIS ETHER
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // OR THIS, DON'T EDIT IT
        logger.info("You are attempting to set your mod ID as: " + ID); // NO WHY
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) { // DO *NOT* CHANGE THIS ESPECIALLY, TO EDIT YOUR MOD ID, SCROLL UP JUST A LITTLE, IT'S JUST ABOVE
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION); // THIS ALSO DON'T EDIT
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) { // NO
            modID = EXCEPTION_STRINGS.DEFAULTID; // DON'T
        } else { // NO EDIT AREA
            modID = ID; // DON'T WRITE OR CHANGE THINGS HERE NOT EVEN A LITTLE
        } // NO
        logger.info("Success! ID is " + modID); // WHY WOULD U WANT IT NOT TO LOG?? DON'T EDIT THIS.
    } // NO
    
    public static String getModID() { // NO
        return modID; // DOUBLE NO
    } // NU-UH
    
    private static void pathCheck() { // ALSO NO
        Gson coolG = new Gson(); // NNOPE DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i still hate u btw Gdx.files
        InputStream in = AdventurerMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"); // DON'T EDIT THISSSSS
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // NAH, NO EDIT
        String packageName = AdventurerMod.class.getPackage().getName(); // STILL NO EDIT ZONE
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources"); // PLEASE DON'T EDIT THINGS HERE, THANKS
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) { // LEAVE THIS EDIT-LESS
            if (!packageName.equals(getModID())) { // NOT HERE ETHER
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID()); // THIS IS A NO-NO
            } // WHY WOULD U EDIT THIS
            if (!resourcePathExists.exists()) { // DON'T CHANGE THIS
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources"); // NOT THIS
            }// NO
        }// NO
    }// NO
    
    // ====== YOU CAN EDIT AGAIN ======
    
    
    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing Adventurerlocalization Mod. Hi. =========================");
        AdventurerMod defaultmod = new AdventurerMod();
        logger.info("========================= /Adventurerlocalization Mod Initialized. Hello World./ =========================");
    }
    
    // ============== /SUBSCRIBE, CREATE THE COLOR_GRAY, INITIALIZE/ =================
    
    
    // =============== LOAD THE CHARACTER =================
    
    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + AdventurerEnum.ADVENTURER.toString());
        
        BaseMod.addCharacter(new Adventurer("The Adventurer", AdventurerEnum.ADVENTURER),
                THE_DEFAULT_BUTTON, THE_DEFAULT_PORTRAIT, AdventurerEnum.ADVENTURER);
        
        receiveEditPotions();
        logger.info("Added " + AdventurerEnum.ADVENTURER.toString());
    }
    
    // =============== /LOAD THE CHARACTER/ =================
    
    
    // =============== POST-INITIALIZE =================
    
    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");
        
        // Load the Mod Badge
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        
        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();
        
        // Create the on/off button:
        ModLabeledToggleButton enableNormalsButton = new ModLabeledToggleButton("This is the text which goes next to the checkbox.",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont, // Position (trial and error it), color, font
                enablePlaceholder, // Boolean it uses
                settingsPanel, // The mod panel in which this button will be in
                (label) -> {}, // thing??????? idk
                (button) -> { // The actual button:
            
            enablePlaceholder = button.enabled; // The boolean true/false will be whether the button is enabled or not
            try {
                // And based on that boolean, set the settings and save them
                SpireConfig config = new SpireConfig("defaultMod", "theDefaultConfig", theDefaultDefaultSettings);
                config.setBool(ENABLE_PLACEHOLDER_SETTINGS, enablePlaceholder);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        settingsPanel.addUIElement(enableNormalsButton); // Add the button to the settings panel. Button is a go.
        
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        
        // =============== EVENTS =================
        
        // This event will be exclusive to the City (act 2). If you want an event that's present at any
        // part of the game, simply don't include the dungeon ID
        // If you want to have a character-specific event, look at slimebound (CityRemoveEventPatch).
        // Essentially, you need to patch the game and say "if a player is not playing my character class, remove the event from the pool"
        BaseMod.addEvent(IdentityCrisisEvent.ID, IdentityCrisisEvent.class, TheCity.ID);
        
        // =============== /EVENTS/ =================
        logger.info("Done loading badge Image and mod options");
    }
    
    // =============== / POST-INITIALIZE/ =================
    
    
    // ================ ADD POTIONS ===================
    
    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");
        
        // Class Specific Potion. If you want your potion to not be class-specific,
        // just remove the player class at the end (in this case the "TheDefaultEnum.ADVENTURER".
        // Remember, you can press ctrl+P inside parentheses like addPotions)
        BaseMod.addPotion(PlaceholderPotion.class, PLACEHOLDER_POTION_LIQUID, PLACEHOLDER_POTION_HYBRID, PLACEHOLDER_POTION_SPOTS, PlaceholderPotion.POTION_ID, AdventurerEnum.ADVENTURER);
        
        logger.info("Done editing potions");
    }
    
    // ================ /ADD POTIONS/ ===================
    
    
    // ================ ADD RELICS ===================
    
    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");

        // This adds a character specific relic. Only when you play with the mentioned color, will you get this relic.
        /*
        BaseMod.addRelicToCustomPool(new PlaceholderRelic(), Adventurer.Enums.COLOR_GRAY);
        BaseMod.addRelicToCustomPool(new BottledPlaceholderRelic(), Adventurer.Enums.COLOR_GRAY);
        BaseMod.addRelicToCustomPool(new DefaultClickableRelic(), Adventurer.Enums.COLOR_GRAY);
        */
        BaseMod.addRelicToCustomPool(new AdventurerNovice(), AdventurerColor.ADVENTURER);
        BaseMod.addRelicToCustomPool(new ThiefNovice(), AdventurerColor.ADVENTURER);
        BaseMod.addRelicToCustomPool(new AcolyteNovice(), AdventurerColor.ADVENTURER);
        BaseMod.addRelicToCustomPool(new ArcherNovice(), AdventurerColor.ADVENTURER);
        BaseMod.addRelicToCustomPool(new MagicianNovice(), AdventurerColor.ADVENTURER);
        BaseMod.addRelicToCustomPool(new MerchantNovice(), AdventurerColor.ADVENTURER);
        BaseMod.addRelicToCustomPool(new SwordsmanNovice(), AdventurerColor.ADVENTURER);

        UnlockTracker.markRelicAsSeen(ThiefNovice.ID);
        UnlockTracker.markRelicAsSeen(AcolyteNovice.ID);
        UnlockTracker.markRelicAsSeen(ArcherNovice.ID);
        UnlockTracker.markRelicAsSeen(MagicianNovice.ID);
        UnlockTracker.markRelicAsSeen(MerchantNovice.ID);
        UnlockTracker.markRelicAsSeen(SwordsmanNovice.ID);
        UnlockTracker.markRelicAsSeen(AdventurerNovice.ID);

        logger.info("Done adding relics!");

    }
    
    // ================ /ADD RELICS/ ===================
    
    
    // ================ ADD CARDS ===================
    
    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");
        //Ignore this
        pathCheck();
        // Add the Custom Dynamic Variables
        logger.info("Add variabls");
        // Add the Custom Dynamic variabls
        BaseMod.addDynamicVariable(new DefaultCustomVariable());
        BaseMod.addDynamicVariable(new DefaultSecondMagicNumber());
        BaseMod.addDynamicVariable(new LevelScalingOneSixth());


        logger.info("Adding cards");
        // Add the cards
        // Don't comment out/delete these cards (yet). You need 1 of each type and rarity (technically) for your game not to crash
        // when generating card rewards/shop screen items.

        // Basic
        BaseMod.addCard(new Strike_Adventurer());
        BaseMod.addCard(new Defend_Adventurer());

        //Common

        ///Attack
        BaseMod.addCard(new Strike());

        ///Skill
        BaseMod.addCard(new LesserWeak());

        ///Power
        BaseMod.addCard(new LevelBoost());

        //Uncommon

        ///Attack
        BaseMod.addCard(new LesserLightningBolt());
        BaseMod.addCard(new ShieldBash());

        ///Skill
        BaseMod.addCard(new LesserVulnerability());

        ///Power
        BaseMod.addCard(new TakeRest());

        //Rare

        ///Attack
        BaseMod.addCard(new ScalingPain());

        ///Skill
        BaseMod.addCard(new LesserPoison());

        ///Power
        BaseMod.addCard(new AdventurerForm());

        
        logger.info("Making sure the cards are unlocked.");
        // Unlock the cards
        // This is so that they are all "seen" in the library, for people who like to look at the card list
        // before playing your mod.

        UnlockTracker.unlockCard(Strike_Adventurer.ID);
        UnlockTracker.unlockCard(Defend_Adventurer.ID);

        UnlockTracker.unlockCard(Strike.ID);

        UnlockTracker.unlockCard(AdventurerForm.ID);
        UnlockTracker.unlockCard(TakeRest.ID);
        UnlockTracker.unlockCard(LesserVulnerability.ID);
        UnlockTracker.unlockCard(LesserWeak.ID);
        UnlockTracker.unlockCard(LesserPoison.ID);

        UnlockTracker.unlockCard(ScalingPain.ID);
        UnlockTracker.unlockCard(LevelBoost.ID);

        UnlockTracker.unlockCard(ShieldBash.ID);

        logger.info("Done adding cards!");
    }
    
    // There are better ways to do this than listing every single individual card, but I do not want to complicate things
    // in a "tutorial" mod. This will do and it's completely ok to use. If you ever want to clean up and
    // shorten all the imports, go look take a look at other mods, such as Hubris.
    
    // ================ /ADD CARDS/ ===================
    
    
    // ================ LOAD THE TEXT ===================
    
    @Override
    public void receiveEditStrings() {
        logger.info("You seeing this?");
        logger.info("Beginning to edit strings for mod with ID: " + getModID());

        // CardStrings
        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/eng/Adventurer-Card-Strings.json");

        // PowerStrings
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/eng/Adventurer-Power-Strings.json");

        // RelicStrings
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/eng/Adventurer-Relic-Strings.json");

        // Event Strings
        BaseMod.loadCustomStringsFile(EventStrings.class,
                getModID() + "Resources/localization/eng/Adventurer-Event-Strings.json");

        // PotionStrings
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                getModID() + "Resources/localization/eng/Adventurer-Potion-Strings.json");

        // CharacterStrings
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/localization/eng/Adventurer-Character-Strings.json");

        // OrbStrings
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                getModID() + "Resources/localization/eng/Adventurer-Orb-Strings.json");

        BaseMod.loadCustomStringsFile(UIStrings.class, (getModID() + "Resources/localization/eng/Adventurer-Ui-Strings.json"));

        logger.info("Done edittting strings");
    }
    
    // ================ /LOAD THE TEXT/ ===================
    
    // ================ LOAD THE KEYWORDS ===================
    
    @Override
    public void receiveEditKeywords() {
        // Keywords on cards are supposed to be Capitalized, while in Keyword-String.json they're lowercase
        //
        // Multiword keywords on cards are done With_Underscores
        //
        // If you're using multiword keywords, the first element in your NAMES array in your keywords-strings.json has to be the same as the PROPER_NAME.
        // That is, in Card-Strings.json you would have #yA_Long_Keyword (#y highlights the keyword in yellow).
        // In Keyword-Strings.json you would have PROPER_NAME as A Long Keyword and the first element in NAMES be a long keyword, and the second element be a_long_keyword

        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/Adventurer-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                    BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
                    //  getModID().toLowerCase() makes your keyword mod specific (it won't show up in other cards that use that word)
            }
        }
    }
    
    // ================ /LOAD THE KEYWORDS/ ===================    
    
    // this adds "ModName:" before the ID of any card/relic/power etc.
    // in order to avoid conflicts if any other mod uses the same ID.
    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    public static String reverseID(String idText) {
        String[] parts = idText.split(":");
        return parts[1];
    }

    public static int maxOptionsRestRoom = 1;
    public static int curOptionsRestRoom = 0;

    @Override
    public boolean receivePostCampfire() {
        maxOptionsRestRoom = 1;

        if (AbstractDungeon.player.hasRelic(AdventurerNovice.ID)) {
            maxOptionsRestRoom++;
        }

        curOptionsRestRoom++;
        if (curOptionsRestRoom < maxOptionsRestRoom) {
            return false;
        } else {
            maxOptionsRestRoom = 1;
            curOptionsRestRoom = 0;
            return true;
        }
    }

    @Override
    public void receivePostDungeonInitialize() {
        AbstractDungeon.bossRelicPool.clear();
        AbstractDungeon.bossRelicPool.add(ArcherNovice.ID);
        AbstractDungeon.bossRelicPool.add(MagicianNovice.ID);
        AbstractDungeon.bossRelicPool.add(SwordsmanNovice.ID);
        AbstractDungeon.bossRelicPool.add(ThiefNovice.ID);
        Collections.shuffle(AbstractDungeon.bossRelicPool);
    }
}

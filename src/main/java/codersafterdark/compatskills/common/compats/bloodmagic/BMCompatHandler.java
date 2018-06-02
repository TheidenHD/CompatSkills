package codersafterdark.compatskills.common.compats.bloodmagic;

import WayofTime.bloodmagic.ritual.Ritual;
import codersafterdark.compatskills.common.compats.bloodmagic.ritualhandler.RitualHandler;
import codersafterdark.compatskills.common.compats.bloodmagic.ritualhandler.keys.RitualCostLockKey;
import codersafterdark.compatskills.common.compats.bloodmagic.ritualhandler.keys.RitualCrystalLockKey;
import codersafterdark.compatskills.common.compats.bloodmagic.ritualhandler.keys.RitualNameLockKey;
import codersafterdark.reskillable.base.LevelLockHandler;
import crafttweaker.mc1120.commands.CTChatCommand;
import net.minecraftforge.common.MinecraftForge;

public class BMCompatHandler {
    public static void setup() {
        LevelLockHandler.registerLockKey(Ritual.class, RitualNameLockKey.class, RitualCrystalLockKey.class, RitualCostLockKey.class);

        MinecraftForge.EVENT_BUS.register(new BindHandler());
        MinecraftForge.EVENT_BUS.register(new RitualHandler());

        CTChatCommand.registerCommand(new RitualCommand());
    }
}
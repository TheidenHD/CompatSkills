package codersafterdark.compatskills.common.compats.bloodmagic;

import WayofTime.bloodmagic.ritual.Ritual;
import codersafterdark.compatskills.common.compats.bloodmagic.ritualhandler.RitualHandler;
import codersafterdark.compatskills.common.compats.bloodmagic.ritualhandler.keys.RitualCostLockKey;
import codersafterdark.compatskills.common.compats.bloodmagic.ritualhandler.keys.RitualCrystalLockKey;
import codersafterdark.compatskills.common.compats.bloodmagic.ritualhandler.keys.RitualNameLockKey;
import codersafterdark.reskillable.api.data.LockKey;
import codersafterdark.reskillable.api.data.RequirementHolder;
import codersafterdark.reskillable.base.LevelLockHandler;
import crafttweaker.mc1120.commands.CTChatCommand;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashSet;
import java.util.Set;

public class BMCompatHandler {
    private static Set<Class<? extends LockKey>> lockTypes = new HashSet<>();
    private static boolean ritual;

    public static void setup() {
        MinecraftForge.EVENT_BUS.register(new BindHandler());
        CTChatCommand.registerCommand(new RitualCommand());
    }

    public static void addBMLock(LockKey key, RequirementHolder holder) {
        if (key instanceof RitualNameLockKey) {
            registerRitualLock(RitualNameLockKey.class);
            registerRitual();
        } else if (key instanceof RitualCrystalLockKey) {
            registerRitualLock(RitualCrystalLockKey.class);
            registerRitual();
        } else if (key instanceof RitualCostLockKey) {
            registerRitualLock(RitualCostLockKey.class);
            registerRitual();
        }
        LevelLockHandler.addLockByKey(key, holder);
    }

    private static void registerRitual() {
        if (!ritual) {
            MinecraftForge.EVENT_BUS.register(new RitualHandler());
            ritual = true;
        }
    }

    private static void registerRitualLock(Class<? extends LockKey> ritualLockType) {
        if (!lockTypes.contains(ritualLockType)) {
            LevelLockHandler.registerLockKey(Ritual.class, ritualLockType);
            lockTypes.add(ritualLockType);
        }
    }
}
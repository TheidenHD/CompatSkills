package codersafterdark.compatskills.common.compats.tinkersconstruct;

import codersafterdark.compatskills.CompatSkills;
import codersafterdark.compatskills.common.compats.tinkersconstruct.commands.MaterialDumpCommand;
import codersafterdark.compatskills.common.compats.tinkersconstruct.commands.ModifierDumpCommand;
import codersafterdark.compatskills.common.compats.tinkersconstruct.materiallocks.MaterialLockKey;
import codersafterdark.compatskills.common.compats.tinkersconstruct.modifierlocks.ModifierLockKey;
import codersafterdark.compatskills.utils.CompatModuleBase;
import codersafterdark.reskillable.api.data.LockKey;
import codersafterdark.reskillable.api.data.RequirementHolder;
import codersafterdark.reskillable.base.LevelLockHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.modifiers.IToolMod;

public class TinkersCompatHandler extends CompatModuleBase {
    public static boolean ENABLED;

    private static boolean anyRegistered;

    public static void addTinkersLock(LockKey key, RequirementHolder holder) {
        if (!anyRegistered) {
            LevelLockHandler.registerLockKey(Material.class, MaterialLockKey.class);
            LevelLockHandler.registerLockKey(IToolMod.class, ModifierLockKey.class);
            LevelLockHandler.registerLockKey(ItemStack.class, ToolWrapperLockKey.class);
            anyRegistered = true;
        }
        LevelLockHandler.addLockByKey(key, holder);
    }

    @Override
    public void preInit() {
        ENABLED = true;
    }

    @Override
    public void loadComplete() {
        if (CompatSkills.craftweakerLoaded) {
            CompatSkills.registerCommand(new MaterialDumpCommand());
            CompatSkills.registerCommand(new ModifierDumpCommand());
        }
        MinecraftForge.EVENT_BUS.register(new TinkerLockHandler());
    }
}
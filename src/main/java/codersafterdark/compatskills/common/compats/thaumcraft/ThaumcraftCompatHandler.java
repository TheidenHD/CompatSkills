package codersafterdark.compatskills.common.compats.thaumcraft;

import codersafterdark.compatskills.common.compats.thaumcraft.commands.ThaumcraftFullDump;
import codersafterdark.compatskills.common.compats.thaumcraft.commands.ThaumcraftResearchCategoryDump;
import codersafterdark.compatskills.common.compats.thaumcraft.commands.ThaumcraftResearchEntryDump;
import codersafterdark.compatskills.common.compats.thaumcraft.keys.KnowledgeKey;
import codersafterdark.compatskills.common.compats.thaumcraft.keys.ResearchKey;
import codersafterdark.compatskills.common.compats.thaumcraft.requirements.ResearchRequirement;
import codersafterdark.compatskills.common.compats.thaumcraft.requirements.TCInvalidateHandler;
import codersafterdark.compatskills.common.compats.thaumcraft.requirements.WarpRequirement;
import codersafterdark.compatskills.utils.CompatModuleBase;
import codersafterdark.reskillable.api.ReskillableAPI;
import codersafterdark.reskillable.api.data.LockKey;
import codersafterdark.reskillable.api.data.RequirementHolder;
import codersafterdark.reskillable.api.requirement.RequirementException;
import codersafterdark.reskillable.api.requirement.RequirementRegistry;
import codersafterdark.reskillable.base.LevelLockHandler;
import crafttweaker.mc1120.commands.CTChatCommand;
import net.minecraftforge.common.MinecraftForge;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchEntry;

public class ThaumcraftCompatHandler extends CompatModuleBase {
    public static boolean ENABLED;
    private static boolean knowledge, research;

    @Override
    public void preInit() {
        ENABLED = true;
        MinecraftForge.EVENT_BUS.register(new TCInvalidateHandler());
        RequirementRegistry registry = ReskillableAPI.getInstance().getRequirementRegistry();
        registry.addRequirementHandler("warp", input -> {
            try {
                int warp = Integer.parseInt(input);
                if (warp >= 0) {
                    return new WarpRequirement(warp);
                } else {
                    throw new RequirementException("Warp level must be positive, received: '" + input + "'.");
                }
            } catch (NumberFormatException e) {
                throw new RequirementException("Invalid warp level '" + input + "'.");
            }
        });
        registry.addRequirementHandler("research", input -> {
            ResearchEntry research = ResearchCategories.getResearch(input);
            if (research == null) {
                throw new RequirementException("Invalid research string '" + input + "'.");
            }
            return new ResearchRequirement(input);
        });
    }

    @Override
    public void loadComplete() {
        CTChatCommand.registerCommand(new ThaumcraftFullDump());
        CTChatCommand.registerCommand(new ThaumcraftResearchCategoryDump());
        CTChatCommand.registerCommand(new ThaumcraftResearchEntryDump());
    }

    public static void addThaumcraftLock(LockKey key, RequirementHolder holder) {
        if (key instanceof KnowledgeKey) {
            if (!knowledge) {
                MinecraftForge.EVENT_BUS.register(new KnowledgeHandler());
                knowledge = true;
            }
        } else if (key instanceof ResearchKey) {
            if (!research) {
                MinecraftForge.EVENT_BUS.register(new ResearchHandler());
                research = true;
            }
        }
        LevelLockHandler.addLockByKey(key, holder);
    }
}
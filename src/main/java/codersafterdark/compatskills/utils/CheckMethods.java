package codersafterdark.compatskills.utils;

import WayofTime.bloodmagic.BloodMagic;
import blusunrize.immersiveengineering.api.MultiblockHandler;
import codersafterdark.compatskills.common.compats.reskillable.customcontent.CrTSkill;
import codersafterdark.compatskills.common.compats.reskillable.skillchange.IChangeHandler;
import codersafterdark.reskillable.api.ReskillableRegistries;
import codersafterdark.reskillable.api.skill.Skill;
import codersafterdark.reskillable.api.unlockable.Unlockable;
import com.cout970.magneticraft.api.MagneticraftApi;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.data.DataMap;
import crafttweaker.api.data.IData;
import crafttweaker.api.entity.IEntityDefinition;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.oredict.IOreDictEntry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.modifiers.IModifier;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.research.ResearchCategories;

public class CheckMethods {
    public static boolean checkIItemstack(IItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            CraftTweakerAPI.logError("Itemstack: " + stack + " was found to be either null or empty!");
            return false;
        }
        return true;
    }

    public static boolean checkChangeHandler(IChangeHandler handler) {
        if (handler == null) {
            CraftTweakerAPI.logError("IChangeHandler was found to be null!");
            return false;
        }
        return true;
    }

    public static boolean checkStringArray(String[] strings) {
        if (strings == null || strings.length == 0) {
            CraftTweakerAPI.logError("String Array 'locked' was found to have no entries!");
            return false;
        } else {
            for (String string : strings) {
                if (string == null || string.isEmpty()) {
                    CraftTweakerAPI.logError("String was found to be either null or empty!");
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkOptionalRequirements(String[] strings) {
        if (strings != null && strings.length > 0) {
            for (String string : strings) {
                if (string == null || string.isEmpty()) {
                    CraftTweakerAPI.logError("String was found to be either null or empty!");
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkString(String message) {
        if (message == null || message.isEmpty()) {
            CraftTweakerAPI.logError("'String' Param is either null or empty!");
            return false;
        }
        return true;
    }

    public static boolean checkInt(int i) {
        if (i < 0) {
            CraftTweakerAPI.logError("Integer, " + i + ", was found to be lower than 0, this is not allowed!");
            return false;
        }
        return true;
    }

    public static boolean checkLong(long i) {
        if (i < 0) {
            CraftTweakerAPI.logError("Long, " + i + ", was found to be lower than 0, this is not allowed!");
            return false;
        }
        return true;
    }

    public static boolean checkDouble(double i) {
        if (i < 0) {
            CraftTweakerAPI.logError("Double, " + i + ", was found to be lower than 0, this is not allowed!");
            return false;
        }
        return true;
    }

    public static boolean checkFloat(float i) {
        if (i < 0) {
            CraftTweakerAPI.logError("Float, " + i + ", was found to be lower than 0, this is not allowed!");
            return false;
        }
        return true;
    }

    public static boolean checkModLoaded(String modid) {
        if (modid == null || modid.isEmpty()) {
            CraftTweakerAPI.logError("String Mod ID was Null or Empty!");
            return false;
        } else if (!Loader.isModLoaded(modid)) {
            CraftTweakerAPI.logError("Mod Id: " + modid + " Is not Loaded!");
            return false;
        }
        return true;
    }

    public static boolean checkValidNBTTagCompound(IData tag) {
        if (tag == null) {
            CraftTweakerAPI.logError("'IData' Param is null!");
            return false;
        } else if (!(tag instanceof DataMap)) {
            CraftTweakerAPI.logError("Invalid NBT Tag: " + tag.asString());
            return false;
        }
        return true;
    }

    public static boolean checkValidDimension(int dimension) {
        if (!DimensionManager.isDimensionRegistered(dimension)) {
            CraftTweakerAPI.logError("Dimension: " + dimension + " was found to not be registered!");
            return false;
        }
        return true;
    }

    public static boolean checkValidIEntityDefinition(IEntityDefinition entity) {
        if (entity == null) {
            CraftTweakerAPI.logError("IEntityDefinition was found to be null");
            return false;
        }
        return true;
    }

    public static boolean checkValidTileEntityName(String tileName) {
        if (tileName == null || tileName.isEmpty()) {
            CraftTweakerAPI.logError("String Tile Name Param is either null or empty!");
            return false;
        }
        if (!TileEntity.REGISTRY.containsKey(new ResourceLocation(tileName))) {
            CraftTweakerAPI.logError("Tile Entity " + tileName + " is not a valid Tile Entity Name");
            return false;
        }
        return true;
    }

    public static boolean checkIOreDictEntry(IOreDictEntry entry) {
        if (entry == null || !OreDictionary.doesOreNameExist(entry.getName())) {
            CraftTweakerAPI.logError("Ore Dictionary Entry: " + entry + " was found to be either null or does not exist!");
            return false;
        }
        return true;
    }

    public static boolean checkResourceLocation(String message) {
        if (message == null || message.isEmpty()) {
            CraftTweakerAPI.logError("'String' Param is either null or empty!");
            return false;
        }
        if (!message.contains(":")) {
            CraftTweakerAPI.logError("'String' Param, " + message + ", is not a valid resource location!");
            return false;
        }
        return true;
    }

    /////////////////
    // Blood Magic //
    /////////////////
    public static boolean checkRitual(String ritual) {
        if (ritual == null || ritual.isEmpty()) {
            CraftTweakerAPI.logError("String Ritual was Null or Empty!");
            return false;
        } else if (BloodMagic.RITUAL_MANAGER.getRitual(ritual) == null) {
            CraftTweakerAPI.logError("Invalid Ritual: " + ritual);
            return false;
        }
        return true;
    }

    /////////////////
    // Thaumcraft  //
    /////////////////
    public static boolean checkCategory(String categoryName) {
        if (categoryName == null || categoryName.isEmpty()) {
            CraftTweakerAPI.logError("String Category was Null or Empty!");
            return false;
        } else if (ResearchCategories.getResearchCategory(categoryName) == null) {
            CraftTweakerAPI.logError("Invalid category: " + categoryName);
            return false;
        }
        return true;
    }

    public static boolean checkKnowledgeType(String knowledgeType) {
        if (knowledgeType == null || knowledgeType.isEmpty()) {
            CraftTweakerAPI.logError("String Knowledge Type was Null or Empty!");
            return false;
        }
        IPlayerKnowledge.EnumKnowledgeType[] knowledgeTypes = IPlayerKnowledge.EnumKnowledgeType.values();
        for (IPlayerKnowledge.EnumKnowledgeType type : knowledgeTypes) {
            if (knowledgeType.equals(type.getAbbreviation())) {
                return true;
            }
        }
        CraftTweakerAPI.logError("Invalid Knowledge Type: " + knowledgeType);
        return false;
    }

    /////////////////
    //   Traits    //
    /////////////////

    public static boolean checkIntX(int x) {
        if (x < 0 || x > 4) {
            CraftTweakerAPI.logError("X-Pos needs to be between 0 and 4, found: " + x);
            return false;
        }
        return true;
    }

    public static boolean checkIntY(int y) {
        if (y < 0 || y > 3) {
            CraftTweakerAPI.logError("Y-Pos needs to be between 0 and 3, found: " + y);
            return false;
        }
        return true;
    }

    public static boolean checkParentSkillsString(String parent) {
        if (parent == null || parent.isEmpty()) {
            CraftTweakerAPI.logError("String for Parent Skill was found to be null or empty!");
            return false;
        } else if (!ReskillableRegistries.SKILLS.containsKey(new ResourceLocation(parent))) {
            CraftTweakerAPI.logError("String Resource Location, " + parent + ", is not a Valid Skill!");
            return false;
        }
        return true;
    }

    public static boolean checkParentUnlockablesString(String parent) {
        if (parent == null || parent.isEmpty()) {
            CraftTweakerAPI.logError("String for Parent Unlockable was found to be null or empty!");
            return false;
        } else if (!ReskillableRegistries.UNLOCKABLES.containsKey(new ResourceLocation(parent))) {
            CraftTweakerAPI.logError("String Resource Location, " + parent + ", is not a Valid Unlockable!");
            return false;
        }
        return true;
    }

    public static boolean checkCrTSkillParent(CrTSkill parent) {
        if (parent == null) {
            CraftTweakerAPI.logError("CrTSkill Parent is found to be Null!");
            return false;
        }
        ResourceLocation registryName = parent.getRegistryName();
        if (registryName == null) {
            CraftTweakerAPI.logError("CrTSkill Parent registry found to be Null!");
            return false;
        }
        return checkParentSkillsString(registryName.toString());
    }

    public static boolean checkSkill(Skill skill) {
        if (skill == null) {
            CraftTweakerAPI.logError("Skill is found to be Null!");
            return false;
        }
        ResourceLocation registryName = skill.getRegistryName();
        if (registryName == null) {
            CraftTweakerAPI.logError("Skill registry found to be Null!");
            return false;
        }
        return checkParentSkillsString(registryName.toString());
    }

    public static boolean checkUnlockable(Unlockable unlockable) {
        if (unlockable == null) {
            CraftTweakerAPI.logError("Unlockable is found to be Null!");
            return false;
        }
        ResourceLocation registryName = unlockable.getRegistryName();
        if (registryName == null) {
            CraftTweakerAPI.logError("Unlockable registry found to be Null!");
            return false;
        }
        return checkParentUnlockablesString(registryName.toString());
    }

    ////////////////////////////////
    //   Immersive Engineering    //
    ////////////////////////////////

    public static boolean checkValidMultiblockNameIE(String multiBlock) {
        if (multiBlock == null || multiBlock.isEmpty()) {
            CraftTweakerAPI.logError("String for Multiblock Name was found to be null or empty!");
            return false;
        } else if (MultiblockHandler.getMultiblocks().parallelStream().map(MultiblockHandler.IMultiblock::getUniqueName).noneMatch(multiBlock::equals)) {
            CraftTweakerAPI.logError("No valid IE multiblock match was found for the String: " + multiBlock);
            return false;
        }
        return true;
    }

    ///////////////////////
    //   Magneticraft    //
    ///////////////////////

    public static boolean checkValidMultiblockNameMag(String multiBlock) {
        if (multiBlock == null || multiBlock.isEmpty()) {
            CraftTweakerAPI.logError("String for Multiblock Name was found to be null or empty!");
            return false;
        } else if (!MagneticraftApi.getMultiblockManager().getRegisteredMultiblocks().containsKey(multiBlock)) {
            CraftTweakerAPI.logError("No valid MagnetiCraft multiblock match was found for the String: " + multiBlock);
            return false;
        }
        return true;
    }

    /////////////////////////////
    //   Tinker's Construct    //
    /////////////////////////////

    public static boolean checkModifier(String identifier) {
        if (identifier == null || identifier.isEmpty()) {
            CraftTweakerAPI.logError("String for Modifier Identifier was found to be null or empty!");
            return false;
        } else if (TinkerRegistry.getAllModifiers().parallelStream().map(IModifier::getIdentifier).noneMatch(identifier::equals)) {
            CraftTweakerAPI.logError("No valid modifier match was found for the String: " + identifier);
            return false;
        }
        return true;
    }

    public static boolean checkMaterial(String identifier) {
        if (identifier == null || identifier.isEmpty()) {
            CraftTweakerAPI.logError("String for Material Identifier was found to be null or empty!");
            return false;
        } else if (TinkerRegistry.getAllMaterials().parallelStream().map(Material::getIdentifier).noneMatch(identifier::equals)) {
            CraftTweakerAPI.logError("No valid material match was found for the String: " + identifier);
            return false;
        }
        return true;
    }


    ////////////////////
    // Utility Method //
    ////////////////////

    public static IBlockState convertItemStackToIBlockState(ItemStack stack) {
        if (stack.getItem() instanceof ItemBlock) {
            return ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
        }
        CraftTweakerAPI.logError("ItemStack: " + stack.getDisplayName() + " is not an instanceof ItemBlock!");
        return null;
    }
}
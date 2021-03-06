package codersafterdark.compatskills.common.compats.minecraft.item.harvestlevel;

import codersafterdark.reskillable.api.requirement.Requirement;
import codersafterdark.reskillable.api.requirement.RequirementComparision;
import codersafterdark.reskillable.api.requirement.RequirementException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import java.util.Objects;

public class HarvestLevelRequirement extends Requirement { //TODO support optional specific toolType
    private final int harvestLevel;

    public HarvestLevelRequirement(int level) {
        this.harvestLevel = level;
        this.tooltip = TextFormatting.GRAY + " - " + new TextComponentTranslation("compatskills.requirements.format.harvest_level", "%s", harvestLevel).getUnformattedComponentText();
    }

    public static HarvestLevelRequirement fromString(String input) throws RequirementException {
        if (input.isEmpty()) {
            throw new RequirementException("No harvest level given.");
        }
        try {
            return new HarvestLevelRequirement(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new RequirementException("Invalid harvest level '" + input + "'.");
        }
    }

    @Override
    public boolean achievedByPlayer(EntityPlayer player) {
        return hasHarvestLevel(player.getHeldItemMainhand()) || hasHarvestLevel(player.getHeldItemOffhand());
    }

    @Override
    public RequirementComparision matches(Requirement o) {
        if (o instanceof HarvestLevelRequirement) {
            HarvestLevelRequirement other = (HarvestLevelRequirement) o;
            if (harvestLevel == other.harvestLevel) {
                return RequirementComparision.EQUAL_TO;
            }
            return harvestLevel > other.harvestLevel ? RequirementComparision.GREATER_THAN : RequirementComparision.LESS_THAN;
        }
        return RequirementComparision.NOT_EQUAL;
    }

    private boolean hasHarvestLevel(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        Item item = stack.getItem();
        return item.getToolClasses(stack).stream().anyMatch(toolClass -> item.getHarvestLevel(stack, toolClass, null, null) >= harvestLevel);
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof HarvestLevelRequirement && harvestLevel == ((HarvestLevelRequirement) o).harvestLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(harvestLevel);
    }
}
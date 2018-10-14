package codersafterdark.compatskills.common.compats.minecraft.dimension.dimensionlocks;

import codersafterdark.reskillable.api.data.PlayerData;
import codersafterdark.reskillable.api.data.PlayerDataHandler;
import codersafterdark.reskillable.api.data.RequirementHolder;
import codersafterdark.reskillable.base.ConfigHandler;
import codersafterdark.reskillable.base.LevelLockHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.stream.Collectors;

public class DimensionLockHandler {
    @SubscribeEvent
    public void onDimensionChanging(EntityTravelToDimensionEvent event) {
        if (event.isCanceled() || !(event.getEntity() instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) event.getEntity();
        if (!ConfigHandler.enforceOnCreative && player.isCreative()) {
            return;
        }
        PlayerData data = PlayerDataHandler.get(player);
        RequirementHolder requirementHolder = LevelLockHandler.getLockByKey(new DimensionLockKey(event.getDimension()));
        if (!requirementHolder.equals(LevelLockHandler.EMPTY_LOCK) && !data.matchStats(requirementHolder)) {
            event.setCanceled(true);
            TextComponentTranslation error = new TextComponentTranslation("compatskills.dimension.travelError");
            TextComponentTranslation error2 = new TextComponentTranslation("compatskills.misc.Requirements");
            String reqString = requirementHolder.getRequirements().stream().map(requirement -> "\n " + requirement.getToolTip(data) + ' ').collect(Collectors.joining());
            ITextComponent textComponent = new TextComponentString(error.getUnformattedComponentText() + ' ' + error2.getUnformattedComponentText() + ' ' + reqString);
            player.sendStatusMessage(textComponent, false);
        }
    }
}
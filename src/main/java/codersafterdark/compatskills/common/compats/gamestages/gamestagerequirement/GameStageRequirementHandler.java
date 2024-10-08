package codersafterdark.compatskills.common.compats.gamestages.gamestagerequirement;

import codersafterdark.reskillable.api.requirement.RequirementCache;
import net.darkhax.gamestages.event.GameStageEvent;
import net.darkhax.gamestages.event.StagesSyncedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GameStageRequirementHandler {
    @SubscribeEvent
    public void gameStageAdded(GameStageEvent.Added event) {
        RequirementCache.invalidateCache(event.getEntityPlayer(), GameStageRequirement.class);
    }

    @SubscribeEvent
    public void gameStageRemoved(GameStageEvent.Removed event) {
        RequirementCache.invalidateCache(event.getEntityPlayer(), GameStageRequirement.class);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void stagesSync(StagesSyncedEvent event) {
        RequirementCache.invalidateCache(event.getEntityPlayer(), GameStageRequirement.class);
    }
}
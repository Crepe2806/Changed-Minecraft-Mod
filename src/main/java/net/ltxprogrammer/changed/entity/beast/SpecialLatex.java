package net.ltxprogrammer.changed.entity.beast;

import net.ltxprogrammer.changed.entity.HairStyle;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.ltxprogrammer.changed.entity.GooType;
import net.ltxprogrammer.changed.entity.TransfurMode;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.ChangedEntities;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.ltxprogrammer.changed.util.Color3;
import net.ltxprogrammer.changed.util.PatreonBenefits;
import net.minecraft.SharedConstants;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpecialLatex extends ChangedEntity {
    private UUID assignedUUID = null;
    public String wantedState = "default";
    public List<String> possibleModels = new ArrayList<>();
    public PatreonBenefits.SpecialForm specialForm = null;

    public SpecialLatex(EntityType<? extends ChangedEntity> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }
    
    public PatreonBenefits.EntityData getCurrentData() {
        if (specialForm == null)
            return null;
        return specialForm.entityData().getOrDefault(wantedState, specialForm.getDefaultEntity());
    }

    public void setSpecialForm(UUID uuid) {
        this.assignedUUID = uuid;
        this.specialForm = PatreonBenefits.getPlayerSpecialForm(uuid);
    }

    public EntityDimensions getDimensions(Pose pose) {
        if (specialForm == null)
            return super.getDimensions(pose);

        EntityDimensions core = getCurrentData().dimensions();

        if (this.isVisuallySwimming())
            return EntityDimensions.scalable(core.width, core.width);
        return switch (pose) {
            case STANDING -> core;
            case SLEEPING -> SLEEPING_DIMENSIONS;
            case FALL_FLYING, SWIMMING, SPIN_ATTACK -> EntityDimensions.scalable(core.width, core.width);
            case CROUCHING -> EntityDimensions.scalable(core.width, core.height - 0.2f);
            case DYING -> EntityDimensions.fixed(0.2f, 0.2f);
            default -> core;
        };
    }

    @Override
    public Color3 getHairColor(int layer) {
        if (specialForm == null)
            return Color3.WHITE;
        try {
            return getCurrentData().hairColors().get(layer);
        } catch (Exception ignored) {
            if (!getCurrentData().hairColors().isEmpty())
                return getCurrentData().hairColors().get(0);
            else
                return Color3.WHITE;
        }
    }

    public @Nullable List<HairStyle> getValidHairStyles() {
        if (specialForm == null)
            return HairStyle.Collection.EMPTY;
        return getCurrentData().hairStyles();
    }

    public UUID getAssignedUUID() {
        return assignedUUID;
    }

    @Override
    public GooType getGooType() {
        if (specialForm == null)
            return GooType.NEUTRAL;
        return specialForm.variant().getGooType();
    }

    @Override
    public TransfurMode getTransfurMode() {
        if (specialForm == null)
            return TransfurMode.REPLICATION;
        return specialForm.variant().transfurMode();
    }

    @Override
    public Color3 getDripColor() {
        if (specialForm == null)
            return null;
        return getCurrentData().dripColors().isEmpty() ? null :
                getCurrentData().dripColors().get(level.random.nextInt(getCurrentData().dripColors().size()));
    }

    @Override
    public TransfurVariant<?> getTransfurVariant() {
        return specialForm.variant();
    }

    public static void init() {}


    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class Event {
        @SubscribeEvent
        public static void onVariantAssigned(ProcessTransfur.EntityVariantAssigned event) {
            if (SharedConstants.IS_RUNNING_IN_IDE || !FMLLoader.isProduction())
                return; // Ignore

            if (event.variant == null || event.variant.getEntityType() != ChangedEntities.SPECIAL_LATEX.get())
                return;

            if (event.livingEntity instanceof Player player)
                event.variant = PatreonBenefits.getPlayerSpecialVariant(player.getUUID()); // Ensure player was assigned correct special variant (or null if none)
        }
    }
}

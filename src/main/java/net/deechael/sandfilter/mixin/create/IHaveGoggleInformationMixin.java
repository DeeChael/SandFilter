package net.deechael.sandfilter.mixin.create;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.foundation.utility.Lang;
import com.simibubi.create.foundation.utility.LangBuilder;
import net.deechael.sandfilter.utils.SandFilterHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;
import java.util.Optional;

@Mixin(value = IHaveGoggleInformation.class, remap = false)
public interface IHaveGoggleInformationMixin {

    /**
     * @author mlus
     * @reason add purity information
     */
    @Overwrite
    default boolean containedFluidTooltip(List<Component> tooltip, boolean isPlayerSneaking,
                                          LazyOptional<IFluidHandler> handler) {
        Optional<IFluidHandler> resolve = handler.resolve();
        if (resolve.isEmpty())
            return false;

        IFluidHandler tank = resolve.get();
        if (tank.getTanks() == 0)
            return false;

        LangBuilder mb = Lang.translate("generic.unit.millibuckets");
        Lang.translate("gui.goggles.fluid_container")
                .forGoggles(tooltip);

        boolean isEmpty = true;
        for (int i = 0; i < tank.getTanks(); i++) {
            FluidStack fluidStack = tank.getFluidInTank(i);
            if (fluidStack.isEmpty())
                continue;

            Lang.builder()
                    .add(Lang.translate(SandFilterHelper.isPurified(fluidStack) ? "thirst.purity.purified" : "thirst.purity.dirty"))
                    .add(fluidStack.getDisplayName().copy())
                    .style(sandfilter$getPurityColor(true))
                    .forGoggles(tooltip, 1);

            Lang.builder()
                    .add(Lang.number(fluidStack.getAmount())
                            .add(mb)
                            .style(ChatFormatting.GOLD))
                    .text(ChatFormatting.GRAY, " / ")
                    .add(Lang.number(tank.getTankCapacity(i))
                            .add(mb)
                            .style(ChatFormatting.DARK_GRAY))
                    .forGoggles(tooltip, 1);

            isEmpty = false;
        }

        if (tank.getTanks() > 1) {
            if (isEmpty)
                tooltip.remove(tooltip.size() - 1);
            return true;
        }

        if (!isEmpty)
            return true;

        Lang.translate("gui.goggles.fluid_container.capacity")
                .add(Lang.number(tank.getTankCapacity(0))
                        .add(mb)
                        .style(ChatFormatting.GOLD))
                .style(ChatFormatting.GRAY)
                .forGoggles(tooltip, 1);

        return true;
    }

    @Unique
    default ChatFormatting sandfilter$getPurityColor(boolean purified) {
        if (purified) {
            return ChatFormatting.AQUA;
        } else {
            return ChatFormatting.DARK_GRAY;
        }
    }

}

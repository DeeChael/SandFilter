package net.deechael.sandfilter.mixin.create;

import com.simibubi.create.content.fluids.transfer.GenericItemEmptying;
import com.simibubi.create.foundation.utility.Pair;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sfiomn.legendarysurvivaloverhaul.registry.ItemRegistry;

@Mixin(value = GenericItemEmptying.class, remap = false)
public class GenericItemEmptyingMixin {

    @Inject(method = "emptyItem", at = @At("RETURN"), cancellable = true, remap = false)
    private static void inject$emptyItem$return(Level world, ItemStack stack, boolean simulate, CallbackInfoReturnable<Pair<FluidStack, ItemStack>> cir) {
        Pair<FluidStack, ItemStack> output = cir.getReturnValue();

        if (stack.is(ItemRegistry.PURIFIED_WATER_BOTTLE.get())) {
            FluidStack fluidStack = output.getFirst();
            if (fluidStack.isEmpty())
                return;
            CompoundTag tag = fluidStack.getOrCreateTag();
            tag.putBoolean("Purified", true);
            fluidStack.setTag(tag);
            cir.setReturnValue(Pair.of(fluidStack, output.getSecond()));
        }
    }

}

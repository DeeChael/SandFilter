package net.deechael.sandfilter.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sfiomn.legendarysurvivaloverhaul.registry.ItemRegistry;

public final class SandFilterHelper {

    public static FluidStack setPurified(FluidStack fluid, boolean isPurified) {
        CompoundTag tag = fluid.getOrCreateTag();
        tag.putBoolean("Purified", isPurified);
        return fluid;
    }

    public static boolean isPurified(FluidStack fluid) {
        CompoundTag tag = fluid.getOrCreateTag();
        if (!tag.contains("Purified"))
            tag.putBoolean("Purified", false);
        return tag.getBoolean("Purified");
    }

    public static void fill(FluidStack availableFluid, CallbackInfoReturnable<ItemStack> cir) {
        if (SandFilterHelper.isPurified(availableFluid)) {
            ItemStack itemStack = cir.getReturnValue();
            if (itemStack.getItem() == Items.AIR)
                return;
            if (!itemStack.is(Items.POTION))
                return;
            if (PotionUtils.getPotion(itemStack) != Potions.WATER)
                return;
            ItemStack newStack = new ItemStack(ItemRegistry.PURIFIED_WATER_BOTTLE.get());
            cir.setReturnValue(newStack);
        }
    }

    private SandFilterHelper() {
    }

}

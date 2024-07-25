package net.deechael.sandfilter.mixin.legendarysurvivaloverhaul;

import net.deechael.sandfilter.registry.SandFilterRegistry;
import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sfiomn.legendarysurvivaloverhaul.registry.CreativeTabRegistry;

@Mixin(CreativeTabRegistry.class)
public class CreativeTabRegistryMixin {

    @Inject(method = "lambda$static$1", at = @At(value = "RETURN"))
    private static void inject$displayItems$return(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output list, CallbackInfo ci) {
        list.accept(
                SandFilterRegistry.SAND_FILTER_BLOCK.asStack()
        );
    }

}

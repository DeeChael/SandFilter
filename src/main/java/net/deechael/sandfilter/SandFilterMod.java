package net.deechael.sandfilter;

import net.deechael.sandfilter.compat.create.ponder.SandFilterPonders;
import net.deechael.sandfilter.registry.SandFilterRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SandFilterMod.ID)
public class SandFilterMod {

    public final static String ID = "sandfilter";

    public SandFilterMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::setupClient);

        SandFilterRegistry.init();
    }

    private void setupClient(FMLClientSetupEvent event) {
        event.enqueueWork(SandFilterPonders::register);
    }

}

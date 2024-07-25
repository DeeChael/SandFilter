package net.deechael.sandfilter.compat.create.ponder;

import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.foundation.ponder.PonderTag;
import com.simibubi.create.infrastructure.ponder.AllPonderTags;
import net.deechael.sandfilter.SandFilterMod;
import net.deechael.sandfilter.compat.create.ponder.scene.SandFilterScene;
import net.deechael.sandfilter.registry.SandFilterRegistry;
import net.minecraft.resources.ResourceLocation;


public class SandFilterPonders {

    public static final PonderTag PURIFICATION = new PonderTag(new ResourceLocation(SandFilterMod.ID, "purification"))
            .item(SandFilterRegistry.SAND_FILTER_BLOCK.get().asItem(), true, false)
            .defaultLang("Purification", "Components which purifying water");

    static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(SandFilterMod.ID);

    public static void register() {
        HELPER.addStoryBoard(
                SandFilterRegistry.SAND_FILTER_BLOCK,
                "sand_filter",
                SandFilterScene::filtering,
                AllPonderTags.FLUIDS,
                PURIFICATION
        );
    }

}

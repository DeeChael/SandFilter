package net.deechael.sandfilter.registry;

import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.deechael.sandfilter.SandFilterMod;
import net.deechael.sandfilter.compat.create.SandFilterBlock;
import net.deechael.sandfilter.compat.create.SandFilterTileEntity;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;

public class SandFilterRegistry {

    public static final NonNullSupplier<Registrate> REGISTRATE = NonNullSupplier.lazy(() -> Registrate.create(SandFilterMod.ID));

    public static final BlockEntry<SandFilterBlock> SAND_FILTER_BLOCK = REGISTRATE.get()
            .block("sand_filter", SandFilterBlock::new)
            .initialProperties(SharedProperties::copperMetal)
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(), AssetLookup.partialBaseModel(ctx, prov)))
            .item(AssemblyOperatorBlockItem::new)
            .transform(customItemModel())
            .register();

    public static final BlockEntityEntry<SandFilterTileEntity> SAND_FILTER_TILE_ENTITY = REGISTRATE.get()
            .blockEntity("sand_filter", SandFilterTileEntity::new)
            .validBlocks(SAND_FILTER_BLOCK)
            .register();

    public static void init() {
    }

}


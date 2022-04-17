package me.give_me_moneyz.abhorsen.core.init;

import me.give_me_moneyz.abhorsen.Abhorsen;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            Abhorsen.MOD_ID);

    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item",
            () -> new Item(new Item.Properties().group(Abhorsen.TUTORIAL_GROUP)));

    public static final RegistryObject<BlockItem> EXAMPLE_BLOCK = ITEMS.register("example_block",
            () -> new BlockItem(BlockInit.EXAMPLE_BLOCK.get(),
                    new Item.Properties().group(Abhorsen.TUTORIAL_GROUP)));

    public static final RegistryObject<BlockItem> EXAMPLE_ORE = ITEMS.register("example_ore",
            () -> new BlockItem(BlockInit.EXAMPLE_ORE.get(), new Item.Properties().group(Abhorsen.TUTORIAL_GROUP)));
}

package com.skyligthmodding.acofi.init;

import com.skyligthmodding.acofi.ACOFI;
import com.skyligthmodding.acofi.items.custom.FieryUpgradeTemplate;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ACOFIItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ACOFI.MODID);

    public static final RegistryObject<Item> FIERY_UPGRADE_TEMPLATE = ITEMS.register("fiery_upgrade_template", FieryUpgradeTemplate::new);
}

package com.skylightmodding.acofi;

import com.mojang.logging.LogUtils;

import com.skylightmodding.acofi.items.custom.FieryUpgradeTemplate;

import net.minecraft.SharedConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import org.slf4j.Logger;

import twilightforest.init.TFCreativeTabs;

import java.nio.file.Path;
import java.util.List;

@Mod(ACOFI.MODID)
public class ACOFI {
    public static final String MODID = "acofi";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item> FIERY_UPGRADE_TEMPLATE = ITEMS.register("fiery_upgrade_template", FieryUpgradeTemplate::new);

    public ACOFI(IEventBus modEventBus) {
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::packSetup);

        ITEMS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == TFCreativeTabs.EQUIPMENT.getKey()) {
            event.accept(FIERY_UPGRADE_TEMPLATE);
        }
    }

    public void packSetup(AddPackFindersEvent event) {
        this.loadACOFIAdd(event);
    }

    /*
    * copied from
    * https://github.com/The-Aether-Team/The-Aether/blob/1.20.4-develop/src/main/java/com/aetherteam/aether/Aether.java
    */
    private void loadACOFIAdd(AddPackFindersEvent event) {
            Path resourcePath = ModList.get().getModFileById(MODID).getFile().findResource("acofi_add");
            PackMetadataSection metadata = new PackMetadataSection(Component.translatable("pack.acofi.acofi_add.description"), SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES));
            event.addRepositorySource((source) ->
                    source.accept(Pack.create(
                            "acofi_add",
                            Component.translatable("pack.acofi.acofi_add.title"),
                            true,
                            new PathPackResources.PathResourcesSupplier(resourcePath, true),
                            new Pack.Info(metadata.description(), PackCompatibility.COMPATIBLE, FeatureFlagSet.of(), List.of(), true),
                            Pack.Position.TOP,
                            false,
                            PackSource.BUILT_IN)
                    )
            );
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {}
    }
}

package com.skylightmodding.acofi;

import net.minecraft.ChatFormatting;
import net.minecraft.SharedConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.server.packs.PackType;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.resource.PathPackResources;

import com.mojang.logging.LogUtils;

import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.function.UnaryOperator;

import com.skylightmodding.acofi.init.ACOFIItems;

import twilightforest.init.TFCreativeTabs;

@Mod(ACOFI.MODID)
public class ACOFI {
    public static final String MODID = "acofi";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ACOFI() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::packSetup);
        modEventBus.addListener(this::addCreative);

        ACOFIItems.ITEMS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == TFCreativeTabs.EQUIPMENT.getKey()) {
            event.accept(ACOFIItems.FIERY_UPGRADE_TEMPLATE.get());
        }
    }

    /* ********************** copy + pasted from https://github.com/The-Aether-Team/The-Aether/blob/1.20.1-develop/src/main/java/com/aetherteam/aether/Aether.java ********************** */
    public void packSetup(AddPackFindersEvent event) {
        this.setupACOFIAddPack(event);
    }

    private void setupACOFIAddPack(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            Path resourcePath = ModList.get().getModFileById(MODID).getFile().findResource("acofi_add");
            PathPackResources pack = new PathPackResources(ModList.get().getModFileById(MODID).getFile().getFileName() + ":" + resourcePath, true, resourcePath);
            PackMetadataSection metadata = new PackMetadataSection(Component.translatable("pack.acofi.acofi_add.description"), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA));
            event.addRepositorySource((source) ->
                    source.accept(Pack.create("acofi_add",
                            Component.translatable("pack.acofi.acofi_add.title"), false,
                            (string) -> pack,
                            new Pack.Info(metadata.getDescription(), metadata.getPackFormat(PackType.SERVER_DATA), metadata.getPackFormat(PackType.CLIENT_RESOURCES), FeatureFlagSet.of(), pack.isHidden()),
                            PackType.SERVER_DATA,
                            Pack.Position.TOP, false,
                            create(decorateWithSource("pack.source.builtin"), true))
                    )
            );
        }
    }

    static PackSource create(final UnaryOperator<Component> decorator, final boolean shouldAddAutomatically) {
        return new PackSource() {
            public Component decorate(Component component) { return decorator.apply(component); }
            public boolean shouldAddAutomatically() { return shouldAddAutomatically; }
        };
    }

    private static UnaryOperator<Component> decorateWithSource(String translationKey) {
        Component component = Component.translatable(translationKey);
        return (name) -> Component.translatable("pack.nameAndSource", name, component).withStyle(ChatFormatting.GRAY);
    }
    /* ************************************************************************************************************************************************************************************  */
}

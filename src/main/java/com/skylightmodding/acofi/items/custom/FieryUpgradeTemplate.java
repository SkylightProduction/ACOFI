package com.skylightmodding.acofi.items.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class FieryUpgradeTemplate extends SmithingTemplateItem {
    private static final Component FIERY_UPGRADE = Component.translatable("upgrade.acofi.fiery_upgrade").withStyle(ChatFormatting.GRAY);
    private static final Component FIERY_UPGRADE_APPLIES_TO = Component.translatable("item.acofi.smithing_template.fiery_upgrade.applies_to").withStyle(ChatFormatting.BLUE);
    private static final Component FIERY_UPGRADE_INGREDIENTS = Component.translatable("item.acofi.smithing_template.fiery_upgrade.ingredients").withStyle(ChatFormatting.BLUE);
    private static final Component FIERY_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable("item.acofi.smithing_template.fiery_upgrade.base_slot_description");
    private static final Component FIERY_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable("item.acofi.smithing_template.fiery_upgrade.additions_slot_description");

    private static final ResourceLocation EMPTY_SLOT_INGOT = new ResourceLocation("item/empty_slot_ingot");
    private static final ResourceLocation EMPTY_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_SWORD = new ResourceLocation("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = new ResourceLocation("item/empty_slot_pickaxe");

    public FieryUpgradeTemplate() {
        super(
                FIERY_UPGRADE_APPLIES_TO, FIERY_UPGRADE_INGREDIENTS, FIERY_UPGRADE, FIERY_UPGRADE_BASE_SLOT_DESCRIPTION, FIERY_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_BOOTS), List.of(EMPTY_SLOT_INGOT)
        );
    }
}

package com.ibiaasif216.chakrachargemod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChakraChargeMod implements ModInitializer {
    public static final String MOD_ID = "chakrachargemod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Item CHAKRA_CHARGE = new Item(new FabricItemSettings().stacksTo(1)) {
        @Override
        public void inventoryTick(ItemStack stack, Level world, Player player, int slotId, boolean isSelected) {
            if (isSelected) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1));
                for (int i = 0; i < 10; i++) {
                    world.addParticle(ParticleTypes.EFFECT, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
                }
                player.getScoreboard().getOrCreateObjective("Chakra").addScore(player, 1);
                stack.shrink(1);
            }
        }
    };

    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "chakra_charge"), CHAKRA_CHARGE);
        LOGGER.info("Chakra Charge Mod initialized");
    }
}
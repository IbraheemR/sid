package com.ibraheemrodrigues.sid;

import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;
import net.minecraft.util.profiler.ProfileResult;
import net.minecraft.util.profiler.ProfilerSystem;
import net.minecraft.util.profiler.TickTimeTracker;

import java.util.Timer;
import java.util.TimerTask;

public class Main implements ModInitializer {
    public static SIDConfig config;


    @Override
    public void onInitialize() {
            config = SIDConfig.load();
            System.out.println("SID UP! :)");

        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(CommandManager.literal("tps").executes(context -> {

                float tickTime = ((MinecraftServer) FabricLoader.getInstance().getGameInstance()).getTickTime();
                float tps = 1000/tickTime;

                tps = tps > 20 ? 20: tps;

                char color;
                if (tps > 15) {
                    color = 'a';
                } else if (tps > 10) {
                    color = 'e';
                } else {
                    color = '4';
                }

                context.getSource().sendFeedback(new LiteralText(
                        String.format("Server reports: §L§%s%.1ftps§r (%.2f ms)", color, tps, tickTime)
                ), false);
                return 1;
            }));
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(CommandManager.literal("suicide").executes(context -> {

                Entity e = context.getSource().getEntity();

                e.kill();
                context.getSource().sendFeedback(new TranslatableText("commands.kill.success.single", new Object[]{e.getDisplayName()}), true);

                return 1;
            }));
        });
    }

    private static int getTicks() {
        return MinecraftClient.getInstance().getServer().getTicks();
    }
}

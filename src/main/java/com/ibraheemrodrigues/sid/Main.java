package com.ibraheemrodrigues.sid;

import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {
    public static SIDConfig config;

    @Override
    public void onInitialize() {
            config = SIDConfig.load();
            System.out.println("SID UP! :)");
    }
}

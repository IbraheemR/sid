package com.ibraheemrodrigues.sid.mixin;

import com.ibraheemrodrigues.sid.Main;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;


@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
	@Inject(at = @At("RETURN"), method = "getServerModName", cancellable = true)
	private void getServerModName(CallbackInfoReturnable<String> cir) {
		cir.setReturnValue(Main.config.serverBrand);
	}
}

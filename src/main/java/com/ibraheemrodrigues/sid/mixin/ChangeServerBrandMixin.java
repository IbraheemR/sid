package com.ibraheemrodrigues.sid.mixin;

import com.ibraheemrodrigues.sid.SIDMain;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftServer.class)
public class ChangeServerBrandMixin {
	// MinecraftServer.getServerModName() uses @DontObfuscate so we need to set remap = false
	@Inject(at = @At("RETURN"), method = "getServerModName", cancellable = true, remap = false)
	private void getServerModName(CallbackInfoReturnable<String> cir) {
		if (SIDMain.CUSTOM_BRAND != null) cir.setReturnValue(SIDMain.CUSTOM_BRAND);

	}
}

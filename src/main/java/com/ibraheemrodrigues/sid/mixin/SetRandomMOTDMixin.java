package com.ibraheemrodrigues.sid.mixin;

import com.ibraheemrodrigues.sid.SIDMain;
import net.minecraft.server.ServerMetadata;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerMetadata.class)
public class SetRandomMOTDMixin {
    @Inject(at = @At("RETURN"), method = "getDescription", cancellable = true)
    private void getDescription(CallbackInfoReturnable<Text> cir) {
        if (SIDMain.CUSTOM_MOTD_LIST.length > 0) {
            String randomMOTD = SIDMain.CUSTOM_MOTD_LIST[(int) (Math.random() * SIDMain.CUSTOM_MOTD_LIST.length)];

            cir.setReturnValue(Text.literal(randomMOTD));

        }
    }
}

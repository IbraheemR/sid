package com.ibraheemrodrigues.sid.mixin;

        import com.ibraheemrodrigues.sid.Main;
        import com.ibraheemrodrigues.sid.SIDConfig;
        import net.minecraft.server.ServerMetadata;
        import net.minecraft.text.LiteralText;
        import net.minecraft.text.Text;
        import org.spongepowered.asm.mixin.Mixin;
        import org.spongepowered.asm.mixin.injection.At;
        import org.spongepowered.asm.mixin.injection.Inject;
        import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.Random;


@Mixin(ServerMetadata.class)
public class ServerMetadataMixin {
    @Inject(at = @At("RETURN"), method = "getDescription", cancellable = true)
    private void getDescription(CallbackInfoReturnable<Text> cir) {
        if (Main.config.motdType == SIDConfig.MotdOptions.LIST){
            String[] motds = Main.config.motds;
            String motd = motds[new Random().nextInt(motds.length)];

            motd = String.format(Main.config.motdTemplate, motd);

            cir.setReturnValue(new LiteralText(motd));
        }
    }
}


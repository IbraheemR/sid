package com.ibraheemrodrigues.sid;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name="sid")
public class SIDConfig implements ConfigData {
    @Comment("Server brand as seen in F3 debug menu")
    public String serverBrand = "SIDServer";

    @Comment("\"DEFAULT\" - load from server.properties.\n\"LIST\" - randomly pick from \"motds\" list below.")
    public MotdOptions motdType = MotdOptions.DEFAULT;

    @Comment("Template to insert \"list\" motds into. Use \"%s\" for insertion point.")
    public String motdTemplate = "%s";

    @Comment("List of motds to randomly pick if \"motdType\" is \"list\".")
    public String[] motds = {"A SIDServer", "SID says:\nChange Me!"};

    public enum MotdOptions {
        DEFAULT,
        LIST,
    }

    static SIDConfig load() {
        return AutoConfig.register(SIDConfig.class, JanksonConfigSerializer::new).getConfig();
    }
}



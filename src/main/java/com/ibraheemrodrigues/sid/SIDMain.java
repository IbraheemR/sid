package com.ibraheemrodrigues.sid;

import net.fabricmc.api.DedicatedServerModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SIDMain implements DedicatedServerModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("sid");

	public static String CUSTOM_BRAND;
	public static String[] CUSTOM_MOTD_LIST = new String[] {};

	@Override
	public void onInitializeServer() {
		LOGGER.info("SID up!");

		ConfigFile brandConfig = new ConfigFile(
				"brand.txt",
				new String[] {
					"# Add a custom brand below",
					"#SID"
				}
		);
		CUSTOM_BRAND = brandConfig.readFirstLine();
		if (CUSTOM_BRAND != null) {
			LOGGER.info("Set custom brand: " + CUSTOM_BRAND);
		} else {
			LOGGER.info("No custom brand set.");
		}

		ConfigFile motdConfig = new ConfigFile("motds.txt", new String[] {
				"# Add custom motds below. Supports color codes & \\n. Lines starting with # are ignored.",
				"# Examples:",
				"# §1Hello world!",
				"# §aThis is a random message!",
				"# §4Nice shoes!",
				"# §dThis message spans\\nmultiple lines!"
		});
		CUSTOM_MOTD_LIST = motdConfig.readAllLines();
		if (CUSTOM_MOTD_LIST.length > 0) {
			LOGGER.info("Using random MOTDs from motds.txt. Will override server.properties.");
		} else {
			LOGGER.info("No random MOTDs loaded.");
		}
	}
}

package com.ibraheemrodrigues.sid;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class SIDMain implements DedicatedServerModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("sid");

	public static String CUSTOM_BRAND;

	@Override
	public void onInitializeServer() {
		LOGGER.info("SID up!");

		File brandFile = new File("brand.txt");

		if (brandFile.isFile()) {
			try {
				Scanner brandReader = new Scanner(brandFile);
				String brand = "";
				if (brandReader.hasNext()) {
					brand = brandReader.nextLine();
				}
				if (brand == "") {
					LOGGER.info("No custom brand set in brand.txt");
				} else {
					CUSTOM_BRAND = brand;
					LOGGER.info("Set brand to '" + CUSTOM_BRAND + "'");
				}
			} catch (FileNotFoundException e) {
				LOGGER.warn("Failed to read brand.txt! Will not set custom brand.");
			}
		} else {
			try {
				LOGGER.info("No brand.txt file exists. Creating it...");
				brandFile.createNewFile();
			} catch (IOException e) {
				LOGGER.warn("Failed to create brand.txt!");
			}
		}
	}
}

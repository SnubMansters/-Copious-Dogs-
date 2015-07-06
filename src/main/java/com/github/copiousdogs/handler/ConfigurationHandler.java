package com.github.copiousdogs.handler;

import java.io.File;

import com.github.copiousdogs.entity.EntityAmericanBulldog;
import com.github.copiousdogs.entity.EntityAustralianShepherd;
import com.github.copiousdogs.entity.EntityBeagle;
import com.github.copiousdogs.entity.EntityBerneseMountain;
import com.github.copiousdogs.entity.EntityBloodhound;
import com.github.copiousdogs.entity.EntityBoxer;
import com.github.copiousdogs.entity.EntityCardiganCorgi;
import com.github.copiousdogs.entity.EntityChihuahua;
import com.github.copiousdogs.entity.EntityCollie;
import com.github.copiousdogs.entity.EntityDachshund;
import com.github.copiousdogs.entity.EntityDalmatian;
import com.github.copiousdogs.entity.EntityDoberman;
import com.github.copiousdogs.entity.EntityEskimoSpitz;
import com.github.copiousdogs.entity.EntityFrenchBulldog;
import com.github.copiousdogs.entity.EntityGermanShepherd;
import com.github.copiousdogs.entity.EntityGoldenRetriever;
import com.github.copiousdogs.entity.EntityGreatDane;
import com.github.copiousdogs.entity.EntityHusky;
import com.github.copiousdogs.entity.EntityNewfoundland;
import com.github.copiousdogs.entity.EntityPapillon;
import com.github.copiousdogs.entity.EntityPembroke;
import com.github.copiousdogs.entity.EntityPomeranian;
import com.github.copiousdogs.entity.EntityPoodle;
import com.github.copiousdogs.entity.EntityPug;
import com.github.copiousdogs.entity.EntitySaintBernard;
import com.github.copiousdogs.entity.EntityYorkshire;
import com.github.copiousdogs.lib.Reference;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {

	public static boolean INDIVIDUAL_TRAITS;
	public static int DOG_SPAWN_PROB;
	public static int DOG_ID0;
	public static int DOG_ID1;
	public static int DOG_ID2;
	public static int DOG_ID3;
	public static int DOG_ID4;
	public static int DOG_ID5;
	public static int DOG_ID6;
	public static int DOG_ID7;
	public static int DOG_ID8;
	public static int DOG_ID9;
	public static int DOG_ID10;
	public static int DOG_ID11;
	public static int DOG_ID12;
	public static int DOG_ID13;
	public static int DOG_ID14;
	public static int DOG_ID15;
	public static int DOG_ID16;
	public static int DOG_ID17;
	public static int DOG_ID18;
	public static int DOG_ID19;
	public static int DOG_ID20;
	public static int DOG_ID21;
	public static int DOG_ID22;
	public static int DOG_ID23;
	public static int DOG_ID24;
	public static int DOG_ID25;
	public static int DOG_ID26;
	public static int DOG_ID27;
	public static int DOG_ID28;
	public static int DOG_ID29;
	public static int DOG_ID30;
	public static int DOG_ID207;
	public static int DOG_ID208;
	public static int DOG_ID209;
	
	public static Configuration configuration;
	
	public static void init(File configFile)
	{
		
		if (configuration == null) {
			
			configuration = new Configuration(configFile);
		}
		
		load();
	}
	
	public static void load() {

			
		INDIVIDUAL_TRAITS = configuration.getBoolean("randomized traits", Configuration.CATEGORY_GENERAL, true, "Tells if the dogs should have randomized individual traits or not");
		DOG_SPAWN_PROB = configuration.getInt("dog spawn probability", Configuration.CATEGORY_GENERAL, 10, 0, 100, "The weighted probability value for dog spawning. Higher value means more frequent spawning. Set to 0 to disable spawning.");
		DOG_ID0 = configuration.getInt("EntityBeagle.class", Configuration.CATEGORY_GENERAL, 0, 0, 50, "Entity ID's (ONLY CHANGE IF YOU KNOW WHAT YOUR DOING! DELETE THE CONFIG IF YOU GET ANY ERRORS AS OF RESULT!.");
		DOG_ID1 = configuration.getInt("EntityBerneseMountain.class", Configuration.CATEGORY_GENERAL, 1, 0, 50, "Entity ID's");
		DOG_ID2 = configuration.getInt("EntityGoldenRetriever.class", Configuration.CATEGORY_GENERAL, 2, 0, 50, "Entity ID's");
		DOG_ID3 = configuration.getInt("EntityHusky.class", Configuration.CATEGORY_GENERAL, 3, 0, 50, "Entity ID's");
		DOG_ID4 = configuration.getInt("EntityChihuahua.class", Configuration.CATEGORY_GENERAL, 4, 0, 50, "Entity ID's");
		DOG_ID5 = configuration.getInt("EntityFrenchBulldog.class", Configuration.CATEGORY_GENERAL, 5, 0, 50, "Entity ID's");
		DOG_ID6 = configuration.getInt("EntityGermanShepherd.class", Configuration.CATEGORY_GENERAL, 6, 0, 50, "Entity ID's");
		DOG_ID7 = configuration.getInt("EntityDalmatian.class", Configuration.CATEGORY_GENERAL, 7, 0, 50, "Entity ID's");
		DOG_ID8 = configuration.getInt("EntityGreatDane.class", Configuration.CATEGORY_GENERAL, 8, 0, 50, "Entity ID's");
		DOG_ID9 = configuration.getInt("EntityBoxer.class", Configuration.CATEGORY_GENERAL, 9, 0, 50, "Entity ID's");
		DOG_ID10 = configuration.getInt("EntityCardiganCorgi.class", Configuration.CATEGORY_GENERAL, 10, 0, 50, "Entity ID's");
		DOG_ID11 = configuration.getInt("EntityCollie.class", Configuration.CATEGORY_GENERAL, 11, 0, 50, "Entity ID's");
		DOG_ID12 = configuration.getInt("EntityDoberman.class", Configuration.CATEGORY_GENERAL, 12, 0, 50, "Entity ID's");
		DOG_ID13 = configuration.getInt("EntityPomeranian.class", Configuration.CATEGORY_GENERAL, 13, 0, 50, "Entity ID's");
		DOG_ID14 = configuration.getInt("EntityPoodle.class", Configuration.CATEGORY_GENERAL, 14, 0, 50, "Entity ID's");
		DOG_ID15 = configuration.getInt("EntityPug.class", Configuration.CATEGORY_GENERAL, 15, 0, 50, "Entity ID's");
		DOG_ID16 = configuration.getInt("EntitySaintBernard.class", Configuration.CATEGORY_GENERAL, 16, 0, 50, "Entity ID's");
		DOG_ID17 = configuration.getInt("EntityYorkshire.class", Configuration.CATEGORY_GENERAL, 17, 0, 50, "Entity ID's");
		DOG_ID18 = configuration.getInt("EntityAmericanBulldog.class", Configuration.CATEGORY_GENERAL, 18, 0, 50, "Entity ID's");
		DOG_ID19 = configuration.getInt("EntityEskimoSpitz.class", Configuration.CATEGORY_GENERAL, 19, 0, 50, "Entity ID's");
		DOG_ID20 = configuration.getInt("EntityAustralianShepherd.class", Configuration.CATEGORY_GENERAL, 20, 0, 50, "Entity ID's");
		DOG_ID21 = configuration.getInt("EntityBloodhound.class", Configuration.CATEGORY_GENERAL, 21, 0, 50, "Entity ID's");
		DOG_ID22 = configuration.getInt("EntityDachshund.class", Configuration.CATEGORY_GENERAL, 22, 0, 50, "Entity ID's");
		DOG_ID23 = configuration.getInt("EntityNewfoundland.class", Configuration.CATEGORY_GENERAL, 23, 0, 50, "Entity ID's");
		DOG_ID24 = configuration.getInt("EntityPapillon.class", Configuration.CATEGORY_GENERAL, 24, 0, 50, "Entity ID's");
		DOG_ID25 = configuration.getInt("EntityPembroke.class", Configuration.CATEGORY_GENERAL, 25, 0, 50, "Entity ID's");
		DOG_ID26 = configuration.getInt("test1", Configuration.CATEGORY_GENERAL, 26, 0, 50, "Entity ID's");
		if (configuration.hasChanged()) {
				
			configuration.save();
		}
	}
	
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent event) {
		
		if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
			
			load();
		}
	}
}
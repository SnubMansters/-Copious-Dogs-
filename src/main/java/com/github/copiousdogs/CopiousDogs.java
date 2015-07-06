package com.github.copiousdogs;

import java.util.ArrayList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

import com.github.copiousdogs.client.model.entity.ModelDog;
import com.github.copiousdogs.content.CopiousDogsBlocks;
import com.github.copiousdogs.content.CopiousDogsItems;
import com.github.copiousdogs.content.CopiousDogsTileEntities;
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
import com.github.copiousdogs.entity.EntityDunno;
import com.github.copiousdogs.entity.EntityEskimoSpitz;
import com.github.copiousdogs.entity.EntityFrenchBulldog;
import com.github.copiousdogs.entity.EntityGermanShepherd;
import com.github.copiousdogs.entity.EntityGoldenRetriever;
import com.github.copiousdogs.entity.EntityGreatDane;
import com.github.copiousdogs.entity.EntityHusky;
import com.github.copiousdogs.entity.EntityMolly;
import com.github.copiousdogs.entity.EntityNewfoundland;
import com.github.copiousdogs.entity.EntityPapillon;
import com.github.copiousdogs.entity.EntityPembroke;
import com.github.copiousdogs.entity.EntityPitbull;
import com.github.copiousdogs.entity.EntityPolly;
import com.github.copiousdogs.entity.EntityPomeranian;
import com.github.copiousdogs.entity.EntityPoodle;
import com.github.copiousdogs.entity.EntityPug;
import com.github.copiousdogs.entity.EntitySaintBernard;
import com.github.copiousdogs.entity.EntityYorkshire;
import com.github.copiousdogs.handler.ConfigurationHandler;
import com.github.copiousdogs.handler.DogDishHandler;
import com.github.copiousdogs.lib.Reference;
import com.github.copiousdogs.lib.SpawnMap;
import com.github.copiousdogs.network.MessageDogDish;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class CopiousDogs 
{

	@Instance("CopiousDogs")
	public static CopiousDogs instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS,
			serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static CreativeTabs tabCopiousDogs = new CreativeTabs("tabCopiousDogs")
	{
		@Override
		public Item getTabIconItem() 
		{
			return CopiousDogsItems.dogBiscuit;
		}
	};
	
	public static SimpleNetworkWrapper snw;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		
		snw = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.CHANNEL_NAME);
		
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) 
		{
			snw.registerMessage(DogDishHandler.class, MessageDogDish.class, 0, Side.CLIENT);
		}
		
		CopiousDogsItems.init();
		CopiousDogsBlocks.init();
		CopiousDogsTileEntities.init();
		
		ModelDog.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{	
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			proxy.registerRenderers();
			registerEntitiesClient();
		}
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
		{
			registerEntitiesServer();
		}
		proxy.registerRecipes();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		BiomeDictionary.registerAllBiomes();
		
		for (Type type : Type.values())
		{
			BiomeGenBase[] biomes = BiomeDictionary.getBiomesForType(type);
			ArrayList<Class<? extends EntityLiving>> classes = SpawnMap.getClassesFromType(type);
			
			if (classes != null) 
			{
				for (BiomeGenBase biome : biomes)
				{
					for (Class<? extends EntityLiving> clazz : classes)
					{
						EntityRegistry.addSpawn(clazz, ConfigurationHandler.DOG_SPAWN_PROB, 2, 6, EnumCreatureType.creature, biome);
					}
				}
			}
		}
	}
	
	private void registerEntitiesClient()
	{
		
		SpawnMap.registerSpawnBiomes(EntityGermanShepherd.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(EntityChihuahua.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(EntityFrenchBulldog.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(EntityGoldenRetriever.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(EntityBoxer.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(EntityYorkshire.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(EntityAustralianShepherd.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(EntityDachshund.class, Type.PLAINS, Type.DRY, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(EntityPapillon.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(EntityPitbull.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(EntityCollie.class, Type.PLAINS, Type.FOREST, Type.HILLS, Type.MOUNTAIN);
		SpawnMap.registerSpawnBiomes(EntityPoodle.class, Type.PLAINS, Type.FOREST, Type.HILLS, Type.LUSH);
		SpawnMap.registerSpawnBiomes(EntityBeagle.class, Type.FOREST, Type.LUSH);
		SpawnMap.registerSpawnBiomes(EntityDalmatian.class, Type.FOREST, Type.LUSH);
		SpawnMap.registerSpawnBiomes(EntityDoberman.class, Type.FOREST, Type.LUSH);
		SpawnMap.registerSpawnBiomes(EntityPomeranian.class, Type.FOREST, Type.LUSH);
		SpawnMap.registerSpawnBiomes(EntityPug.class, Type.FOREST, Type.LUSH);
		SpawnMap.registerSpawnBiomes(EntityAmericanBulldog.class, Type.FOREST, Type.LUSH);
		SpawnMap.registerSpawnBiomes(EntityBloodhound.class, Type.FOREST, Type.LUSH);
		SpawnMap.registerSpawnBiomes(EntityNewfoundland.class, Type.FOREST, Type.LUSH);
		SpawnMap.registerSpawnBiomes(EntityBerneseMountain.class, Type.HILLS, Type.MOUNTAIN);
		SpawnMap.registerSpawnBiomes(EntityGreatDane.class, Type.HILLS, Type.MOUNTAIN);
		SpawnMap.registerSpawnBiomes(EntityCardiganCorgi.class, Type.HILLS, Type.MOUNTAIN);
		SpawnMap.registerSpawnBiomes(EntitySaintBernard.class, Type.HILLS, Type.MOUNTAIN, Type.COLD);
		SpawnMap.registerSpawnBiomes(EntityPembroke.class, Type.HILLS, Type.MOUNTAIN);
		SpawnMap.registerSpawnBiomes(EntityHusky.class, Type.COLD);
		SpawnMap.registerSpawnBiomes(EntityEskimoSpitz.class, Type.COLD);
		
		
		EntityRegistry.registerModEntity(EntityBeagle.class, "beagle", ConfigurationHandler.DOG_ID0, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID0, EntityBeagle.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID0, new EntityEggInfo(ConfigurationHandler.DOG_ID0, 0xCE935F, 0x685043));
		
		EntityRegistry.registerModEntity(EntityBerneseMountain.class, "bernese_mountain", ConfigurationHandler.DOG_ID1, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID1, EntityBerneseMountain.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID1, new EntityEggInfo(ConfigurationHandler.DOG_ID1, 0x0B0C12, 0x723510));
		
		EntityRegistry.registerModEntity(EntityGoldenRetriever.class, "golden_retriever", ConfigurationHandler.DOG_ID2, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID2, EntityGoldenRetriever.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID2, new EntityEggInfo(ConfigurationHandler.DOG_ID2, 0xBC8E5F, 0xDDCDB6));
		
		EntityRegistry.registerModEntity(EntityHusky.class, "husky", ConfigurationHandler.DOG_ID3, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID3, EntityHusky.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID3, new EntityEggInfo(ConfigurationHandler.DOG_ID3, 0x2B2E2D, 0x7E807D));
		
		EntityRegistry.registerModEntity(EntityChihuahua.class, "chihuahua", ConfigurationHandler.DOG_ID4, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID4, EntityChihuahua.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID4, new EntityEggInfo(ConfigurationHandler.DOG_ID4, 0xC7A087, 0x9E7F6B));
		
		EntityRegistry.registerModEntity(EntityFrenchBulldog.class, "french_bulldog", ConfigurationHandler.DOG_ID5, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID5, EntityFrenchBulldog.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID5, new EntityEggInfo(ConfigurationHandler.DOG_ID5, 0x151618, 0xBDBDB7));
		
		EntityRegistry.registerModEntity(EntityGermanShepherd.class, "german_shepherd", ConfigurationHandler.DOG_ID6, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID6, EntityGermanShepherd.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID6, new EntityEggInfo(ConfigurationHandler.DOG_ID6, 0xAD754F, 0x17141B));
		
		EntityRegistry.registerModEntity(EntityDalmatian.class, "dalmatian", ConfigurationHandler.DOG_ID7, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID7, EntityDalmatian.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID7, new EntityEggInfo(ConfigurationHandler.DOG_ID7, 0xFFFFFF, 0x000000));
		
		EntityRegistry.registerModEntity(EntityGreatDane.class, "great_dane", ConfigurationHandler.DOG_ID8, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID8, EntityGreatDane.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID8, new EntityEggInfo(ConfigurationHandler.DOG_ID8, 0xDFB188, 0xC79B69));
		
		EntityRegistry.registerModEntity(EntityBoxer.class, "boxer", ConfigurationHandler.DOG_ID9, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID9, EntityBoxer.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID9, new EntityEggInfo(ConfigurationHandler.DOG_ID9, 0x866331, 0xB7B1A8));
		
		EntityRegistry.registerModEntity(EntityCardiganCorgi.class, "cardigan_corgi", ConfigurationHandler.DOG_ID10, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID10, EntityCardiganCorgi.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID10, new EntityEggInfo(ConfigurationHandler.DOG_ID10, 0xA46F43, 0x827A72));
		
		EntityRegistry.registerModEntity(EntityCollie.class, "collie", ConfigurationHandler.DOG_ID11, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID11, EntityCollie.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID11, new EntityEggInfo(ConfigurationHandler.DOG_ID11, 0x9F653D, 0xDAD9DB));
		
		EntityRegistry.registerModEntity(EntityDoberman.class, "doberman", ConfigurationHandler.DOG_ID12, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID12, EntityDoberman.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID12, new EntityEggInfo(ConfigurationHandler.DOG_ID12, 0x1C1B1B, 0x7D462D));
		
		EntityRegistry.registerModEntity(EntityPomeranian.class, "pomeranian", ConfigurationHandler.DOG_ID13, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID13, EntityPomeranian.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID13, new EntityEggInfo(ConfigurationHandler.DOG_ID13, 0x9C531B, 0xC0854A));
		
		EntityRegistry.registerModEntity(EntityPoodle.class, "poodle", ConfigurationHandler.DOG_ID14, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID14, EntityPoodle.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID14, new EntityEggInfo(ConfigurationHandler.DOG_ID14, 0xC2C7D4, 0xD7DADF));
		
		EntityRegistry.registerModEntity(EntityPug.class, "pug", ConfigurationHandler.DOG_ID15, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID15, EntityPug.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID15, new EntityEggInfo(ConfigurationHandler.DOG_ID15, 0xCDA27F, 0xD2B094));
		
		EntityRegistry.registerModEntity(EntitySaintBernard.class, "saint_bernard", ConfigurationHandler.DOG_ID16, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID16, EntitySaintBernard.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID16, new EntityEggInfo(ConfigurationHandler.DOG_ID16, 0x793F1F, 0xC6BEAA));
		
		EntityRegistry.registerModEntity(EntityYorkshire.class, "yorkshire", ConfigurationHandler.DOG_ID17, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID17, EntityYorkshire.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID17, new EntityEggInfo(ConfigurationHandler.DOG_ID17, 0x1F1E1D, 0x805234));
		
		EntityRegistry.registerModEntity(EntityAmericanBulldog.class, "american_bulldog", ConfigurationHandler.DOG_ID18, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID18, EntityAmericanBulldog.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID18, new EntityEggInfo(ConfigurationHandler.DOG_ID18, 0xC6C0BE, 0x8A5D40));
		
		EntityRegistry.registerModEntity(EntityEskimoSpitz.class, "eskimo_spitz", ConfigurationHandler.DOG_ID19, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID19, EntityEskimoSpitz.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID19, new EntityEggInfo(ConfigurationHandler.DOG_ID19, 0xE9E2E0, 0xD5D0D3));
		
		EntityRegistry.registerModEntity(EntityAustralianShepherd.class, "australian_shepherd", ConfigurationHandler.DOG_ID20, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID20, EntityAustralianShepherd.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID20, new EntityEggInfo(ConfigurationHandler.DOG_ID20, 0xA9AEAE, 0x1E272C));
		
		EntityRegistry.registerModEntity(EntityBloodhound.class, "bloodhound", ConfigurationHandler.DOG_ID21, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID21, EntityBloodhound.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID21, new EntityEggInfo(ConfigurationHandler.DOG_ID21, 0xB97530, 0x1E1816));
		
		EntityRegistry.registerModEntity(EntityDachshund.class, "dachshund", ConfigurationHandler.DOG_ID22, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID22, EntityDachshund.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID22, new EntityEggInfo(ConfigurationHandler.DOG_ID22, 0x1C202E, 0x8F5E40));
		
		EntityRegistry.registerModEntity(EntityNewfoundland.class, "newfoundland", ConfigurationHandler.DOG_ID23, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID23, EntityNewfoundland.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID23, new EntityEggInfo(ConfigurationHandler.DOG_ID23, 0x161E18, 0x383E3C));
		
		EntityRegistry.registerModEntity(EntityPapillon.class, "papillon", ConfigurationHandler.DOG_ID24, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID24, EntityPapillon.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID24, new EntityEggInfo(ConfigurationHandler.DOG_ID24, 0xDDDEDC, 0x8B5832));
		
		EntityRegistry.registerModEntity(EntityPembroke.class, "pembroke", ConfigurationHandler.DOG_ID25, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID25, EntityPembroke.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID25, new EntityEggInfo(ConfigurationHandler.DOG_ID25, 0xA56E40, 0xDCC8B2));
		
		EntityRegistry.registerModEntity(EntityPembroke.class, "pitbull", (ConfigurationHandler.DOG_ID26), this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID26, EntityPitbull.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID26, new EntityEggInfo(ConfigurationHandler.DOG_ID26, 0x615655, 0x897C7C));
	}
	private void registerEntitiesServer()
	{
		
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityGermanShepherd.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityChihuahua.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityFrenchBulldog.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityGoldenRetriever.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityBoxer.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityYorkshire.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityAustralianShepherd.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityDachshund.class, Type.PLAINS, Type.DRY, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityPapillon.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityPitbull.class, Type.PLAINS, Type.SPARSE);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityCollie.class, Type.PLAINS, Type.FOREST, Type.HILLS, Type.MOUNTAIN);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityPoodle.class, Type.PLAINS, Type.FOREST, Type.HILLS, Type.LUSH);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityBeagle.class, Type.FOREST, Type.LUSH);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityDalmatian.class, Type.FOREST, Type.LUSH);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityDoberman.class, Type.FOREST, Type.LUSH);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityPomeranian.class, Type.FOREST, Type.LUSH);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityPug.class, Type.FOREST, Type.LUSH);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityAmericanBulldog.class, Type.FOREST, Type.LUSH);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityBloodhound.class, Type.FOREST, Type.LUSH);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityNewfoundland.class, Type.FOREST, Type.LUSH);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityBerneseMountain.class, Type.HILLS, Type.MOUNTAIN);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityGreatDane.class, Type.HILLS, Type.MOUNTAIN);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityCardiganCorgi.class, Type.HILLS, Type.MOUNTAIN);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntitySaintBernard.class, Type.HILLS, Type.MOUNTAIN, Type.COLD);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityPembroke.class, Type.HILLS, Type.MOUNTAIN);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityHusky.class, Type.COLD);
		SpawnMap.registerSpawnBiomes(com.github.copiousdogs.server.entity.EntityEskimoSpitz.class, Type.COLD);
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityBeagle.class, "beagle", ConfigurationHandler.DOG_ID0, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID0, com.github.copiousdogs.server.entity.EntityBeagle.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID0, new EntityEggInfo(ConfigurationHandler.DOG_ID0, 0xCE935F, 0x685043));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityBerneseMountain.class, "bernese_mountain", ConfigurationHandler.DOG_ID1, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID1, com.github.copiousdogs.server.entity.EntityBerneseMountain.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID1, new EntityEggInfo(ConfigurationHandler.DOG_ID1, 0x0B0C12, 0x723510));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityGoldenRetriever.class, "golden_retriever", ConfigurationHandler.DOG_ID2, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID2, com.github.copiousdogs.server.entity.EntityGoldenRetriever.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID2, new EntityEggInfo(ConfigurationHandler.DOG_ID2, 0xBC8E5F, 0xDDCDB6));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityHusky.class, "husky", ConfigurationHandler.DOG_ID3, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID3, com.github.copiousdogs.server.entity.EntityHusky.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID3, new EntityEggInfo(ConfigurationHandler.DOG_ID3, 0x2B2E2D, 0x7E807D));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityChihuahua.class, "chihuahua", ConfigurationHandler.DOG_ID4, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID4, com.github.copiousdogs.server.entity.EntityChihuahua.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID4, new EntityEggInfo(ConfigurationHandler.DOG_ID4, 0xC7A087, 0x9E7F6B));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityFrenchBulldog.class, "french_bulldog", ConfigurationHandler.DOG_ID5, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID5, com.github.copiousdogs.server.entity.EntityFrenchBulldog.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID5, new EntityEggInfo(ConfigurationHandler.DOG_ID5, 0x151618, 0xBDBDB7));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityGermanShepherd.class, "german_shepherd", ConfigurationHandler.DOG_ID6, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID6, com.github.copiousdogs.server.entity.EntityGermanShepherd.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID6, new EntityEggInfo(ConfigurationHandler.DOG_ID6, 0xAD754F, 0x17141B));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityDalmatian.class, "dalmatian", ConfigurationHandler.DOG_ID7, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID7, com.github.copiousdogs.server.entity.EntityDalmatian.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID7, new EntityEggInfo(ConfigurationHandler.DOG_ID7, 0xFFFFFF, 0x000000));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityGreatDane.class, "great_dane", ConfigurationHandler.DOG_ID8, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID8, com.github.copiousdogs.server.entity.EntityGreatDane.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID8, new EntityEggInfo(ConfigurationHandler.DOG_ID8, 0xDFB188, 0xC79B69));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityBoxer.class, "boxer", ConfigurationHandler.DOG_ID9, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID9, com.github.copiousdogs.server.entity.EntityBoxer.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID9, new EntityEggInfo(ConfigurationHandler.DOG_ID9, 0x866331, 0xB7B1A8));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityCardiganCorgi.class, "cardigan_corgi", ConfigurationHandler.DOG_ID10, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID10, com.github.copiousdogs.server.entity.EntityCardiganCorgi.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID10, new EntityEggInfo(ConfigurationHandler.DOG_ID10, 0xA46F43, 0x827A72));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityCollie.class, "collie", ConfigurationHandler.DOG_ID11, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID11, com.github.copiousdogs.server.entity.EntityCollie.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID11, new EntityEggInfo(ConfigurationHandler.DOG_ID11, 0x9F653D, 0xDAD9DB));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityDoberman.class, "doberman", ConfigurationHandler.DOG_ID12, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID12, com.github.copiousdogs.server.entity.EntityDoberman.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID12, new EntityEggInfo(ConfigurationHandler.DOG_ID12, 0x1C1B1B, 0x7D462D));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityPomeranian.class, "pomeranian", ConfigurationHandler.DOG_ID13, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID13, com.github.copiousdogs.server.entity.EntityPomeranian.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID13, new EntityEggInfo(ConfigurationHandler.DOG_ID13, 0x9C531B, 0xC0854A));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityPoodle.class, "poodle", ConfigurationHandler.DOG_ID14, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID14, com.github.copiousdogs.server.entity.EntityPoodle.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID14, new EntityEggInfo(ConfigurationHandler.DOG_ID14, 0xC2C7D4, 0xD7DADF));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityPug.class, "pug", ConfigurationHandler.DOG_ID15, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID15, com.github.copiousdogs.server.entity.EntityPug.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID15, new EntityEggInfo(ConfigurationHandler.DOG_ID15, 0xCDA27F, 0xD2B094));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntitySaintBernard.class, "saint_bernard", ConfigurationHandler.DOG_ID16, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID16, com.github.copiousdogs.server.entity.EntitySaintBernard.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID16, new EntityEggInfo(ConfigurationHandler.DOG_ID16, 0x793F1F, 0xC6BEAA));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityYorkshire.class, "yorkshire", ConfigurationHandler.DOG_ID17, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(17, com.github.copiousdogs.server.entity.EntityYorkshire.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID17, new EntityEggInfo(ConfigurationHandler.DOG_ID17, 0x1F1E1D, 0x805234));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityAmericanBulldog.class, "american_bulldog", ConfigurationHandler.DOG_ID18, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID18, com.github.copiousdogs.server.entity.EntityAmericanBulldog.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID18, new EntityEggInfo(ConfigurationHandler.DOG_ID18, 0xC6C0BE, 0x8A5D40));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityEskimoSpitz.class, "eskimo_spitz", ConfigurationHandler.DOG_ID19, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID19, com.github.copiousdogs.server.entity.EntityEskimoSpitz.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID19, new EntityEggInfo(ConfigurationHandler.DOG_ID19, 0xE9E2E0, 0xD5D0D3));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityAustralianShepherd.class, "australian_shepherd", ConfigurationHandler.DOG_ID20, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID20, com.github.copiousdogs.server.entity.EntityAustralianShepherd.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID20, new EntityEggInfo(ConfigurationHandler.DOG_ID20, 0xA9AEAE, 0x1E272C));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityBloodhound.class, "bloodhound", ConfigurationHandler.DOG_ID21, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(21, com.github.copiousdogs.server.entity.EntityBloodhound.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID21, new EntityEggInfo(ConfigurationHandler.DOG_ID21, 0xB97530, 0x1E1816));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityDachshund.class, "dachshund", ConfigurationHandler.DOG_ID22, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID22, com.github.copiousdogs.server.entity.EntityDachshund.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID22, new EntityEggInfo(ConfigurationHandler.DOG_ID22, 0x1C202E, 0x8F5E40));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityNewfoundland.class, "newfoundland", ConfigurationHandler.DOG_ID23, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID23, com.github.copiousdogs.server.entity.EntityNewfoundland.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID23, new EntityEggInfo(ConfigurationHandler.DOG_ID23, 0x161E18, 0x383E3C));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityPapillon.class, "papillon", ConfigurationHandler.DOG_ID24, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID24, com.github.copiousdogs.server.entity.EntityPapillon.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID24, new EntityEggInfo(ConfigurationHandler.DOG_ID24, 0xDDDEDC, 0x8B5832));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityPembroke.class, "pembroke", ConfigurationHandler.DOG_ID25, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID25, com.github.copiousdogs.server.entity.EntityPembroke.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID25, new EntityEggInfo(ConfigurationHandler.DOG_ID25, 0xA56E40, 0xDCC8B2));
		
		EntityRegistry.registerModEntity(com.github.copiousdogs.server.entity.EntityPitbull.class, "pitbull", ConfigurationHandler.DOG_ID26, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(ConfigurationHandler.DOG_ID26, com.github.copiousdogs.server.entity.EntityPitbull.class);
		EntityList.entityEggs.put(ConfigurationHandler.DOG_ID26, new EntityEggInfo(ConfigurationHandler.DOG_ID26, 0x615655, 0x897C7C));
		
	}
}

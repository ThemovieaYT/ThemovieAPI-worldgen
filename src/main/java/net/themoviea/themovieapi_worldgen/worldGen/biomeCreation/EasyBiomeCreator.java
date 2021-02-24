package net.themoviea.themovieapi_worldgen.worldGen.biomeCreation;

import java.util.ArrayList;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;

import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;

/**
 * The purpose of this class is to make biome creation
 * much easier, by writing less code (duh)
 * @author Themoviea
 */
public class EasyBiomeCreator {
	/**
	 * A simple but powerful method for creating biomes.
	 * 
	 * Create a MyBiomeCreator class, then create a method called createCustomBiome(),
	 * then add this method and put the settings for the biome.
	 * Refer to {@link fabricmc.net/wiki}'s biome tutorial. If you don't get why it doesn't
	 * have similar code from your mod, click the source of this API on your IDE, and find
	 * the EasyBiomeCreator, and find this method, then compare the code with the wiki.
	 * @param structure
	 * @param biomeCategory
	 * @param addDefaultGenSettings
	 * @param depth
	 * @param scale
	 * @param temperature
	 * @param downfall
	 * @param skyColor
	 * @param waterColor
	 * @param waterFogColor
	 * @param fogColor
	 * @param foliageColor
	 * @param grassColor
	 * @return Biome
	 */
	public static Biome createDefaultModBiome(ArrayList<ConfiguredStructureFeature<?, ?>> structure, Category biomeCategory, boolean addDefaultGenSettings, float depth, float scale, float temperature, float downfall, int skyColor, int waterColor, int waterFogColor, int fogColor, int foliageColor, int grassColor) {
		SpawnSettings.Builder spawnSettingsBuilder = new SpawnSettings.Builder();
        spawnSettingsBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 12, 4, 4));
        spawnSettingsBuilder.playerSpawnFriendly();

        GenerationSettings.Builder generationSettingsBuilder = (new GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
       
        if(structure.size() == 0) {
        	System.out.println("There are no structures!");
        } else {
        	for(int x = 0; x < structure.size(); x++) {
        		generationSettingsBuilder.structureFeature(structure.get(x));
        	}
        }
        
        if(addDefaultGenSettings != false) {
        	DefaultBiomeFeatures.addLandCarvers(generationSettingsBuilder);
        	DefaultBiomeFeatures.addDungeons(generationSettingsBuilder);
        	DefaultBiomeFeatures.addPlainsTallGrass(generationSettingsBuilder);
        	DefaultBiomeFeatures.addDefaultOres(generationSettingsBuilder);
        	DefaultBiomeFeatures.addPlainsFeatures(generationSettingsBuilder);
        	DefaultBiomeFeatures.addDefaultMushrooms(generationSettingsBuilder);
        	DefaultBiomeFeatures.addSprings(generationSettingsBuilder);
        	DefaultBiomeFeatures.addFrozenTopLayer(generationSettingsBuilder);
        }
        
        BiomeEffects.Builder biomeEffectsBuilder = new BiomeEffects.Builder();
        biomeEffectsBuilder.skyColor(skyColor);
        biomeEffectsBuilder.waterColor(waterColor);
        biomeEffectsBuilder.waterFogColor(waterFogColor);
        biomeEffectsBuilder.fogColor(fogColor);
        biomeEffectsBuilder.foliageColor(foliageColor);
        biomeEffectsBuilder.grassColor(grassColor);

        Biome.Builder biomeBuilder = new Biome.Builder();
        biomeBuilder.precipitation(Biome.Precipitation.RAIN);
        biomeBuilder.depth(depth);
        biomeBuilder.scale(scale);
        biomeBuilder.temperature(temperature);
        biomeBuilder.downfall(downfall);
        biomeBuilder.category(biomeCategory);
        biomeBuilder.effects(biomeEffectsBuilder.build());
        biomeBuilder.generationSettings(generationSettingsBuilder.build());
        biomeBuilder.spawnSettings(spawnSettingsBuilder.build());

        return biomeBuilder.build();
	}
}

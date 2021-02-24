package net.themoviea.themovieapi_worldgen.worldGen.features;

import java.util.ArrayList;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

/**
 * A class for easily building structures into the world.
 * @author Themoviea
 */
@SuppressWarnings("deprecation")
public class EasyFeatureBuild {
	/**
	 * This is a method for building a modded structure into your world. Simply write
	 * your mod id, structure name, and your {@codepublic static final StructureFeature<AFeatureConfigHere> FEATURE}.
	 * Then put how the structure will generate in the world. Finally, put your configured structure in the method.
	 * @param modid
	 * @param name
	 * @param feature
	 * @param generationStep
	 * @param spacing
	 * @param separation
	 * @param salt
	 * @param configuredStructure
	 */
	public static ArrayList<String> featureName = new ArrayList<>();
	public static void buildDefaultStructureFeatures(String modid, StructureFeature<DefaultFeatureConfig> feature, GenerationStep.Feature generationStep, int spacing, int separation, int salt, ConfiguredStructureFeature<?, ?> configuredStructure) {
		for(int x = 0; x < featureName.size(); x++) {
			FabricStructureBuilder.create(new Identifier(modid, featureName.get(x)), feature)
				.step(generationStep)
				.defaultConfig(spacing, separation, salt)
				.adjustsSurface()
				.register();
			
			RegistryKey<ConfiguredStructureFeature<?, ?>> configured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN, new Identifier(modid, featureName.get(x)));
			BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, configured.getValue(), configuredStructure);
			BiomeModifications.addStructure(BiomeSelectors.all(), configured);
		}
	}
}

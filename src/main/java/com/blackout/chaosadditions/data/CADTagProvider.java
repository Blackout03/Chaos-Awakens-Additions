package com.blackout.chaosadditions.data;

import com.blackout.chaosadditions.ChaosAdditions;
import com.blackout.chaosadditions.registry.CADItems;
import io.github.chaosawakens.data.provider.CATagProvider;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.nio.file.Path;

public class CADTagProvider extends BlockTagsProvider {
	public CADTagProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, ChaosAdditions.MODID, existingFileHelper);
	}

	public static class CADItemTagProvider extends ItemTagsProvider {
		public CADItemTagProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
			super(gen, new CATagProvider.CABlockTagProvider(gen, existingFileHelper), ChaosAdditions.MODID, existingFileHelper);
		}

		protected Path getPath(ResourceLocation resourceLocation) {
			return this.generator.getOutputFolder().resolve("data/" + resourceLocation.getNamespace() + "/tags/items/" + resourceLocation.getPath() + ".json");
		}

		@Override
		public String getName() {
			return ChaosAdditions.MODNAME + ": Item Tags";
		}

		@Override
		protected void addTags() {
			this.tag(ItemTags.PIGLIN_LOVED).add(CADItems.GOLDEN_AIOT.get());
		}
	}
}

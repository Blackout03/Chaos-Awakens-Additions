package com.blackout.chaosadditions.util;

import com.blackout.chaosadditions.registry.CADItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.ModList;

public class ToolUtil {
	public static ActionResultType hoeUse(ItemUseContext ctx) {
		ItemStack stack = ctx.getItemInHand();
		PlayerEntity player = ctx.getPlayer();
		World world = ctx.getLevel();
		BlockPos pos = ctx.getClickedPos();
		Direction side = ctx.getClickedFace();

		if (stack.getItem() == CADItems.ULTIMATE_AIOT.get() || (ModList.get().isLoaded("purechaos") && stack.getItem() == CADItems.MEGANIUM_AIOT.get())) {
			int hook = ForgeEventFactory.onHoeUse(ctx);
			if (hook != 0) return hook > 0 ? ActionResultType.SUCCESS : ActionResultType.FAIL;
			if (side != Direction.DOWN && world.isEmptyBlock(pos.above())) {
				BlockState blockState = world.getBlockState(pos).getToolModifiedState(world, pos, player, stack, ToolType.HOE);
				if (blockState != null) {
					if (!world.isClientSide && player != null) {
						stack.hurtAndBreak(1, player, (playerEntity) -> playerEntity.broadcastBreakEvent(ctx.getHand()));
					}
					world.playSound(player, pos, SoundEvents.HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
					for (int x = -1; x < 2; x++) {
						for (int y = -1; y < 2; y++) {
							for (int z = -1; z < 2; z++) {
								BlockPos targetPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
								if (world.isEmptyBlock(targetPos.above())) {
									blockState = world.getBlockState(targetPos).getToolModifiedState(world, targetPos, player, stack, ToolType.HOE);
									if (blockState != null) {
										if (!world.isClientSide) {
											world.setBlock(targetPos, blockState, 11);
										}
									}
								}
							}
						}
					}
					return ActionResultType.sidedSuccess(world.isClientSide);
				} else if (world.getBlockState(pos).getBlock() instanceof FarmlandBlock) {
					Block block;
					block = Blocks.GRASS_BLOCK;
					if (block != null) {
						if (!world.isClientSide && player != null) {
							stack.hurtAndBreak(1, player, (playerEntity) -> playerEntity.broadcastBreakEvent(ctx.getHand()));
						}
						for (int x = -1; x < 2; x++) {
							for (int y = -1; y < 2; y++) {
								for (int z = -1; z < 2; z++) {
									BlockPos targetPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
									if (world.isEmptyBlock(targetPos.above())) {
										if (world.getBlockState(targetPos).is(Blocks.FARMLAND)) {
											world.setBlockAndUpdate(targetPos, block.defaultBlockState());
											if (world.getBlockState(targetPos).getBlock() == Blocks.FARMLAND) {
												assert player != null;
												player.setPos(player.getX(), player.getY() + 0.0625, player.getZ());
											}
										}
									}
								}
							}
						}
						return ActionResultType.sidedSuccess(world.isClientSide);
					}
				}
			}
		} else {
			if (player != null && player.mayUseItemAt(pos, side, stack)) {
				if (side != Direction.DOWN && world.isEmptyBlock(pos.above())) {
					BlockState blockstate = world.getBlockState(pos).getToolModifiedState(world, pos, player, stack, ToolType.HOE);
					if (blockstate != null) {
						if (blockstate.getBlock() == Blocks.FARMLAND) {
							blockstate = Blocks.FARMLAND.defaultBlockState();
						}
						world.playSound(player, pos, SoundEvents.HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
						if (!world.isClientSide) {
							world.setBlockAndUpdate(pos, blockstate);
							stack.hurtAndBreak(1, player, (playerEntity) -> playerEntity.broadcastBreakEvent(ctx.getHand()));
						}

						return ActionResultType.sidedSuccess(world.isClientSide);
					} else if (world.getBlockState(pos).getBlock() instanceof FarmlandBlock) {
						Block block;
						block = Blocks.GRASS_BLOCK;
						if (block != null) {
							world.setBlockAndUpdate(pos, block.defaultBlockState());
							if (world.getBlockState(pos).getBlock() == Blocks.FARMLAND) {
								player.setPos(player.getX(), player.getY() + 0.0625, player.getZ());
							}
							return ActionResultType.sidedSuccess(world.isClientSide);
						}
					}
				}
			}
		}
		return ActionResultType.PASS;
	}

	public static ActionResultType shovelUse(ItemUseContext ctx) {
		ItemStack stack = ctx.getItemInHand();
		World world = ctx.getLevel();
		BlockPos eventPos = ctx.getClickedPos();
		BlockState blockstate = world.getBlockState(eventPos);
		PlayerEntity playerentity = ctx.getPlayer();
		if (ctx.getClickedFace() == Direction.DOWN) {
			return ActionResultType.PASS;
		} else {
			if (stack.getItem() == CADItems.ULTIMATE_AIOT.get() || (ModList.get().isLoaded("purechaos") && stack.getItem() == CADItems.MEGANIUM_AIOT.get())) {
				if (!world.isClientSide && playerentity != null) {
					ctx.getItemInHand().hurtAndBreak(1, playerentity, (p_220041_1_) -> p_220041_1_.broadcastBreakEvent(ctx.getHand()));
				}
				for (int x = -1; x < 2; x++) {
					for (int y = -1; y < 2; y++) {
						for (int z = -1; z < 2; z++) {
							BlockPos targetPos = new BlockPos(eventPos.getX() + x, eventPos.getY() + y, eventPos.getZ() + z);
							BlockState blockstate1 = blockstate.getToolModifiedState(world, targetPos, playerentity, ctx.getItemInHand(), net.minecraftforge.common.ToolType.SHOVEL);
							BlockState blockstate2 = null;
							if (world.getBlockState(targetPos).is(blockstate.getBlock())) {
								if (blockstate1 != null) {
									world.playSound(playerentity, targetPos, SoundEvents.SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
									blockstate2 = blockstate1;
								} else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.getValue(CampfireBlock.LIT)) {
									if (!world.isClientSide()) {
										world.levelEvent(null, 1009, targetPos, 0);
									}

									CampfireBlock.dowse(world, targetPos, blockstate);
									blockstate2 = blockstate.setValue(CampfireBlock.LIT, Boolean.FALSE);
								}

								if (world.isEmptyBlock(targetPos.above())) {
									if (blockstate2 != null) {
										if (!world.isClientSide) {
											world.setBlock(targetPos, blockstate2, 11);
										}
									}
								}
							}
						}
					}
				}
				return ActionResultType.sidedSuccess(world.isClientSide);
			} else {
				BlockState modifiedState = blockstate.getToolModifiedState(world, eventPos, playerentity, ctx.getItemInHand(), ToolType.SHOVEL);
				if (modifiedState != null && world.isEmptyBlock(eventPos.above())) {
					world.playSound(playerentity, eventPos, SoundEvents.SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
				} else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.getValue(CampfireBlock.LIT)) {
					if (!world.isClientSide()) {
						world.levelEvent(null, 1009, eventPos, 0);
					}

					CampfireBlock.dowse(world, eventPos, blockstate);
					modifiedState = blockstate.setValue(CampfireBlock.LIT, false);
				}

				if (modifiedState != null) {
					if (!world.isClientSide) {
						world.setBlockAndUpdate(eventPos, modifiedState);
						if (playerentity != null) {
							ctx.getItemInHand().hurtAndBreak(1, playerentity, (entity) -> entity.broadcastBreakEvent(ctx.getHand()));
						}
					}
					return ActionResultType.sidedSuccess(world.isClientSide);
				}
			}
		}
		return ActionResultType.PASS;
	}

	public static ActionResultType axeUse(ItemUseContext ctx) {
		ItemStack stack = ctx.getItemInHand();
		World world = ctx.getLevel();
		BlockPos blockpos = ctx.getClickedPos();
		PlayerEntity playerentity = ctx.getPlayer();
		BlockState blockstate = world.getBlockState(blockpos);
		if (ctx.getClickedFace() == Direction.DOWN) {
			return ActionResultType.PASS;
		} else {
			if (stack.getItem() == CADItems.ULTIMATE_AIOT.get() || (ModList.get().isLoaded("purechaos") && stack.getItem() == CADItems.MEGANIUM_AIOT.get())) {
				if (!world.isClientSide && playerentity != null) {
					ctx.getItemInHand().hurtAndBreak(1, playerentity, (p_220040_1_) -> p_220040_1_.broadcastBreakEvent(ctx.getHand()));
				}
				for (int x = -1; x < 2; x++) {
					for (int y = -1; y < 2; y++) {
						for (int z = -1; z < 2; z++) {
							BlockPos targetPos = new BlockPos(blockpos.getX() + x, blockpos.getY() + y, blockpos.getZ() + z);
							BlockState blockstate1 = world.getBlockState(targetPos).getToolModifiedState(world, targetPos, playerentity, stack, ToolType.AXE);
							BlockState blockstate2 = null;
							if (world.getBlockState(targetPos).is(blockstate.getBlock())) {
								if (blockstate1 != null) {
									world.playSound(playerentity, targetPos, SoundEvents.AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
									blockstate2 = blockstate1;
								}

								if (blockstate2 != null) {
									if (!world.isClientSide) {
										world.setBlock(targetPos, blockstate2, 11);
									}
								}
							}
						}
					}
				}
				return ActionResultType.sidedSuccess(world.isClientSide);
			} else {
				BlockState block = blockstate.getToolModifiedState(world, blockpos, playerentity, stack, ToolType.AXE);
				if (block != null) {
					world.playSound(playerentity, blockpos, SoundEvents.AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
					if (!world.isClientSide) {
						world.setBlockAndUpdate(blockpos, block);
						if (playerentity != null) {
							ctx.getItemInHand().hurtAndBreak(1, playerentity, (player) -> player.broadcastBreakEvent(ctx.getHand()));
						}
					}
					return ActionResultType.sidedSuccess(world.isClientSide);
				}
			}
		}
		return ActionResultType.PASS;
	}
}

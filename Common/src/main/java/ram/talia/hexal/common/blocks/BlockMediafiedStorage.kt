package ram.talia.hexal.common.blocks

import at.petrak.hexcasting.xplat.IForgeLikeBlock
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.EntityBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import ram.talia.hexal.common.blocks.entity.BlockEntityMediafiedStorage

/**
 * Block that actually stores all the mediafied items from all the players bound to it.
 * Exists so that it's possible to do *something* to prevent the mediafied items slowly
 * growing and growing in memory consumption.
 */
class BlockMediafiedStorage(properties: Properties) : Block(properties), EntityBlock, IForgeLikeBlock {
    override fun newBlockEntity(pos: BlockPos, state: BlockState) = BlockEntityMediafiedStorage(pos, state)

    override fun <T : BlockEntity> getTicker(level: Level, state: BlockState, type: BlockEntityType<T>): BlockEntityTicker<T> {
        return BlockEntityTicker(Companion::tick)
    }

    companion object {
        private fun <T : BlockEntity> tick(level: Level, blockPos: BlockPos, blockState: BlockState, t: T) {
            if (t is BlockEntityMediafiedStorage) {
                if (!level.isClientSide)
                    t.serverTick()
            }
        }
    }
}
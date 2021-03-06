package com.dyonovan.neotech.common.blocks.connected

import com.dyonovan.neotech.utils.ClientUtils
import com.teambr.bookshelf.client.gui.GuiTextFormat
import com.teambr.bookshelf.traits.HasToolTip
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.{AxisAlignedBB, BlockPos}
import net.minecraft.world.World

/**
  * This file was created for NeoTech
  *
  * NeoTech is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Paul Davis "pauljoda"
  * @since 2/26/2016
  */
class BlockPhantomGlass extends BlockConnected("phantomGlass", Material.glass) with HasToolTip {

    /**
      * Define true if you are a clear texture
      *
      * @return
      */
    override def isClear: Boolean = true


    override def addCollisionBoxesToList(worldIn: World, pos: BlockPos, state: IBlockState, mask: AxisAlignedBB,
                                            list: java.util.List[AxisAlignedBB], collidingEntity: Entity) : Unit = {
        if(collidingEntity.isInstanceOf[EntityPlayer] && !collidingEntity.isSneaking) { /* NO OP */ }
        else
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity)
    }

    override def getToolTip(): List[String] = List(GuiTextFormat.ITALICS + ClientUtils.translate("neotech.phantomGlass.tip"))
}

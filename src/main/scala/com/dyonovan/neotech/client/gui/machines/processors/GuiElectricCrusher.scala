package com.dyonovan.neotech.client.gui.machines.processors

import java.awt.Color

import com.dyonovan.neotech.client.gui.machines.GuiAbstractMachineHelper
import com.dyonovan.neotech.common.container.machines.processors.ContainerElectricCrusher
import com.dyonovan.neotech.common.tiles.machines.processors.TileElectricCrusher
import com.teambr.bookshelf.client.gui.GuiBase
import com.teambr.bookshelf.client.gui.component.display.{GuiComponentArrow, GuiComponentPowerBar, GuiTabCollection}
import net.minecraft.entity.player.EntityPlayer

import scala.collection.mutable.ArrayBuffer

/**
  * This file was created for NeoTech
  *
  * NeoTech is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Dyonovan
  * @since August 13, 2015
  */
class GuiElectricCrusher(player: EntityPlayer, tileEntity: TileElectricCrusher) extends
        GuiBase[ContainerElectricCrusher](new ContainerElectricCrusher(player.inventory, tileEntity), 175, 165,
            "neotech.crusher.title") {

    protected var tile = tileEntity

    var hasUpgrade = tileEntity.getUpgradeBoard != null

    override def drawGuiContainerBackgroundLayer(f: Float, i: Int, j:Int): Unit = {
        tile = tile.getWorld.getTileEntity(tile.getPos).asInstanceOf[TileElectricCrusher]

        val oldValue = hasUpgrade
        hasUpgrade = tile.getUpgradeBoard != null

        if(oldValue != hasUpgrade) {
            val motherBoardTab = rightTabs.getTabs.head
            rightTabs.getTabs.clear()
            rightTabs.getTabs += motherBoardTab
            GuiAbstractMachineHelper.updateRightTabs(rightTabs, tileEntity, inventory)
        }

        super[GuiBase].drawGuiContainerBackgroundLayer(f, i, j)
    }

    override def addComponents(): Unit = {
        components += new GuiComponentArrow(64, 34) {
            override def getCurrentProgress: Int = tile.getCookProgressScaled(24)
        }
        components += new GuiComponentPowerBar(14, 18, 18, 60, new Color(255, 0, 0)) {
            override def getEnergyPercent(scale: Int): Int = {
                tile.getEnergyStored(null) * scale / tile.getMaxEnergyStored(null)
            }
            override def getDynamicToolTip(x: Int, y: Int): ArrayBuffer[String] = {
                ArrayBuffer(tile.getEnergyStored(null) + " / " + tile.getMaxEnergyStored(null))
            }
        }
    }

    override def addRightTabs(tabs : GuiTabCollection) = {
        if (tileEntity != null) {
            GuiAbstractMachineHelper.addRightTabs(tabs, tileEntity, inventory)
        }
    }
}
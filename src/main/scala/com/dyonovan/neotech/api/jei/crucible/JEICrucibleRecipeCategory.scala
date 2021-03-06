package com.dyonovan.neotech.api.jei.crucible

import java.awt.Color

import com.dyonovan.neotech.api.jei.NeoTechPlugin
import com.dyonovan.neotech.api.jei.drawables.{GuiComponentBox, GuiComponentPowerBarJEI, GuiComponentArrowJEI, SlotDrawable}
import com.dyonovan.neotech.lib.Reference
import mezz.jei.api.gui.{IDrawable, IRecipeLayout}
import mezz.jei.api.recipe.{IRecipeWrapper, IRecipeCategory}
import net.minecraft.client.Minecraft
import net.minecraft.util.{ResourceLocation, StatCollector}

/**
  * This file was created for NeoTech
  *
  * NeoTech is licensed under the
  * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License:
  * http://creativecommons.org/licenses/by-nc-sa/4.0/
  *
  * @author Dyonovan
  * @since 2/22/2016u
  */
class JEICrucibleRecipeCategory extends IRecipeCategory {

    val location = new ResourceLocation(Reference.MOD_ID, "textures/gui/jei/jei.png")
    val input = new SlotDrawable(56, 17)
    val arrow = new GuiComponentArrowJEI(81, 17)
    val power = new GuiComponentPowerBarJEI(14, 0, 18, 60, new Color(255, 0, 0)) {
        addColor(new Color(255, 150, 0))
        addColor(new Color(255, 255, 0))
    }
    val tank = new GuiComponentBox(115, 0, 50, 60)

    override def getBackground: IDrawable = NeoTechPlugin.jeiHelpers.getGuiHelper.createDrawable(location, 0, 0, 170, 60)

    override def setRecipe(recipeLayout: IRecipeLayout, recipeWrapper: IRecipeWrapper): Unit = {
        val fluidStack = recipeLayout.getFluidStacks
        val itemStack = recipeLayout.getItemStacks
        fluidStack.init(0, false, 116, 0, 48, 59, 2000, false, null)
        itemStack.init(0, true, 56, 17)

        recipeWrapper match {
            case crucible: JEICrucibleRecipe =>
                recipeLayout.getFluidStacks.set(0, crucible.getFluidOutputs)
                recipeLayout.getItemStacks.set(0, crucible.getInputs)
            case _ =>
        }
    }

    override def drawAnimations(minecraft: Minecraft): Unit = {
        arrow.draw(minecraft, 0, 0)
        power.draw(minecraft, 0, 0)
    }

    override def drawExtras(minecraft: Minecraft): Unit = {
        input.draw(minecraft)
        tank.draw(minecraft)
    }

    override def getTitle: String = StatCollector.translateToLocal("tile.neotech:electricCrucible.name")

    override def getUid: String = Reference.MOD_ID + ":crucible"
}

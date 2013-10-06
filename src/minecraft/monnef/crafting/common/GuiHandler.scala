/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.common

import cpw.mods.fml.common.network.IGuiHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import monnef.crafting.client.GuiAutoAssemblyTable

object GuiEnum extends Enumeration {
  type GuiEnum = Value
  val AutoAssemblyTable = Value
}

class GuiHandler extends IGuiHandler {

  import GuiEnum._

  def prepareValues(p: EntityPlayer, w: World, x: Int, y: Int, z: Int) = (p.inventory, w.getBlockTileEntity(x, y, z))

  def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = {
    val (inv, tile) = prepareValues(player, world, x, y, z)
    GuiEnum(ID) match {
      case AutoAssemblyTable => new ContainerAutoAssemblyTable(inv, tile)
      case _ => null
    }
  }

  def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = {
    val (inv, tile) = prepareValues(player, world, x, y, z)
    GuiEnum(ID) match {
      case AutoAssemblyTable => new GuiAutoAssemblyTable(new ContainerAutoAssemblyTable(inv, tile))
      case _ => null
    }
  }
}

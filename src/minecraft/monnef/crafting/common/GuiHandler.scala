/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.common

import cpw.mods.fml.common.network.IGuiHandler
import net.minecraft.entity.player.{InventoryPlayer, EntityPlayer}
import net.minecraft.world.World
import monnef.core.common.ContainerRegistry

import monnef.core.utils.scalautils._
import net.minecraft.tileentity.TileEntity

object GuiEnum extends Enumeration {
  type GuiEnum = Value
  val AutoAssemblyTable = Value
}

class GuiHandler extends IGuiHandler {
  def prepareValues(p: EntityPlayer, w: World, x: Int, y: Int, z: Int) = (w.getBlockTileEntity(x, y, z), p.inventory)

  def processOnlyIfKnownTile(t: TileEntity, i: InventoryPlayer)(f: (TileEntity, InventoryPlayer) => AnyRef) =
    if (ContainerRegistry.containsRegistration(t)) f(t, i)
    else null

  def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef =
    (prepareValues(player, world, x, y, z) |> processOnlyIfKnownTile)(ContainerRegistry.createContainer)

  def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef =
    (prepareValues(player, world, x, y, z) |> processOnlyIfKnownTile)(ContainerRegistry.createGui)
}

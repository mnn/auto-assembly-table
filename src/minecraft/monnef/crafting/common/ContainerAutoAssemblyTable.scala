/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.common

import net.minecraft.inventory.IInventory
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.tileentity.TileEntity
import monnef.core.block.ContainerMonnefCore

object ContainerAutoAssemblyTable {
  def slotsCount = 9 + 9 * 6
}

class ContainerAutoAssemblyTable(inv: InventoryPlayer, tile: TileEntity) extends ContainerMonnefCore(inv, tile) {

  import ContainerAutoAssemblyTable._

  def getSlotsCount: Int = slotsCount

  def getOutputSlotsCount: Int = 10

  def constructSlots(inv: IInventory) {}
}

/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.common

import net.minecraft.inventory.IInventory
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.tileentity.TileEntity
import monnef.core.block.ContainerMonnefCore

class ContainerAutoAssemblyTable(inv: InventoryPlayer, tile: TileEntity) extends ContainerMonnefCore(inv, tile) {
  def constructSlots(inv: IInventory) {}

  override def getYSize: Int = 270

  protected override def getYPlayerInvShift: Int = 1
}

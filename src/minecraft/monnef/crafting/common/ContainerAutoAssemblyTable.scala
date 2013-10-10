/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.common

import net.minecraft.inventory.IInventory
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.tileentity.TileEntity
import monnef.core.block.ContainerMonnefCore
import monnef.core.common.ContainerRegistry.ContainerTag

class ContainerAutoAssemblyTable(inv: InventoryPlayer, tile: TileEntity) extends ContainerMonnefCore(inv, tile) {
  def constructSlots(inv: IInventory) {}
}

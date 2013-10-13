/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.common

import net.minecraft.inventory.{Slot, IInventory}
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.tileentity.TileEntity
import monnef.core.block.ContainerMonnefCore
import AutoAssemblyTableHelper._

class ContainerAutoAssemblyTable(inv: InventoryPlayer, tile: TileEntity) extends ContainerMonnefCore(inv, tile) {
  def constructSlots(inv: IInventory) {
    // 8 x 15
    val slotSize = 18
    for {
      y <- 0 to inputGroupsCount - 1
      x <- 0 to slotsPerInputGroup - 1
    } {
      addSlotToContainer(new Slot(inv, 0 + startOfInputGroup(y) + x, 8 + x * slotSize, 15 + y * slotSize))
    }
    val outputSizeX = 3
    val outputSizeY = 4
    for {
      y <- 0 to outputSizeY - 1
      x <- 0 to outputSizeX - 1
    } {
      addSlotToContainer(new Slot(inv, startOfOutput + y * outputSizeX + x, 116 + x * slotSize, 105 + y * slotSize))
    }
  }

  override def getYSize: Int = 270

  protected override def getYPlayerInvShift: Int = 1
}

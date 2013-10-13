/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.client

import monnef.core.client.GuiContainerMonnefCore
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.tileentity.TileEntity
import monnef.core.block.ContainerMonnefCore

class GuiAutoAssemblyTable(_invPlayer: InventoryPlayer, tile: TileEntity, _container: ContainerMonnefCore) extends GuiContainerMonnefCore(_container) {
  setBackgroundTexture("guiaat.png")

  protected override def usesDoubleTexture(): Boolean = true

  protected override def getContainerTitle: String = "Automatic Assembly Table"
}

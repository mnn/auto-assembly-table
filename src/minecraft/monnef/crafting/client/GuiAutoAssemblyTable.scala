/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.client

import monnef.core.client.GuiContainerMonnefCore
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.tileentity.TileEntity
import monnef.core.block.ContainerMonnefCore
import net.minecraft.client.gui.GuiButton

class GuiAutoAssemblyTable(_invPlayer: InventoryPlayer, tile: TileEntity, _container: ContainerMonnefCore) extends GuiContainerMonnefCore(_container) {
  setBackgroundTexture("guiaat.png")

  protected override def usesDoubleTexture(): Boolean = true

  protected override def getContainerTitle: String = "Automatic Assembly Table"

  override def initGui() {
    super.initGui()
    val buttons = buttonList.asInstanceOf[java.util.List[GuiButton]]
    for (y <- 0 to 2;x <- 0 to 2;id = 3 * y + x)
      buttons.add(new GuiButton(id, 115 + x * 18 + this.x, 14 + y * 18 + this.y, 18, 18 + (if (y == 2) 1 else 0), id.toString))
  }
}

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

  val buttonXPos = 115
  val buttonYPos = 14
  val buttonSize = 18

  override def initGui() {
    super.initGui()
    val buttons = buttonList.asInstanceOf[java.util.List[GuiButton]]
    for (y <- 0 to 2;x <- 0 to 2;id = 3 * y + x)
      buttons.add(new GuiButton(id, buttonXPos + x * buttonSize + this.x, buttonYPos + y * buttonSize + this.y, buttonSize, buttonSize + (if (y == 2) 1 else 0), id.toString))
  }
}

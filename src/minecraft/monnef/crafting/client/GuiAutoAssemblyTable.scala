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
import monnef.crafting.common.AutoAssemblyTableHelper._

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
      buttons.add(new GuiButton(id, buttonXPos + x * buttonSize + this.x, buttonYPos + y * buttonSize + this.y, buttonSize, buttonSize + (if (y == 2) 2 else 0), id.toString))
  }

  protected override def drawGuiContainerForegroundLayer(par1: Int, par2: Int) {
    super.drawGuiContainerForegroundLayer(par1, par2)
  }

  private[this] val slotGuiSize = 18

  protected override def drawGuiContainerBackgroundLayer(f: Float, i: Int, j: Int) {
    super.drawGuiContainerBackgroundLayer(f, i, j)
    for (gId <- 0 to inputGroupsCount - 1) {
      val c = guiGroupColors(gId)
      drawGradientRectWithSize(x + 8 - 4, y + 15 + gId * slotGuiSize, 2, 16, c._1, c._2)
      drawGradientRectWithSize(x + 8 - 4 + 4 * 18 + 4, y + 15 + gId * slotGuiSize, 2, 16, c._1, c._2)
    }
  }
}

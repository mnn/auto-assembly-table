/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.block

import net.minecraft.world.{IBlockAccess, World}
import net.minecraft.entity.player.EntityPlayer
import monnef.crafting.AutomaticAssemblyTable
import monnef.crafting.common.GuiEnum
import net.minecraft.tileentity.TileEntity
import monnef.core.utils.{DirectionHelper, BreakableIronMaterial}
import BreakableIronMaterial.breakableIronMaterial
import net.minecraft.util.Icon
import monnef.crafting.common.AutoAssemblyTableHelper._
import monnef.crafting.network.AssemblyTablePatternUpdatePacket
import cpw.mods.fml.relauncher.Side

class BlockAutoAssemblyTable(_id: Int) extends BlockCrafting(_id, breakableIronMaterial, 1) {

  setResistance(5)
  setHardness(5)
  setIconsCount(32)

  override def onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, side: Int, par7: Float, par8: Float, par9: Float): Boolean =
    if (!player.isSneaking) {
      player.openGui(AutomaticAssemblyTable, GuiEnum.AutoAssemblyTable.id, world, x, y, z)
      if (!player.worldObj.isRemote) AssemblyTablePatternUpdatePacket.create(getTableTile(world, x, y, z), Side.CLIENT).sendToClient(player)
      true
    } else {
      super.onBlockActivated(world, x, y, z, player, side, par7, par8, par9)
    }

  override def hasTileEntity(metadata: Int): Boolean = true

  override def createTileEntity(world: World, metadata: Int): TileEntity = new TileAutoAssemblyTable

  override def getBlockTexture(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): Icon = {
    val shift = if (DirectionHelper.isYAxis(side)) 16 else 0
    icons(tableGroupToDyeNumber(side) + shift)
  }

  def getTableTile(w: World, x: Int, y: Int, z: Int): TileAutoAssemblyTable = w.getBlockTileEntity(x, y, z).asInstanceOf[TileAutoAssemblyTable]
}

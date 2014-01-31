/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.block

import monnef.core.common.ContainerRegistry.ContainerTag
import monnef.crafting.common.AutoAssemblyTableHelper._
import monnef.crafting.common.{PatternBlank, PatternItem}
import net.minecraft.client.Minecraft
import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.client.FMLClientHandler
import monnef.crafting.client.GuiAutoAssemblyTable

@ContainerTag(slotsCount = totalSlots, outputSlotsCount = outputSlotsCount, containerClassName = "monnef.crafting.common.ContainerAutoAssemblyTable", guiClassName = "monnef.crafting.client.GuiAutoAssemblyTable")
class TileAutoAssemblyTable extends TileCraftingWithInventory {

  import monnef.crafting.common.AutoAssemblyTableHelper._

  def getInvName: String = "Automatic Assembly Table"

  def isInvNameLocalized: Boolean = false

  // cost=8; slotsUsed=5;
  // slotsUsed*cost+20*cost = 200

  private val pattern: Array[PatternItem] = Array.fill(recipeSlotsCount)(PatternBlank)

  def updatePattern(indexStateList: List[(Int, Int)]) {
    indexStateList foreach {
      case (idx, state) =>
        pattern.update(idx, PatternItem.fromInt(state))
    }
    // client updates its AAT GUI
    if (worldObj.isRemote) {
      FMLClientHandler.instance().getClient.currentScreen match {
        case gui: GuiAutoAssemblyTable =>
          if (gui.tile == this) gui.fillPatternFromTile()
        case _ => // nothing
      }
    }
  }

  def generateCompleteIndexStateList(): List[(Int, Int)] = pattern.zipWithIndex.map {case (pattItem, idx) => (idx, pattItem.toInt)}.toList

  def getErrorOfIndexStateList(l: List[(Int, Int)]): Option[String] = {
    val wrongIndex = l.find {case (idx, state) => idx >= recipeSlotsCount || idx < 0}
    if (wrongIndex.isDefined) Some(s"Invalid index ${wrongIndex.get}.")

    val wrongState = l.find {case (idx, state) => state >= recipeSlotsCount}
    if (wrongState.isDefined) Some(s"Invalid state ${wrongState.get}.")

    None
  }
}

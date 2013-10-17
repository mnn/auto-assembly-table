/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.common

import monnef.core.utils.{ColorHelper, DyeHelper}


object AutoAssemblyTableHelper {
  final val inputGroupsCount = 9
  final val slotsPerInputGroup = 4
  final val outputSlotsCount = 3 * 4
  final val recipeSlotsCount = 9

  def startOfInputGroup(g: Int) = g * slotsPerInputGroup

  val startOfOutput = startOfInputGroup(inputGroupsCount - 1) + slotsPerInputGroup

  final val totalSlots = slotsPerInputGroup * inputGroupsCount + outputSlotsCount

  private val groupToDyeMap: Array[Int] = Array(1, 10, 4, 13, 14, 8, 12, 11, 15)
  private val dyeValues = groupToDyeMap.map(id => {
    val c = ColorHelper.getColor(DyeHelper.getIntColor(id))
    c.setAlpha(255)
    val cc = ColorHelper.getColor(ColorHelper.addBrightness(c.toInt, -50))
    (c.toInt, cc.toInt)
  })

  def guiGroupColors(g: Int): (Int, Int) = dyeValues(g)

  def guiGroupMainColor(g: Int): Int = guiGroupColors(g)._1

  def tableGroupToDyeNumber(g: Int) =
    if (g < 0 || g >= inputGroupsCount) throw new IllegalArgumentException
    else groupToDyeMap(g)

  def checkSanity() {
    assert(groupToDyeMap.length == inputGroupsCount, s"groupToDyeMap's size should be $inputGroupsCount, but is ${groupToDyeMap.length}.")
    assert(groupToDyeMap.distinct.length == groupToDyeMap.length, "groupToDyeMap contains duplicate values.")
  }

  checkSanity()
}

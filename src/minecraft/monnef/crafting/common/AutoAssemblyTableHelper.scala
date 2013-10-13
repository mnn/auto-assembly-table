/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.common


object AutoAssemblyTableHelper {
  final val inputGroupsCount = 9
  final val slotsPerInputGroup = 4
  final val outputSlotsCount = 3 * 4
  final val recipeSlotsCount = 9

  def startOfInputGroup(g: Int) = g * slotsPerInputGroup

  val startOfOutput = startOfInputGroup(inputGroupsCount) + slotsPerInputGroup

  final val totalSlots = slotsPerInputGroup * (inputGroupsCount + 1) + outputSlotsCount

  // TODO: do the actual mapping
  private val groupToDyeMap: Map[Int, Int] = (0 to inputGroupsCount).foldLeft(Map[Int, Int]())((a, v) => a + (v -> v))

  def tableGroupToDyeNumber(g: Int) =
    if (g < 0 || g >= inputGroupsCount) throw new IllegalArgumentException
    else groupToDyeMap(g)
}

/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.common

abstract class PatternItem {
  def isBlank: Boolean

  def isColor = !isBlank

  def toInt: Int
}

object PatternItem {
  def fromInt(state: Int): PatternItem =
    if (state < 0) PatternBlank
    else PatternColor(state)
}

case object PatternBlank extends PatternItem {
  def isBlank: Boolean = true

  def toInt: Int = -1
}

case class PatternColor(color: Int) extends PatternItem {
  if (color < 0) throw new RuntimeException("Color number cannot be less than zero.")

  def isBlank: Boolean = false

  def toInt: Int = color
}
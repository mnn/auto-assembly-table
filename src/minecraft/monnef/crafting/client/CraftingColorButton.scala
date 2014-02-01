/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.client

import monnef.core.client.ColorButton

class CraftingColorButton(_id: Int, _x: Int, _y: Int, _width: Int, _height: Int, _colorStates: Array[Integer],
                          _stringStates: Array[String])
  extends ColorButton(_id, _x, _y, _width, _height, _colorStates, _stringStates, 0, true) {
  val stringsLen = _stringStates.length

  def isColorSelected: Boolean = getCurrentStateNumber >= stringsLen

  def numberOfSelectedState: Int = getCurrentStateNumber - stringsLen

  def setNumberOfSelectedColor(id: Int) { setState(stringsLen + id) }

  override def doesReactToThisButton(button: Int): Boolean = super.doesReactToThisButton(button) || button == 2
}

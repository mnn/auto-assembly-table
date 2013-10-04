/*
 * Automatic Assembly Table
 * author: monnef
 */

package monnef.crafting.client

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import cpw.mods.fml.common.registry.LanguageRegistry

class CraftingCreativeTab(label: String, title: String) extends CreativeTabs(label) {
  private var iconItem: Item = _

  override def getTabIconItem: Item = iconItem

  def setup(iconItem: Item) { this.iconItem = iconItem }

  LanguageRegistry.instance().addStringLocalization("itemGroup." + label, "en_US", title)
}

package data;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private ItemManager(){

    }
    public static @NotNull ItemStack createCustomItem(Material type, String name, @Nullable List<String> lore, int count, boolean shiny){
        ItemStack i = new ItemStack(type);
        i.setAmount(count);
        ItemMeta m = i.getItemMeta();
        m.displayName(Component.text(name));
        if (lore != null) {
            List<Component> l = new ArrayList<>();
            lore.forEach(s -> l.add(Component.text(s)));
            m.lore(l);
        }
        if (shiny) {
            m.addEnchant(Enchantment.DURABILITY, 1, false);
        }
        m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_DYE, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_POTION_EFFECTS);
        m.setUnbreakable(true);
        i.setItemMeta(m);
        return i;
    }
}

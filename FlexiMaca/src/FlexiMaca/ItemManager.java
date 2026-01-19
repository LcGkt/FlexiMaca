package FlexiMaca;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack getFlexiMaca() {

        FileConfiguration config = Main.getInstance().getConfig();

        ItemStack item = new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(config.getString("item.nome").replace("&", "§"));

        List<String> lore = new ArrayList<>();
        for (String line : config.getStringList("item.lore")) {
            lore.add(line.replace("&", "§"));
        }
        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }

    public static void aplicarBuffs(Player p) {

        FileConfiguration config = Main.getInstance().getConfig();

        for (String key : config.getConfigurationSection("buffs").getKeys(false)) {

            if (!config.getBoolean("buffs." + key + ".ativo"))
                continue;

            PotionEffectType type = PotionEffectType.getByName(key.toUpperCase());
            if (type == null) continue;

            int dur = config.getInt("buffs." + key + ".duracao");
            int lvl = config.getInt("buffs." + key + ".nivel");

            p.addPotionEffect(new PotionEffect(type, dur * 20, lvl - 1));
        }
    }
}

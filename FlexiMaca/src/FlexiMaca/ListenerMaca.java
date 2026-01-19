package FlexiMaca;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class ListenerMaca implements Listener {

    @EventHandler
    public void aoComer(PlayerItemConsumeEvent e) {

        if (e.getItem().getType() != Material.GOLDEN_APPLE) return;
        if (!e.getItem().hasItemMeta()) return;
        if (!e.getItem().getItemMeta().hasDisplayName()) return;

        String nome = Main.getInstance().getConfig().getString("item.nome").replace("&", "§");

        if (e.getItem().getItemMeta().getDisplayName().equals(nome)) {
            ItemManager.aplicarBuffs(e.getPlayer());
        }
    }
}

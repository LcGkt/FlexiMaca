package FlexiMaca;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        
        getCommand("fleximaca").setExecutor(new Commands());
        getLogger().info("§a[FlexiMaca]§7 ativado!");
        
        getServer().getPluginManager().registerEvents(new ListenerMaca(), this);
        
    }

    @Override
    public void onDisable() {
        getLogger().info("§c[FlexiMaca] desativado!");
    }

    public static Main getInstance() {
        return instance;
    }
}

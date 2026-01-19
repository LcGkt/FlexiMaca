package FlexiMaca;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        getCommand("fleximaca").setExecutor(new Commands());
        getServer().getPluginManager().registerEvents(new ListenerMaca(), this);

        // msg no console
        sendConsole("&a[FlexiMaca] habilitado! Servidor rodando: " + Bukkit.getBukkitVersion().split("-")[0]);
		
    }
        @Override
        public void onDisable() {
        	sendConsole("&c[FlexiMaca] desabilitado!");
        }
        	

    /** Enviar mensagem colorida ao console */
    public static void sendConsole(String msg) {
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        console.sendMessage(color(msg));
		
	}

	/** COLOR SYSTEM */
    public static String color(String msg) {
        return msg.replace("&", "§");
    }

    public static Main getInstance() {
        return instance;
    }
}

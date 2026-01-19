package FlexiMaca;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("fleximaca.admin")) {
            sender.sendMessage("§cVocê não tem permissão para usar este comando.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§eUse: /fleximaca give <jogador> [quantia]");
            return true;
        }

        if (args[0].equalsIgnoreCase("give")) {

            if (args.length < 2) {
                sender.sendMessage("§cUse: /fleximaca give <jogador> [quantia]");
                return true;
            }

            String playerName = args[1];
            String quantidadeArg = (args.length >= 3 ? args[2] : "1");

            // Roda o processamento em ASYNC
            Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {

                int quantidade;

                try {
                    quantidade = Integer.parseInt(quantidadeArg);
                    if (quantidade <= 0) quantidade = 1;
                } catch (NumberFormatException ex) {
                    sender.sendMessage("§cQuantidade inválida.");
                    return;
                }

                int finalQuantidade = quantidade;

                // Voltamos ao main thread para dar o item
                Bukkit.getScheduler().runTask(Main.getInstance(), () -> {

                    Player target = Bukkit.getPlayer(playerName);
                    if (target == null) {
                        sender.sendMessage("§cJogador offline.");
                        return;
                    }

                    ItemStack item = ItemManager.getFlexiMaca().clone();
                    int maxStack = item.getMaxStackSize();

                    int entregar = Math.min(finalQuantidade, maxStack);
                    item.setAmount(entregar);

                    if (finalQuantidade > maxStack) {
                        sender.sendMessage("§eQuantidade maior que o máximo ("
                                + maxStack + "). Enviando apenas o máximo.");
                    }

                    target.getInventory().addItem(item);

                    target.sendMessage("§aVocê recebeu §6" + entregar + "x Maçã Flexi§a!");
                    sender.sendMessage("§eEnviado para §f" + target.getName() + " §e(x" + entregar + ")");
                });

            });

            return true;
        }

        return false;
    }
}

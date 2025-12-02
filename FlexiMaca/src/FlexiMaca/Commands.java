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

        // Permissão
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

            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage("§cJogador offline.");
                return true;
            }

            int quantidade = 1;
            if (args.length >= 3) {
                try {
                    quantidade = Integer.parseInt(args[2]);
                    if (quantidade <= 0) {
                        sender.sendMessage("§cA quantidade deve ser um número positivo.");
                        return true;
                    }
                } catch (NumberFormatException ex) {
                    sender.sendMessage("§cQuantidade inválida. Use um número.");
                    return true;
                }
            }

            // Clona o item e ajusta a quantidade respeitando o max stack do item
            ItemStack item = ItemManager.getFlexiMaca().clone();
            int maxStack = item.getMaxStackSize();
            if (quantidade > maxStack) {
                quantidade = maxStack;
                sender.sendMessage("§eQuantidade maior que o máximo do item. Enviando o máximo: §6" + maxStack);
            }

            item.setAmount(quantidade);
            target.getInventory().addItem(item);

            target.sendMessage("§aVocê recebeu §6" + quantidade + "x §6Maçã Flexi§a!");
            sender.sendMessage("§eMaçã Flexi enviada para §f" + target.getName() + " §e(x" + quantidade + ")");

            return true;
        }

        return false;
    }
}

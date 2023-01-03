package command;

import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DMHandler implements CommandHandler {
    private static final String INVAILD_USE = Main.INDEX + "/귓속말 <플레이어> <메시지> - 플레이어에게 귓속말을 보냅니다.\n";


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String commandName, @NotNull String[] args) {
        if (CommandHandler.commandEqualsAny(commandName, "귓속말", "귓말", "귓", "w", "msg", "tell", "dm")) {
            if (args.length <= 1) {
                sender.sendMessage(INVAILD_USE);
                return false;
            }

            String target = args[0];
            String[] message = new String[args.length-1];
            System.arraycopy(args, 1, message,0,args.length-1);

            String msg = String.join(" ", message);

            if (target.equals("콘솔")) {
                sender.sendMessage(Main.INDEX + "§7주의: 콘솔에 귓속말을 보내고 있습니다. 이는 모든 관리자에게 귓속말을 보내는 것과 같으며, 관리자들이 바쁠 시 답장이 지연되거나 받지 못할수도 있는 점 유의하시기 바랍니다.");
                sender.sendMessage(Main.INDEX + "§aYou §7→ §d§lCONSOLE§7: §f" + msg);
                Bukkit.getConsoleSender().sendMessage(Main.INDEX + "§e" + sender.getName() + " §7→ §d§lYou§7: §f" + msg);
                return true;
            }
            Player reciver = Bukkit.getPlayer(target);
            if (reciver == null) {
                sender.sendMessage(Main.INDEX + "§c현재 해당 플레이어가 서버에 접속하지 않은 상태입니다!");
                return false;
            }
            if (Objects.equals(sender, reciver)) {
                sender.sendMessage(Main.INDEX + "§c자기 자신에게 귓속말을 보낼 수 없습니다.");
                return false;
            }
            // 귓속말 보내기!!

            final String senderName;
            final String reciverName;

            if (sender instanceof Player p) {
                senderName = p.getName();
                reciverName = "§aYou";
                Bukkit.getConsoleSender().sendMessage(Main.INDEX + "§a" + senderName + " §7→ §e" + reciver.getName() + "§7: §f" + msg);
            } else {
                reciverName = "§d§lCONSOLE";
                senderName = "§d§lCONSOLE";
            }
            sender.sendMessage(Main.INDEX + "§aYou §7→ §f" + reciverName + "§7: §f" + String.join(" ", msg));
            reciver.sendMessage(Main.INDEX + "§a" + senderName + " §7→ §f" + reciverName + "§7: " + msg);
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String commandName, @NotNull String[] args) {
        if (CommandHandler.commandEqualsAny(commandName, "귓속말", "귓말", "귓", "w", "msg", "tell", "dm")) {
            List<String> toReturn = new ArrayList<>();
            Bukkit.getOnlinePlayers().forEach(p -> toReturn.add(p.getName()));
            toReturn.add("콘솔");
            return toReturn;
        }
        return null;
    }
}

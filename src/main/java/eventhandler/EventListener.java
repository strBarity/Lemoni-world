package eventhandler;

import io.papermc.paper.event.player.AsyncChatEvent;
import main.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class EventListener implements Listener {
    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e) {
        e.joinMessage(Component.text(Main.INDEX + e.getPlayer().getName() + "님이 접속했습니다."));
    }

    @EventHandler
    public void onQuit(@NotNull PlayerQuitEvent e) {
        e.quitMessage(Component.text(Main.INDEX + e.getPlayer().getName() + "님이 퇴장했습니다."));
    }

    @EventHandler
    public void onChat(@NotNull AsyncChatEvent e) {
        e.setCancelled(true);
        Bukkit.broadcast(Component.text(ChatColor.BOLD + e.getPlayer().getName() + "§f: ").append(e.message()));
    }

    @EventHandler
    public void onInventoryClick(@NotNull InventoryClickEvent e) {
        String title = ((TextComponent) e.getView().title()).content();
        if (title.equals("색깔 선택")) {
            e.setCancelled(true);
        }
    }
}

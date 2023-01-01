package main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static final String INDEX = "§6[§e§lLemon§6]§f ";

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(Main.INDEX + "§a플러그인이 활성화되었습니다.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Main.INDEX + "§c플러그인이 비활성화되었습니다.");
    }
}

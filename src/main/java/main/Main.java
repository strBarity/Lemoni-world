package main;

import command.ColorHandler;
import command.DMHandler;
import eventhandler.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {
    public static final String INDEX = "§6[§e§lLemon§6]§f ";

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(INDEX + "§a플러그인이 활성화되었습니다.");
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        setExcutors(new DMHandler(), new ColorHandler());
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(INDEX + "§c플러그인이 비활성화되었습니다.");
    }
}

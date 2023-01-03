package command;

import data.ItemManager;
import main.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class ColorHandler implements CommandHandler {
    private static final int WHITE_COLOR_SLOT = 10;
    private static final int DARK_RED_COLOR_SLOT = 11;
    private static final String CLICK_TO = "§e▶ 클릭해서 이 색깔로 설정하기";
    private static final ItemStack BLANK = ItemManager.createCustomItem(Material.GRAY_STAINED_GLASS_PANE, " ", null, 1, false);
    private static final ItemStack NOT_UNLOCKED = ItemManager.createCustomItem(Material.GRAY_DYE, "§c해금되지 않음", null, 1, false);
    private static final List<Integer> OUTLINES = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 26);
    private static final List<Integer> EMPTY_SLOT = Arrays.asList(12, 13, 14, 15, 16);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (CommandHandler.commandEqualsAny(label, "색깔", "color")) {
            if (sender instanceof Player p) {
                Inventory gui = Bukkit.createInventory(null, 27, Component.text("색깔 선택"));
                for (int i : OUTLINES) {
                    gui.setItem(i, BLANK);
                }

                gui.setItem(WHITE_COLOR_SLOT, ItemManager.createCustomItem(Material.WHITE_DYE, "§f§l흰색", Arrays.asList("", "§f입장 시 주어지는 기본 색깔.", "", CLICK_TO), 1, true));
                ItemStack darkRed;
                if (p.isOp()) {
                    darkRed = ItemManager.createCustomItem(Material.RED_DYE, "§4§l짙은 빨간색", Arrays.asList("", "§f관리자 전용으로 주어지는 색깔. ", "", CLICK_TO), 1, true);
                } else {
                    darkRed = NOT_UNLOCKED;
                }
                gui.setItem(DARK_RED_COLOR_SLOT, darkRed);
                for (int i : EMPTY_SLOT) {
                    gui.setItem(i, ItemManager.createCustomItem(Material.BARRIER, "§cCOMING SOON", null, 1, false));
                }
                p.openInventory(gui);
            } else sender.sendMessage(Main.INDEX + "이 명령어는 플레이어만 사용할 수 있습니다.");
            return true;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (CommandHandler.commandEqualsAny(label, "색깔", "color")) {
            return List.of();
        }
        return null;
    }
}

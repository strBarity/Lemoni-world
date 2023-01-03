package data;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public record PlayerAttribute(boolean op,
                              int money,
                              ChatColor chatColor) implements Attribute {

    private static final Map<Player, PlayerAttribute> PLAYER_ATTRIBUTE_MAP = new ConcurrentHashMap<>();

    public static void createNew(Player player) throws DataAlreadyExistsException {
        if (PLAYER_ATTRIBUTE_MAP.containsKey(player)) {
            throw new DataAlreadyExistsException();
        }

        PLAYER_ATTRIBUTE_MAP.put(player, new PlayerAttribute.Builder(player.isOp()).build());
    }

    public static PlayerAttribute fromPlayer(Player player) {
        if (!PLAYER_ATTRIBUTE_MAP.containsKey(player)) {
            throw new NullPointerException(player.getName() + "'s data doesn't exist!!");
        }
        return PLAYER_ATTRIBUTE_MAP.get(player);
    }


    @Override
    public Builder edit() {
        return new PlayerAttribute.Builder(op)
                .setMoney(money);
    }

    public static final class Builder implements AttributeBuilder {

        private final boolean op;
        private int money = 0;
        private ChatColor chatColor = ChatColor.WHITE;

        public Builder(boolean op){
            this.op = op;
        }


        public Builder setMoney(int money) {
            this.money = money;
            return this;
        }

        public Builder setChatColor(ChatColor chatColor){
            this.chatColor = chatColor;
            return this;
        }

        @Contract(" -> new")
        @Override
        public @NotNull PlayerAttribute build() {
            return new PlayerAttribute(op,money,chatColor);
        }
    }
}

package command;

import org.bukkit.command.TabExecutor;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 커맨드가 수행하는 공통 기능
 */
public interface CommandHandler extends TabExecutor {
    static boolean commandEqualsAny(String target, String... comparer){
        return Arrays.stream(comparer).collect(Collectors.toSet()).contains(target);
    }
}

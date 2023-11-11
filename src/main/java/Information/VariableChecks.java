package Information;

import net.dv8tion.jda.api.entities.Guild;

import java.io.FileNotFoundException;

public class VariableChecks {
    private static String prefix;
    private static String botChannel;
    public static String getPrefix(Guild guild) {
        String filepath = ServerStorage.getInfoFilePath(guild, "prefix.txt");
        try {
            prefix = FileAccessor.getFileLine(filepath);
        } catch (FileNotFoundException e) {
            prefix = "!";
            try {
                FileAccessor.rewriteFile(filepath, prefix);
            } catch (FileNotFoundException e2) {}
        }
        return prefix;
    }
    public static void setPrefix(Guild guild, String pre) {
        prefix = pre;
        try {
            String filepath = ServerStorage.getInfoFilePath(guild, "prefix.txt");
            FileAccessor.rewriteFile(filepath, prefix);
        } catch (FileNotFoundException e2) {}
    }
    public static String getBotChannel(Guild guild) {
        String filepath = ServerStorage.getInfoFilePath(guild, "botChannel.txt");
        try {
            botChannel = FileAccessor.getFileLine(filepath);
        } catch (FileNotFoundException e) {
            botChannel = guild.getSystemChannel().getId();
            try {
                FileAccessor.rewriteFile(filepath, botChannel);
            } catch (FileNotFoundException e2) {}
        }
        return botChannel;
    }

    public static void setBotChannel(Guild guild, String bC) {
        botChannel = bC;
        try {
            String filepath = ServerStorage.getInfoFilePath(guild, "botChannel.txt");
            FileAccessor.rewriteFile(filepath, botChannel);
        } catch (FileNotFoundException e2) {}
    }
}

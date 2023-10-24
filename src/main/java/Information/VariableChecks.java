package Information;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;

import java.io.FileNotFoundException;

public class VariableChecks {
    public static String PREFIX = "!";
    private static String botChannel;
    public static void setPrefix(String prefix) {
        PREFIX = prefix;
    }
    public static String getBotChannel(GuildReadyEvent event) {
        String filepath = ServerStorage.getInfoFilePath(event.getGuild(), "botChannel.txt");
        try {
            botChannel = FileAccessor.getFileLine(filepath);
        } catch (FileNotFoundException e) {
            botChannel = event.getGuild().getSystemChannel().getId();
            try {
                FileAccessor.rewriteFile(filepath, botChannel);
            } catch (FileNotFoundException e2) {}
        }
        return botChannel;
    }
}

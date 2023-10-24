package Information;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;

import java.io.FileNotFoundException;

public class VariableChecks {
    public String PREFIX = "!";
    private String botChannel;
    public void setPrefix(String prefix) {
        PREFIX = prefix;
    }
    public String getBotChannel(GuildReadyEvent event) {
        if (botChannel == null) {
            String filepath = ServerStorage.getInfoFilePath(event.getGuild(), "botChannel.txt");
            try {
                botChannel = FileAccessor.getFileLine(filepath);
            } catch (FileNotFoundException e) {
                botChannel = event.getGuild().getSystemChannel().getId();
                try {
                    FileAccessor.rewriteFile(filepath, botChannel);
                } catch (FileNotFoundException e2) {}
            }
        }
        return botChannel;
    }
}

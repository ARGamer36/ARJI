package Commands;

import Commands.Abstracts.PrefixCommand;
import Main.ClearanceChecks;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class VersionCommand extends PrefixCommand {
    private String VERSION;

    public VersionCommand(String version) {
        VERSION = version;
        name = "version";
        description = "Returns version of bot";
    }
    @Override
    public void action(MessageReceivedEvent event) {
        if (ClearanceChecks.isAdmin(event.getMember())) {
            event.getMessage().reply("Version: " + VERSION).queue();
        }
    }
}

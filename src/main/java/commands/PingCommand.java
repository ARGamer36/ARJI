package commands;

import commands.abstracts.PrefixCommand;
import main.ClearanceChecks;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PingCommand extends PrefixCommand {
    public PingCommand() {
        name = "ping";
        description = "Ping Pong";
    }

    @Override
    public void action(MessageReceivedEvent event) {
        if (ClearanceChecks.isAdmin(event.getMember())) {
            event.getMessage().reply("pong").queue();
        }
    }
}

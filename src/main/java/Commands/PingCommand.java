package Commands;

import Commands.Abstracts.PrefixCommand;
import Main.ClearanceChecks;
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

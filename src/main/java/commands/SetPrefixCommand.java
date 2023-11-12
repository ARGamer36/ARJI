package commands;

import commands.abstracts.PrefixCommand;
import main.ClearanceChecks;
import information.VariableChecks;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SetPrefixCommand extends PrefixCommand {
    public SetPrefixCommand() {
        name = "setPrefix";
        description = "Sets prefix to given value";
    }

    @Override
    public void action(MessageReceivedEvent event) {
        if (ClearanceChecks.isAdmin(event.getMember())) {
            String[] messageArray = event.getMessage().getContentRaw().split(" ");
            if (messageArray.length < 2) {
                event.getMessage().reply("Please provide a prefix").queue();
            } else {
                String prefix = messageArray[1];
                VariableChecks.setPrefix(event.getGuild(), prefix);
                event.getMessage().reply("Prefix set to **__" + prefix + "__**").queue();
            }
        }
    }
}

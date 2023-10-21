package Commands;

import Commands.Abstracts.PrefixCommand;
import Main.ClearanceChecks;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class OnCommand extends PrefixCommand {
    public OnCommand() {
        name = "on";
        description = "Bots will react with ✅ if they are on";
    }

    @Override
    public void action(MessageReceivedEvent event) {
        if (ClearanceChecks.isAdmin(event.getMember())) {
            event.getMessage().addReaction(Emoji.fromFormatted("✅")).queue();
        }
    }
}

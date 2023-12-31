package commands.abstracts;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class PrefixCommand {
    public String name;
    public String description;
    public PrefixCommand() {
        name = "CommandName";
        description = "CommandDescription";
    }
    public boolean matchesCommand(String command) {
        return command.equalsIgnoreCase(name);
    }
    public void action(MessageReceivedEvent event) {
        event.getMessage().reply("Command " + name + " performed!").queue();
    }
    public MessageEmbed.Field helpField() {
        MessageEmbed.Field field = new MessageEmbed.Field(name, description, false);
        return field;
    }
}

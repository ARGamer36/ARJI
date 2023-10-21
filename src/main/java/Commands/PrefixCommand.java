package Commands;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PrefixCommand {
    public String name;
    public String description;
    public boolean compare(String command) {
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

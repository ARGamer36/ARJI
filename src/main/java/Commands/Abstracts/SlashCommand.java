package Commands.Abstracts;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.List;

public abstract class SlashCommand {
    public String name;
    public String description;
    public List<OptionData> options;
    public SlashCommandData getData() {
        SlashCommandData data = Commands.slash(name, description);
        try {
            data.addOptions(options);
        } catch (NullPointerException e) {}
        return data;
    }
    public void action(SlashCommandInteractionEvent event) {
        event.reply("Command " + name + " performed!").queue();
    }
    public MessageEmbed.Field helpField() {
        String header = name;
        for (OptionData option : options) {
            header += " " + option.getName();
        }
        MessageEmbed.Field field = new MessageEmbed.Field(header, description, false);
        return field;
    }
}
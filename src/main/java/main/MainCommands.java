package main;

import commands.*;
import commands.abstracts.PrefixCommand;
import commands.abstracts.SlashCommand;
import information.ServerStorage;
import information.VariableChecks;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class MainCommands extends ListenerAdapter {
    public List<PrefixCommand> prefixCommands;
    public List<SlashCommand> slashCommands;
    public MainCommands(String version) {
        slashCommands = new ArrayList<>();
        prefixCommands = new ArrayList<>();
        prefixCommands.add(new VersionCommand(version));
        prefixCommands.add(new OnCommand());
        prefixCommands.add(new SetPrefixCommand());
        prefixCommands.add(new PingCommand());
        prefixCommands.add(new BuildTime());
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        try {
            ServerStorage.createServerFilePath(event.getGuild());
        } catch (IOException e) {
            event.getGuild().getSystemChannel().sendMessage("FAILED TO STORE INFO");
        }
        sendServerMessage(event.getGuild(), "Bot Activated");
        List<CommandData> commandData = new ArrayList<>();
        for (SlashCommand slashCommand : slashCommands) {
            commandData.add(slashCommand.getData());
        }
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        for (SlashCommand slashCommand : slashCommands) {
            if (command.equalsIgnoreCase(slashCommand.name)) {
                slashCommand.action(event);
            }
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String fullMessage = event.getMessage().getContentRaw();
        if (fullMessage.startsWith(VariableChecks.getPrefix(event.getGuild()))) {
            String[] messageArray = fullMessage.split(" ");
            String command = messageArray[0].substring(1);
            for (PrefixCommand prefixCommand : prefixCommands) {
                if (prefixCommand.matchesCommand(command)) {
                    prefixCommand.action(event);
                }
            }
        }
    }
    public static void sendServerMessage(Guild guild, String message) {
        String botChannel = VariableChecks.getBotChannel(guild);
        guild.getTextChannelById(botChannel).sendMessage(message).queue();
    }
}

package Main;

import Commands.*;
import Commands.Abstracts.PrefixCommand;
import Commands.Abstracts.SlashCommand;
import Information.ServerStorage;
import Information.VariableChecks;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class MainCommands extends ListenerAdapter {
    public List<PrefixCommand> prefixCommands;
    public List<SlashCommand> slashCommands;
    public VariableChecks variableChecks;
    public MainCommands(String version) {
        variableChecks = new VariableChecks();
        prefixCommands = new ArrayList<>();
        prefixCommands.add(new VersionCommand(version));
        prefixCommands.add(new OnCommand());
        prefixCommands.add(new SetPrefixCommand(variableChecks));
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
        sendServerMessage(event, "Bot Activated");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String fullMessage = event.getMessage().getContentRaw();
        if (fullMessage.startsWith(variableChecks.PREFIX)) {
            String[] messageArray = fullMessage.split(" ");
            String command = messageArray[0].substring(1);
            for (PrefixCommand prefixCommand : prefixCommands) {
                if (prefixCommand.matchesCommand(command)) {
                    prefixCommand.action(event);
                }
            }
        }
    }
    public void sendServerMessage(GuildReadyEvent event, String message) {
        String botChannel = variableChecks.getBotChannel(event);
        event.getGuild().getTextChannelById(botChannel).sendMessage(message).queue();
    }
}

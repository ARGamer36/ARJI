package Main;

import Commands.Abstracts.PrefixCommand;
import Commands.PingCommand;
import Commands.SetPrefixCommand;
import Commands.Abstracts.SlashCommand;
import Commands.OnCommand;
import Commands.VersionCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class MainCommands extends ListenerAdapter {
    public List<PrefixCommand> prefixCommands;
    public List<SlashCommand> slashCommands;
    public MainCommands(String version) {
        prefixCommands = new ArrayList<>();
        prefixCommands.add(new VersionCommand(version));
        prefixCommands.add(new OnCommand());
        prefixCommands.add(new SetPrefixCommand());
        prefixCommands.add(new PingCommand());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String fullMessage = event.getMessage().getContentRaw();
        if (fullMessage.startsWith(VariableChecks.PREFIX)) {
            String[] messageArray = fullMessage.split(" ");
            String command = messageArray[0].substring(1);
            for (PrefixCommand prefixCommand : prefixCommands) {
                if (prefixCommand.matchesCommand(command)) {
                    prefixCommand.action(event);
                }
            }
        }
    }
}

package Commands;

import Commands.Abstracts.PrefixCommand;
import Information.FileAccessor;
import Main.ClearanceChecks;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.FileNotFoundException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class BuildTime extends PrefixCommand {
    public BuildTime() {
        name = "build";
        description = "Returns build time";
    }

    @Override
    public void action(MessageReceivedEvent event) {
        if (ClearanceChecks.isAdmin(event.getMember())) {
            event.getMessage().reply(getBuiltTime());
        }
    }

    public static String getBuiltTime() {
        try {
            String filePath = "buildInfo.txt";
            String timeStamp = FileAccessor.getFileString(filePath);
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss z");
            ZonedDateTime utcTime = ZonedDateTime.parse(timeStamp, inputFormat.withZone(ZoneId.of("UTC")));
            ZonedDateTime estTime = utcTime.withZoneSameInstant(ZoneId.of("America/New_York"));
            DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a z");
            String currentEST = estTime.format(outputFormat);
            return currentEST;
        } catch (FileNotFoundException e) {
            System.out.println("Failed to find buildInfo.txt");
            return null;
        }
    }
}

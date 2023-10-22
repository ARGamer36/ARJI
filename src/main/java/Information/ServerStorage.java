package Information;

import net.dv8tion.jda.api.entities.Guild;

import java.io.IOException;

public class ServerStorage {
    final private static String FOLDER = FileAccessor.FOLDER;
    public static String createServerFilePath(Guild guild) throws IOException {
        String filePath = getServerFilePath(guild);
        FileAccessor.createDirectories(filePath);
        return filePath;
    }
    public static String getServerFilePath(Guild guild) {
        String filePath = FileAccessor.createFilePathString(FOLDER, guild.getId());
        return filePath;
    }
    public static String createInfoFilePath(Guild guild, String infoFilePath) throws IOException {
        String filePath = getInfoFilePath(guild, infoFilePath);
        FileAccessor.createDirectories(filePath);
        return filePath;
    }
    public static String getInfoFilePath(Guild guild, String infoFilePath) {
        String filePath = FileAccessor.createFilePathString(getServerFilePath(guild), infoFilePath);
        return filePath;
    }
}

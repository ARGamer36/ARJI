package Information;

import net.dv8tion.jda.api.entities.Guild;

import java.io.IOException;

public class ServerStorage {
    final private static String FOLDER = "Storage";
    public ServerStorage() {
        try {
            FileAccessor.createDirectories(FOLDER);
        } catch (IOException e) {
            System.out.println("ERROR: FAILED TO CREATE \"Storage\" FOLDER");
        }
    }
    public static String createServerFilePath(Guild guild) throws IOException {
        String filePath = getServerFilePath(guild);
        FileAccessor.createDirectories(filePath);
        return filePath;
    }
    public static String getServerFilePath(Guild guild) {
        String filePath = createFilePathString(FOLDER, guild.getId());
        return filePath;
    }
    public static String createInfoFilePath(Guild guild, String infoFilePath) throws IOException {
        String filePath = getInfoFilePath(guild, infoFilePath);
        FileAccessor.createDirectories(filePath);
        return filePath;
    }
    public static String getInfoFilePath(Guild guild, String infoFilePath) {
        String filePath = createFilePathString(getServerFilePath(guild), infoFilePath);
        return filePath;
    }
    public static String createFilePathString(String parent, String child) {
        return parent + "/" + child;
    }
}

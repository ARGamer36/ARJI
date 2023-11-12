package main;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public abstract class BotDriver { // test
    protected final Dotenv config;
    protected final ShardManager shardManager;
    protected static String VERSION;
    protected static Activity activity;
    protected static String TOKEN;

    protected void setup() {
        VERSION = "0.0";
        activity = null;
        TOKEN = null;
    }
    public static String getVERSION() {
        return VERSION;
    }
    public BotDriver() throws LoginException {
        setup();
        config = Dotenv.configure().load();
        String token = config.get(TOKEN);

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(activity);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_PRESENCES);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.enableCache(CacheFlag.ONLINE_STATUS);

        shardManager = builder.build();
    }
}

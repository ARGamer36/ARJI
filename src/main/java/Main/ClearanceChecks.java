package Main;

import net.dv8tion.jda.api.entities.Member;

public class ClearanceChecks {
    // admin role(s)
    // Tier 1-3 role(s)
    //Idea for change possibly have each command keep track of which roles can
    //and cannot do them
    //might just have admin role(s) and helper role(s) and not multiple tiers
    public static boolean isAdmin(Member member) {
        return true;
    }
}

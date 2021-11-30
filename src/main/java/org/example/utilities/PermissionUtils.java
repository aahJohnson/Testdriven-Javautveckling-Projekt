package org.example.utilities;

import org.example.Profile;
import org.example.enums.Permissions;
import org.example.enums.Resource;

import java.util.HashMap;

public class PermissionUtils {

    private static HashMap<String, Profile> profiles;

    public static void setProfiles(HashMap<String, Profile> profiles) {
        PermissionUtils.profiles = profiles;
    }

    public static Permissions getUserPermissions(String token, Resource resource) {
        for (Profile profile : profiles.values()) {
            if (profile.getToken().equals(token)) {
                return switch (resource) {
                    case ACCOUNT -> profile.getAccountPermissions();
                    case PROVISION_CALC -> profile.getProvisionsCalcPermissions();
                };
            }
        }
        return null;
    }
}
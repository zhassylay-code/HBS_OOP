package auth;

import entity.Role;

public final class Session {
    private static Role role = Role.USER;

    private Session() {}

    public static Role getRole() {
        return role;
    }

    public static void loginUser() {
        role = Role.USER;
    }

    public static void loginAdminOrManager(Role newRole, String password) {
        if (newRole != Role.ADMIN && newRole != Role.MANAGER) {
            throw new IllegalArgumentException("Only ADMIN or MANAGER allowed.");
        }


        if (!"1234".equals(password)) {
            throw new SecurityException("Wrong password");
        }

        role = newRole;
    }

    public static void require(Role... allowed) {
        for (Role r : allowed) {
            if (role == r) return;
        }
        throw new SecurityException("Access denied: " + role);
    }
}

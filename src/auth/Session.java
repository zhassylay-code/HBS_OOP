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
        if (newRole == Role.ADMIN) {
            if (!"admin123!".equals(password)) {
                throw new SecurityException("Wrong admin password");
            }
            role = Role.ADMIN;
            return;
        }

        if (newRole == Role.MANAGER) {
            if (!"manager123!".equals(password)) {
                throw new SecurityException("Wrong manager password");
            }
            role = Role.MANAGER;
            return;
        }

        throw new IllegalArgumentException("Invalid role");
    }

    public static void require(Role... allowed) {
        for (Role r : allowed) {
            if (role == r) return;
        }
        throw new SecurityException("Access denied: " + role);
    }
}

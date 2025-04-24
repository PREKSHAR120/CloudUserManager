import java.util.*;

class User {
    String username;
    String role;
    String group;

    User(String username, String role, String group) {
        this.username = username;
        this.role = role;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Username: " + username + ", Role: " + role + ", Group: " + group;
    }
}

public class CloudUserManager {
    static Map<String, User> users = new HashMap<>();

    public static void createUser(String username, String role, String group) {
        if (users.containsKey(username)) {
            System.out.println("User already exists.");
        } else {
            users.put(username, new User(username, role, group));
            System.out.println("User created successfully.");
        }
    }

    public static void deleteUser(String username) {
        if (users.remove(username) != null) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    public static void listUsers() {
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            users.values().forEach(System.out::println);
        }
    }

    public static void viewUsersByGroup(String groupName) {
        boolean found = false;
        for (User user : users.values()) {
            if (user.group.equals(groupName)) {
                System.out.println(user);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No users found in this group.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Cloud User Manager ---");
            System.out.println("1. Create User");
            System.out.println("2. Delete User");
            System.out.println("3. List All Users");
            System.out.println("4. View Users by Group");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String uname = sc.nextLine();
                    System.out.print("Enter role (admin/user): ");
                    String role = sc.nextLine();
                    System.out.print("Enter group name: ");
                    String group = sc.nextLine();
                    createUser(uname, role, group);
                    break;
                case 2:
                    System.out.print("Enter username to delete: ");
                    String delUser = sc.nextLine();
                    deleteUser(delUser);
                    break;
                case 3:
                    listUsers();
                    break;
                case 4:
                    System.out.print("Enter group name: ");
                    String grp = sc.nextLine();
                    viewUsersByGroup(grp);
                    break;
                case 5:
                    System.out.println("Exiting Cloud User Manager...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);

        sc.close();
    }
}
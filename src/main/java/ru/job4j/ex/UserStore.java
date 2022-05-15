package ru.job4j.ex;

public class UserStore {
    public static User findUser(User[] users, String login) throws UserNotFoundException {
        int rsl = -1;
        try {
            for (User user : users) {
                if (user.getUsername().equals(login)) {
                    rsl = 0;
                    return user;
                }
            }
            if (rsl == -1) {
                throw new UserNotFoundException("User not found");
            }
        } catch (UserNotFoundException e) {
            System.out.println("User not found. Try againe.");
        }
        return null;
    }

    public static boolean validate(User user) throws UserInvalidException {
        try {
            if (!user.isValid() || user.getUsername().length() < 3) {
                throw new UserInvalidException("User is invalid.");
            }
        } catch (UserInvalidException e) {
            System.out.println("User is invalid. You are need another user.");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            User[] users = {
                    new User("Petr Arsentev", true)
            };
            User user = findUser(users, "Petr Arsentev");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserInvalidException e) {
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }
}

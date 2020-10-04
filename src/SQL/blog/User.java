package SQL.blog;

public class User {
    int id;
    String username;
    String nickname;
    // 当前的登录用户信息
    // 没有登录 user == null
    // 否则，指向具体的用户对象
    private static User currentUser = null;
    public static void login(User user){
        currentUser = user;
        System.out.println("当前登录的用户是："+currentUser);
    }
    public static User getCurrentUser(){
        return currentUser;
    }

    public static boolean isLogined() {
       return currentUser != null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

//package mk.ukim.finki.vinodventuraapp.repository.inmem;
//
//import mk.ukim.finki.vinodventuraapp.bootstrap.DataHolder;
//import mk.ukim.finki.vinodventuraapp.model.User;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public class InMemoryUserRepository {
//    public Optional<User> findByUsername(String username) {
//        return DataHolder.users.stream().filter(v -> v.getUsername().equals(username)).findFirst();
//    }
//
//    public Optional<User> findByUsernameAndPassword(String username, String password) {
//        return DataHolder.users.stream().filter(v -> v.getUsername().equals(username)
//                && v.getPassword().equals(password)).findFirst();
//    }
//
//    public User saveOrUpdate(User user) {
//        DataHolder.users.removeIf(u -> u.getUsername().equals(user.getUsername()));
//        DataHolder.users.add(user);
//        return user;
//    }
//
//    public void delete(String username) {
//        DataHolder.users.removeIf(v -> v.getUsername().equals(username));
//    }
//}
//

//package com.example.userManagementApi;
//
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//
//
//import com.example.userManagementApi.model.User;
//import com.example.userManagementApi.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//
//import javax.swing.plaf.OptionPaneUI;
//import java.util.Optional;
//import java.util.List;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DataJpaTest
//// loads only a slice of the spring context relevant to Jpa
//public class UserRepositoryTest {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private TestEntityManager entityManager;
//    private User user1;
//    private User user2;
//
//    @BeforeEach
//    void setUp(){
//        userRepository.deleteAllInBatch();
//        user1 =new User("Alice Wonderland","alice@example.com");
//        user2=new User("Bob The Builder", "bob@example.com");
//
//        entityManager.persist(user1);
//        entityManager.persist(user2);
//        entityManager.flush();// execute all unexecuted sql commands immediately
//        entityManager.clear();
//    }
//    @Test
//    void testFindByEmailFound(){
//        Optional<User> foundUser = userRepository.findByEmail(user1.getEmail());
//        assertThat(foundUser).isPresent();
//        assertThat(foundUser.get().getName()).isEqualTo(user1.getName());
//        assertThat(foundUser.get().getEmail()).isEqualTo(user1.getEmail());
//        assertThat(foundUser.get().getId()).isEqualTo(user1.getId());
//    }
//
//    @Test
//    void testEmailNotFound(){
//        Optional<User> foundUser = userRepository.findByEmail("nonexistinguser@demo.com");
//        assertThat(foundUser).isNotPresent();
//    }
//
//    @Test
//    void testSaveUser(){
//        User newUser = new User("Charlie Chaplin","charlie@example.com");
//        User saveduser =userRepository.save(newUser);
//        assertThat(saveduser).isNotNull();
//        assertThat(saveduser.getId()).isNotNull();
//        assertThat(saveduser.getName()).isEqualTo("Charlie Chaplin");
//        assertThat(saveduser.getEmail()).isEqualTo("charlie@example.com");
//
//
//    }
//    @Test
//    void testUpdateUser(){
//        String newName = "hhhh";
//        String newEmail = "hhhh@h.com";
//        User user3 = new User("gggg","ggggg@g.com");
//        user3.setName(newName);
//        user3.setEmail(newEmail);
//        User updatedUser=userRepository.save(user3);
//
//        assertThat(updatedUser.getName()).isEqualTo(newName);
//        assertThat(updatedUser.getEmail()).isEqualTo(newEmail);
//    }
//
//    @Test
//    void testDeleteUser(){
//        long before = userRepository.count();
//       userRepository.deleteById(user1.getId());
//        Optional<User> found = userRepository.findById(user1.getId());
//        long after = userRepository.count();
//        assertThat(found).isNotPresent();
//        assertThat(after).isEqualTo(before-1);
//    }
//
//
//
//
////    void testDeleteUser(){
////        User deleteduser =userRepository.deleteAll();
////        assertThat(saveduser).isNotNull();
////        assertThat(saveduser.getId()).isNotNull();
////        assertThat(saveduser.getName()).isEqualTo("Charlie Chaplin");
////        assertThat(saveduser.getEmail()).isEqualTo("charlie@example.com");
////
////
////    }
//
//
//}

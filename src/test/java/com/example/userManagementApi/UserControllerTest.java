package com.example.userManagementApi;
import com.example.userManagementApi.model.User;
import com.example.userManagementApi.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = UserManagementApiApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;
    private List<User> manyUsers;
    private long initialUserCount;
    @BeforeEach
    void setUp(){
    userRepository.deleteAll();

    objectMapper.registerModule(new JavaTimeModule());
    User initialUser1 = new User("John Doe","John.doe@example.com");
    User initialUser2 = new User("Yogesh","yogeshtheboy17@gmail.com");
    List<User> savedInitialUsers= userRepository.saveAll(Arrays.asList(initialUser1,initialUser2));

    this.user1=savedInitialUsers.get(0);
    this.user2=savedInitialUsers.get(1);

         manyUsers = IntStream.rangeClosed(1, 25).mapToObj(i -> new User("User " + i, "user" + i + "@example.com"))
                .collect(Collectors.toList());
         userRepository.saveAll(manyUsers);
        this.initialUserCount = userRepository.count();
    }
    @Test
    void testGetUserByIdFound()throws Exception{
        mockMvc.perform(get("/api/users/{id}", user1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(user1.getId().intValue())))
                .andExpect(jsonPath("$.name",is(user1.getName())))
                .andExpect(jsonPath("$.email",is(user1.getEmail())));
    }
    @Test
    void testCreateUsers() throws Exception{
        User newuser1 = new User("Alice Brown","alice.brown@example.com");
        User newuser2 = new User("Mag Brown","mag.brown@example.com");
        List<User> newUsers = Arrays.asList(newuser1,newuser2);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUsers)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id",notNullValue()))
                .andExpect(jsonPath("$[0].name",is("Alice Brown")));
    }
    @Test
    void testDeleteUserFound() throws Exception {
        mockMvc.perform(delete("/api/users/{id}", user1.getId()))
                .andExpect(status().isNoContent());

        assertFalse(userRepository.existsById(user1.getId()));
        assertEquals(initialUserCount - 1, userRepository.count());
    }

    @Test
    void testDeleteUserNotFound() throws Exception {
        Long nonExistentId = user1.getId() + 1000;

        mockMvc.perform(delete("/api/users/{id}", nonExistentId))
                .andExpect(status().isNotFound());

        assertEquals(initialUserCount, userRepository.count());
    }



}

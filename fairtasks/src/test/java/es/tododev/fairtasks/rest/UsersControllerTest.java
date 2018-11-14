package es.tododev.fairtasks.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.tododev.fairtasks.dto.User;
 


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class UsersControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void findUser() throws Exception {
		mockMvc.perform(get("/users/{email}", new Object[] {"boy@test.com"})).andExpect(status().isOk()).andExpect(jsonPath("$.email", equalTo("boy@test.com"))).andExpect(jsonPath("$.userName", equalTo("boy")));
	}
	
	@Test
	public void insertGetModifyDelete() throws Exception {
		User user = new User("userName", "email@email.com");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(user);
		mockMvc.perform(post("/users").content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		mockMvc.perform(put("/users/{email}", new Object[] {user.getEmail()}).content(json)).andExpect(status().isOk());
		mockMvc.perform(delete("/users/{email}", new Object[] {user.getEmail()}).content(json)).andExpect(status().isOk());
	}
	
}

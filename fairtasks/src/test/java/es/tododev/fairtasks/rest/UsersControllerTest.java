package es.tododev.fairtasks.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.tododev.fairtasks.dto.User;
 


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user", roles = "USER")
public class UsersControllerTest {

	
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before()
	public void setup(){
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();  
	}
	
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
		mockMvc.perform(delete("/users/{email}", new Object[] {user.getEmail()}).content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}
	
}

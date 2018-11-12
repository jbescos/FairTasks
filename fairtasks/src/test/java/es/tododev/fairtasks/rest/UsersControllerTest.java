package es.tododev.fairtasks.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
 


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class UsersControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void findUser() throws Exception {
		mockMvc.perform(get("/users/{email}", new Object[] {"boy@test.com"}))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.email", equalTo("boy@test.com")))
		.andExpect(jsonPath("$.userName", equalTo("boy")));
	}
	
}

package es.tododev.fairtasks.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

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

import es.tododev.fairtasks.dto.Task;
 


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "email@email.com", roles = "USER")
public class TasksControllerTest {

	
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before()
	public void setup(){
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();  
	}
	
	@Test
	public void insertGetModifyDelete() throws Exception {
		Task task = new Task("test task", "test group", 100, "description");
		ObjectMapper mapper = new ObjectMapper();
		String taskJson = mapper.writeValueAsString(task);
		String tasksJson = mapper.writeValueAsString(Arrays.asList(task));
		mockMvc.perform(post("/tasks").content(taskJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
		mockMvc.perform(get("/tasks/{groupName}", new Object[] {task.getGroup()}).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andExpect(content().json(tasksJson));
		mockMvc.perform(delete("/tasks/{groupName}/{task}", new Object[] {task.getGroup(), task.getTask()}).content(tasksJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
		mockMvc.perform(get("/tasks/{groupName}", new Object[] {task.getGroup()}).accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andExpect(content().json(mapper.writeValueAsString(Arrays.asList())));
	}
	
}

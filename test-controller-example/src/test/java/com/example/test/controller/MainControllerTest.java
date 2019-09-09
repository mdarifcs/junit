package com.example.test.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
public class MainControllerTest {
	
	private MockMvc mockMvc;

	@InjectMocks
	private MainController mainController;
	
	@Before
	public void setUp() throws Exception{
		mockMvc = MockMvcBuilders.standaloneSetup(mainController)
				.build();
	}
	
	@Test
	public void testHelloWorldJson() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/test")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", Matchers.is(1)))
			.andExpect(jsonPath("$.name", Matchers.is("Arif")))
			.andExpect(jsonPath("$.address", Matchers.is("Delhi")))
			.andExpect(jsonPath("$.*", Matchers.hasSize(1)));
	}
	
}

package com.moon.studentplatform.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    private MockMvc mvc;

   /* @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new HalloController()).build();
    }*/

    /*@Test
    public void getHallo() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.equalTo("Hallo!")));
    }
*/
    @Test
    public void contextLoads() {
    }

}

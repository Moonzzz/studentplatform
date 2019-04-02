package com.moon.studentplatform;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureMockMvc
@WebAppConfiguration
public class WebTest extends BasicTest {
    protected MockMvc mvc;

    @Autowired
    protected WebApplicationContext context;

    @Before
    public void setMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}

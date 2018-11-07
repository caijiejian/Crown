package org.crown.controller.rest;

import org.crown.CrownApplication;
import org.crown.common.kit.JacksonUtils;
import org.crown.model.parm.LoginPARM;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * <p>
 * AccountRestControllerTest
 * </p>
 *
 * @author Caratacus
 * @date 2018/11/7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CrownApplication.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class AccountRestControllerTest {

    @Autowired
    private AccountRestController accountRestController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountRestController).build();
    }

    @Test
    public void getToken() throws Exception {
        LoginPARM loginPARM = new LoginPARM();
        loginPARM.setLoginName("crown");
        loginPARM.setPassword("crown");
        String responseString = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/account/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JacksonUtils.toJson(loginPARM)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }

    @Test
    public void removeToken() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/account/token"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

}
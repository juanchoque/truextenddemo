package com.truextend.dev.recipes.controllers;

import com.truextend.dev.recipes.model.Accounts;
import com.truextend.dev.recipes.security.JwtGenerator;
import com.truextend.dev.recipes.services.AccountsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@WebMvcTest(value=InitSecurityController.class,secure = false)
public class InitSecurityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountsService accountsService;

    @MockBean
    private JwtGenerator jwtGenerator;

    @Test
    public void createAccount() throws Exception {
        Accounts accounts = new Accounts();

        String inputInJson = "{\n" +
                "\t\"email\":\"juanchoque13026@gmail.com\",\n" +
                "\t\"password\":\"1234\"\n" +
                "}";

        String URI = "/token/verify";

        HashMap hashMap = new HashMap();

        Mockito.when(accountsService.getAccountsByEmailAndPass(Mockito.any(Accounts.class))).thenReturn(hashMap);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        //assertThat(outputInJson).isEqualTo(inputInJson);
        //assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void verifyAccount()throws Exception {

    }
}
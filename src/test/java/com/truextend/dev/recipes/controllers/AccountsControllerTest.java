package com.truextend.dev.recipes.controllers;

import com.truextend.dev.recipes.model.Accounts;
import com.truextend.dev.recipes.model.Recipes;
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

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest(value=AccountsController.class,secure = false)
public class AccountsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountsService accountsService;

    @Test
    public void listAccounts() {
    }

    @Test
    public void addAccounts() {
    }

    @Test
    public void getAccounts() throws Exception {
        Accounts accounts = new Accounts();

        String inputInJson = "{\n" +
                "\t\"email\":\"juanchoque13026@gmail.com\",\n" +
                "\t\"password\":\"1234\"\n" +
                "}";

        String URI = "/recipes/add";

        HashMap hashMap = new HashMap();

        Mockito.when(this.accountsService.saveOrUpdateAccounts(any(Accounts.class))).thenReturn(hashMap);

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
    public void deleteAccounts() {
    }
}
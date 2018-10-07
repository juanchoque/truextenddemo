package com.truextend.dev.recipes.controllers;

import com.truextend.dev.recipes.model.Accounts;
import com.truextend.dev.recipes.model.Recipes;
import com.truextend.dev.recipes.security.JwtValidator;
import com.truextend.dev.recipes.services.RecipesService;
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

import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest(value=RecipesController.class,secure = false)
public class RecipesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipesService recipesService;

    @MockBean
    private JwtValidator jwtValidator;

    @Test
    public void listRecipes() {
    }

    @Test
    public void addRecipes() throws Exception {
        Recipes recipes = new Recipes();

        String inputInJson = "{\n" +
                "\t\"email\":\"juanchoque13026@gmail.com\",\n" +
                "\t\"password\":\"1234\"\n" +
                "}";

        String URI = "/recipes/add";

        HashMap hashMap = new HashMap();

        Mockito.when(this.recipesService.saveOrUpdateRecipes(any(), any(Recipes.class))).thenReturn(hashMap);

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
    public void getRecipes() {
    }

    @Test
    public void deleteRecipes() {
    }
}
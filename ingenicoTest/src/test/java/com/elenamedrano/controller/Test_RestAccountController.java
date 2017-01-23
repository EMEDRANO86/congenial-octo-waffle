package com.elenamedrano.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import beans.AccountHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RestTestContext.class})
public class Test_RestAccountController {
	private MockMvc mockMvc;
	 
    @Mock
    private AccountHandler accountHandlerMock; 
    
    @Autowired 
    private WebApplicationContext ctx;
    
    @Before 
    public void setUp() {
    	accountHandlerMock = Mockito.mock(AccountHandler.class);
    	Mockito.reset(accountHandlerMock);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }
    
    @Test
    public void createAccount_ShouldReturnAccountCreated() throws Exception {
        MvcResult result = mockMvc.perform(get("/account/accountId/1/balance/200"))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();
        
        String content = result.getResponse().getContentAsString();
        System.out.println(content);
    }
    
    @Test
    public void createAccount_ShouldReturnBadRequest() throws Exception {
        MvcResult result = mockMvc.perform(get("/accountId/1/balance/200"))
                .andExpect(status().isNotFound())
                .andDo(print()).andReturn();
        
        String content = result.getResponse().getContentAsString();
        System.out.println(content);
    }
    
    @Test
    public void transferAmount_ShouldReturnBadRequest() throws Exception {
        MvcResult result = mockMvc.perform(get("transfer/sourceAccountId/1/targetAccountId/2/amount/200"))
                .andExpect(status().isNotFound())
                .andDo(print()).andReturn();
        
        String content = result.getResponse().getContentAsString();
        System.out.println(content);
    }
    
    
}

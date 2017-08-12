package rest.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rrr.core.models.entities.Receipt;
import rrr.core.services.ReceiptService;
import rrr.rest.mvc.ReceiptController;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
/**
 * Created by Brandon Paw on 7/15/2017.
 */
public class ReceiptControllerTest {

    @InjectMocks
    private ReceiptController controller;

    @Mock
    private ReceiptService service;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getExistingReceipt() throws Exception {
        Receipt receipt = new Receipt();
        receipt.setId(1L);
        receipt.setReceipt("Von's");

        when(service.findReceipt(1L)).thenReturn(receipt);

        mockMvc.perform(get("/rest/receipt/1"))
                .andDo(print())
                .andExpect(jsonPath("$.receipt", is(receipt.getReceipt())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/receipt/1"))));
    }

    @Test
    public void getNonExistingReceipt() throws Exception {
        when(service.findReceipt(1L)).thenReturn(null);

        mockMvc.perform(get("/rest/receipt/1")).andExpect(status().isNotFound());
    }
}


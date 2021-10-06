package ar.edu.unq.ttip.alec.backend.webservices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import ar.edu.unq.ttip.alec.backend.service.FrontUserService;
import ar.edu.unq.ttip.alec.backend.service.util.JwtUtil;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class TaxControllerTests {
	@Autowired
    private FrontUserService userservice;
	@Autowired
    private JwtUtil jwtTokenUtil;
	@Autowired
	private MockMvc mvc;
	private String token;
	
	@BeforeAll
	public void setUp() {
		final UserDetails userDetails = userservice.loadUserByUsername("user@alec.com");
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        this.token = "Bearer " + jwt;
	}
	
	// Taxlist tests
	@Test
	void whenNotAuthenticated_thenGetAllTaxReturns403() throws Exception {
		mvc.perform(get("/tax"))
	    	.andExpect(status().isForbidden());
	}
	
	@Test
	void whenValidRequest_thenGetAllTaxReturnsStatusCode200() throws Exception {
		mvc.perform(get("/tax").header("Authorization", this.token))
	    	.andExpect(status().isOk());
	}
	
	@Test
	void whenValidRequest_thenGetAllTaxReturnsTheTaxesAsJSON() throws Exception {     
        MvcResult mvcResult = mvc.perform(get("/tax").header("Authorization", this.token))
	    	.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	    	.andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        
        assertThat(responseBody).contains("IVA");
        assertThat(responseBody).contains("Pais");
	}
}

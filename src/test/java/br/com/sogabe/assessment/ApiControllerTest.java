package br.com.sogabe.assessment;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.sogabe.assessment.model.Person;
import br.com.sogabe.assessment.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;

    @Before
    public void init() {
    	Person person = new Person(1L, "Person");
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        
        List<Person> people = Arrays.asList(
                new Person(1L, "Person 1"),
                new Person(2L, "Person 2"));
        when(personRepository.findAll()).thenReturn(people);
    }
    
    @Test
    public void allPerson() throws Exception {
        mockMvc.perform(get("/api/people/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].name", is("Person 1")))
            .andExpect(jsonPath("$[1].id", is(2)))
            .andExpect(jsonPath("$[1].name", is("Person 2")));

        verify(personRepository, times(1)).findAll();
    }
    
    @Test
    public void findPerson() throws Exception {
        mockMvc.perform(get("/api/people/1"))
    		.andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.name", is("Person")));

        verify(personRepository, times(1)).findById(1L);
    }
	
}

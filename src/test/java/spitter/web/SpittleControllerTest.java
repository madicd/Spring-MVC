package spitter.web;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;
import spitter.Spittle;
import spitter.data.SpittleRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class SpittleControllerTest {
    @Test
    public void shouldShowRecentSpittles() throws Exception {
        List<Spittle> expectedSpittles = createSpittleList(20);
        SpittleRepository mockRepository = mock(SpittleRepository.class);
        when(mockRepository.findSpittles(Long.MAX_VALUE, 20))
                .thenReturn(expectedSpittles);

        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
                .build();

        mockMvc.perform(get("/spittles"))
                .andExpect(view().name("spittles"))
                .andExpect(model().attributeExists("spittleList"))
                .andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));

    }

    @Test
    public void shouldShowPagedSpittles() throws Exception {
        List<Spittle> expectedSpittles = createSpittleList(50);
        SpittleRepository mockRepository = mock(SpittleRepository.class);
        when(mockRepository.findSpittles(238900, 50))
                .thenReturn(expectedSpittles);

        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
                .build();

        mockMvc.perform(get("/spittles?maxId=238900&count=50"))
                .andExpect(view().name("spittles"))
                .andExpect(model().attributeExists("spittleList"))
                .andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));

    }

    @Test
    public void givenIdShouldShowSpittle() throws Exception {
        final Spittle expectedSpittle = new Spittle("Hello everyone!", new Date());
        final long spittleId = 1234L;
        expectedSpittle.setId(spittleId);

        final SpittleRepository mockRepository = mock(SpittleRepository.class);
        when(mockRepository.findOne(spittleId))
                .thenReturn(expectedSpittle);

        final SpittleController controller = new SpittleController(mockRepository);
        final MockMvc mockMvc = standaloneSetup(controller).build();

        final String urlTemplate = String.format("/spittles/%s", spittleId);
        mockMvc.perform(get(urlTemplate))
                .andExpect(view().name("spittle"))
                .andExpect(model().attributeExists("spittle"))
                .andExpect(model().attribute("spittle", expectedSpittle));

    }


    private List<Spittle> createSpittleList(int listSize) {
        return IntStream.range(0, listSize)
                .mapToObj(index -> new Spittle("Spittle " + index, new Date()))
                .collect(toList());
    }

}
package au.com.mm.codingChallenge.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProcessEventsServiceImplTest {

    @InjectMocks
    private ProcessEventsService processEventsService;

    @Mock
    EventFactoryService eventFactoryService;


    @Before
    public void setUp()
    {
        processEventsService = ProcessEventsServiceImpl.getInstance();
        eventFactoryService = EventFactoryServiceImpl.getInstance();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcessEventsForGivenTimestamp() {

    }


}

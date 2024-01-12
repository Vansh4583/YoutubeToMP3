package com.example.ytmp3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class VideoProcessorTest {

    public VideoProcessor vp;
    MockHttpServletResponse mockResponse;


    @BeforeEach
    public void setup() {
        vp = new VideoProcessor();
        mockResponse = new MockHttpServletResponse();

    }

	@Test
	void testVideoToMp3ValidUrl() {
        int exitcode = vp.videoToMp3("https://youtu.be/vInqDBhyZI8?si=q2Gq7i1JOisULAJv", mockResponse);
        assertEquals(0,exitcode);

	}
    @Test
    void testVideoToMp3InvalidUrl() {
        int exitcode = vp.videoToMp3("sdocmwodcmsdiopcms", mockResponse);
        assertNotEquals(0,exitcode);

    }

    @Test
    void testVideoToMp3ValidUrlMultipleTimes() {
        int exitcode = vp.videoToMp3("https://youtu.be/vInqDBhyZI8?si=q2Gq7i1JOisULAJv", mockResponse);
        assertEquals(0,exitcode);

        int exitcode1 = vp.videoToMp3("https://youtu.be/vInqDBhyZI8?si=q2Gq7i1JOisULAJv", mockResponse);
        assertEquals(0,exitcode1);

        int exitcode2 = vp.videoToMp3("https://youtu.be/vInqDBhyZI8?si=q2Gq7i1JOisULAJv", mockResponse);
        assertEquals(0,exitcode2);

    }

}

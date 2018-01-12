package com.engineers.united.unitedengineers;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class SongTest {

    private Song mSong;

    @Before
    public void setUp() throws Exception{
        mSong = new Song();
    }

    @Test
    public void test_GetId() throws Exception{
        mSong.setId("3");
        assertTrue(mSong.getId() == "3");
    }

    @Test
    public void test_SetId() throws Exception{
        mSong.setId("3");
        assertEquals("3" , mSong.getId());
    }

    @Test
    public void test_GetArtist() throws Exception{
       mSong.setArtist("Earthmind");
       assertTrue(mSong.getArtist() == "Earthmind");
    }

    @Test
    public void test_SetArtist() throws Exception{
        mSong.setArtist("Earthmind");
        assertEquals("Earthmind", mSong.getArtist());
    }

    @Test
    public void test_GetTitle() throws Exception{
        mSong.setTitle("Another Heaven");
        assertTrue(mSong.getTitle()=="Another Heaven");
    }

    @Test
    public void test_SetTitle() throws Exception{
        mSong.setTitle("Another Heaven");
        assertEquals("Another Heaven", mSong.getTitle());
    }
    @Test
    public void test_GetData() throws Exception{
        mSong.setData("123456");
        //negative assertion
        assertFalse(mSong.getData() == "12345");
        //positive assertion
        assertTrue(mSong.getData() == "123456");
    }
    @Test
    public void test_SetData() throws Exception{
        mSong.setData("123456");
        //negative assertion
        assertNotEquals("12345", mSong.getData());
        //positive assertion
        assertEquals("123456", mSong.getData());
    }
    @Test
    public void test_GetDisplayName() throws Exception{
        mSong.setDisplayName("Another Heaven");
        assertTrue(mSong.getDisplayName()=="Another Heaven");
    }
    @Test
    public void test_SetDisplayName() throws Exception{
        mSong.setDisplayName("Another Heaven - Earthmind");
        assertEquals("Another Heaven - Earthmind", mSong.getDisplayName());

    }
    @Test
    public void test_GetDuration() throws Exception{
        mSong.setDuration("5:13");
        assertTrue(mSong.getDuration()=="5:13");
    }
    @Test
    public void test_SetDuration() throws Exception{
        mSong.setDuration("5:13");
        assertEquals("5:13", mSong.getDuration());
    }

}

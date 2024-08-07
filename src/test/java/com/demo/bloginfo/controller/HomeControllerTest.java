package com.demo.bloginfo.controller;

import com.demo.bloginfo.entity.BlogInfo;
import com.demo.bloginfo.service.BlogInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

    @Mock
    private BlogInfoService blogInfoService;

    @InjectMocks
    private HomeController homeController;

    @Test
    public void testGetAllBlogs() {
        BlogInfo blog1 = new BlogInfo(1L, "Blog1", "Author1", "Description1", LocalDate.now());
        BlogInfo blog2 = new BlogInfo(2L, "Blog2", "Author2", "Description2", LocalDate.now());

        when(blogInfoService.getAllBlogs()).thenReturn(List.of(blog1, blog2));

        ResponseEntity<List<BlogInfo>> response = blogInfoController.getAllBlogs();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetBlogById() {
        BlogInfo blog = new BlogInfo(1L, "Blog1", "Author1", "Description1", LocalDate.now());

        when(blogInfoService.getBlogById(1L)).thenReturn(Optional.of(blog));

        ResponseEntity<BlogInfo> response = blogInfoController.getBlogById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Blog1", response.getBody().getBlogName());
    }

    @Test
    public void testCreateBlog() {
        BlogInfo blog = new BlogInfo(1L, "Blog1", "Author1", "Description1", LocalDate.now());

        when(blogInfoService.createBlog(blog)).thenReturn(blog);

        ResponseEntity<BlogInfo> response = blogInfoController.createBlog(blog);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Blog1", response.getBody().getBlogName());
    }

    @Test
    public void testDeleteBlog() {
        doNothing().when(blogInfoService).deleteBlog(1L);

        ResponseEntity<Void> response = blogInfoController.deleteBlog(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(blogInfoService, times(1)).deleteBlog(1L);
    }
}


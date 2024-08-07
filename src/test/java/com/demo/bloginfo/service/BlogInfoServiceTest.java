package com.demo.bloginfo.service;

import com.demo.bloginfo.entity.BlogInfo;
import com.demo.bloginfo.repository.BlogInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BlogInfoServiceTest {

    @Mock
    private BlogInfoRepository blogInfoRepository;

    @InjectMocks
    private BlogInfoService blogInfoService;

    @Test
    public void testGetAllBlogs() {
        BlogInfo blog1 = new BlogInfo(1L, "Blog1", "Author1", "Description1", LocalDate.now());
        BlogInfo blog2 = new BlogInfo(2L, "Blog2", "Author2", "Description2", LocalDate.now());

        when(blogInfoRepository.findAll()).thenReturn(List.of(blog1, blog2));

        List<BlogInfo> blogs = blogInfoService.getAllBlogs();

        assertNotNull(blogs);
        assertEquals(2, blogs.size());
    }

    @Test
    public void testGetBlogById() {
        BlogInfo blog = new BlogInfo(1L, "Blog1", "Author1", "Description1", LocalDate.now());

        when(blogInfoRepository.findById(1L)).thenReturn(Optional.of(blog));

        Optional<BlogInfo> retrievedBlog = blogInfoService.getBlogById(1L);

        assertTrue(retrievedBlog.isPresent());
        assertEquals("Blog1", retrievedBlog.get().getBlogName());
    }

    @Test
    public void testCreateBlog() {
        BlogInfo blog = new BlogInfo(1L, "Blog1", "Author1", "Description1", LocalDate.now());

        when(blogInfoRepository.save(blog)).thenReturn(blog);

        BlogInfo createdBlog = blogInfoService.createBlog(blog);

        assertNotNull(createdBlog);
        assertEquals("Blog1", createdBlog.getBlogName());
    }

    @Test
    public void testDeleteBlog() {
        doNothing().when(blogInfoRepository).deleteById(1L);

        blogInfoService.deleteBlog(1L);

        verify(blogInfoRepository, times(1)).deleteById(1L);
    }
}

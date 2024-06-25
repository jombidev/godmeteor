package dev.jombi.godmeteor.communtiy.JJE.service;

import dev.jombi.godmeteor.communtiy.SYS.dto.PostDto;

import java.util.List;

public interface PostService {
    public void savePost(PostDto postDto);
    public List<PostDto> getPostList(Integer pageNum);
    public PostDto getPost(Long id);
    public void deletePost(Long id);
    public List<PostDto> searchPosts(String keyword);
    public void update(Long id, PostDto postDto);
}

package dev.jombi.godmeteor.communtiy.JJE.service;

import dev.jombi.godmeteor.communtiy.SYS.dto.PostRequestDto;

import java.util.List;

public interface PostService {
    public void savePost(PostRequestDto postDto);
    public List<PostRequestDto> getPostList(Integer pageNum);
    public PostRequestDto getPost(Long id);
    public void deletePost(Long id);
    public List<PostRequestDto> searchPosts(String keyword);
    public void update(Long id, PostRequestDto postDto);
}

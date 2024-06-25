package dev.jombi.godmeteor.communtiy.JJE.service;

import dev.jombi.godmeteor.communtiy.SYS.dto.PostRequestDto;
import dev.jombi.godmeteor.communtiy.SYS.dto.PostResponseDto;

import java.util.List;

public interface PostService {
    void savePost(PostRequestDto postDto);
    List<PostResponseDto> getPostList();
    PostResponseDto getPost(Long id);
    void deletePost(Long id);
    List<PostResponseDto> searchPosts(String keyword);
    void update(Long id, PostRequestDto postDto);
}

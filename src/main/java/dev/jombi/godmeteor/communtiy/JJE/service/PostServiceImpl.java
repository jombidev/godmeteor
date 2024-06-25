package dev.jombi.godmeteor.communtiy.JJE.service;

import dev.jombi.godmeteor.communtiy.JJE.entity.Post;
import dev.jombi.godmeteor.communtiy.SYS.dto.PostRequestDto;
import dev.jombi.godmeteor.communtiy.SYS.dto.PostResponseDto;
import dev.jombi.godmeteor.communtiy.SYS.repository.PostRepository;
import dev.jombi.godmeteor.communtiy.exception.PostExceptionCode;
import dev.jombi.godmeteor.global.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public void savePost(PostRequestDto postRequestDto) {
        postRepository.save(postRequestDto.toEntity());
    }

    @Override
    public List<PostResponseDto> getPostList() {

        List<Post> all = postRepository.findAll();
        List<PostResponseDto> postRequestDtoList = new ArrayList<>();

        for (Post post : all) {
            PostResponseDto postDto = PostResponseDto.builder()
                    .id(post.getId())
                    .createdAt(post.getCreatedAt())
                    .updatedAt(post.getUpdatedAt())
                    .title(post.getTitle())
                    .contents(post.getContents())
                    .writer(post.getWriter())
                    .build();

            postRequestDtoList.add(postDto);
        }
        return postRequestDtoList;
    }

    @Override
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(PostExceptionCode.POST_NOT_FOUND).setFormats(id.toString()));

        return PostResponseDto.builder()
                .id(post.getId())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .title(post.getTitle())
                .contents(post.getContents())
                .writer(post.getWriter())
                .build();
    }

    @Transactional
    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<PostResponseDto> searchPosts(String keyword) {
        List<Post> posts = postRepository.findByTitleContaining(keyword);
        List<PostResponseDto> postList = new ArrayList<>();

        for (Post post : posts) {
            PostResponseDto build = PostResponseDto.builder()
                    .id(post.getId())
                    .createdAt(post.getCreatedAt())
                    .updatedAt(post.getUpdatedAt())
                    .title(post.getTitle())
                    .contents(post.getContents())
                    .writer(post.getWriter())
                    .build();

            postList.add(build);
        }

        return postList;
    }

    @Transactional
    @Override
    public void update(Long id, PostRequestDto postDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(PostExceptionCode.POST_NOT_FOUND).setFormats(id.toString()));

        post.updatePost(postDto.getWriter(), postDto.getTitle(), postDto.getContents());
    }
}

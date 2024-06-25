package dev.jombi.godmeteor.communtiy.SYS.controller;

import dev.jombi.godmeteor.communtiy.JJE.entity.Post;
import dev.jombi.godmeteor.communtiy.SYS.dto.PostRequestDto;
import dev.jombi.godmeteor.communtiy.SYS.dto.PostResponseDto;
import dev.jombi.godmeteor.communtiy.SYS.repository.PostRepository;
import dev.jombi.godmeteor.communtiy.JJE.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostApiController {

    private final PostServiceImpl postService;
    private final PostRepository postRepository;

    @PostMapping
    public PostResponseDto savePost(@RequestBody PostRequestDto request) {
        postService.savePost(request);

        return new PostResponseDto(
                request.ToEntity().getTitle(),
                request.ToEntity().getWriter(),
                request.ToEntity().getContents()
        );
    }

    @PutMapping("/{id}")
    public PostResponseDto updatePost(@PathVariable("id") Long id, @RequestBody PostRequestDto request) {
        postService.update(id, request);
        Optional<Post> findPost = postRepository.findById(id);
        Post post = findPost.get();

        return new PostResponseDto(
                post.getTitle(),
                post.getWriter(),
                post.getContents()
        );
    }

    @GetMapping("/list")
    public List<PostRequestDto> findPosts() {
        List<Post> findAll = postRepository.findAll();
        List<PostRequestDto> allPost = new ArrayList<>();

        for (Post post : findAll) {
            PostRequestDto build = PostRequestDto.builder()
                    .contents(post.getContents())
                    .writer(post.getWriter())
                    .title(post.getTitle())
                    .build();

            allPost.add(build);
        }

        return allPost;
    }

    @GetMapping("/{id}")
    public PostResponseDto findPost(@PathVariable("id") Long id) {
        PostRequestDto post = postService.getPost(id);

        return new PostResponseDto(
                post.getWriter(),
                post.getTitle(),
                post.getContents()
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        postService.deletePost(id);
    }
}

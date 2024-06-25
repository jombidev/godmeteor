package dev.jombi.godmeteor.communtiy.SYS.controller;

import dev.jombi.godmeteor.communtiy.JJE.entity.Post;
import dev.jombi.godmeteor.communtiy.SYS.dto.PostRequestDto;
import dev.jombi.godmeteor.communtiy.SYS.dto.PostResponseDto;
import dev.jombi.godmeteor.communtiy.SYS.repository.PostRepository;
import dev.jombi.godmeteor.communtiy.JJE.service.PostServiceImpl;
import dev.jombi.godmeteor.global.response.Response;
import dev.jombi.godmeteor.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseData<PostResponseDto>> savePost(@RequestBody PostRequestDto request) {
        postService.savePost(request);

        PostResponseDto postResponseDto = new PostResponseDto(
                request.ToEntity().getTitle(),
                request.ToEntity().getWriter(),
                request.ToEntity().getContents()
        );

        return ResponseData.ok("post save", postResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<PostResponseDto>> updatePost(@PathVariable("id") Long id, @RequestBody PostRequestDto request) {
        postService.update(id, request);
        Optional<Post> findPost = postRepository.findById(id);
        Post post = findPost.get();

        PostResponseDto postResponseDto = new PostResponseDto(
                post.getTitle(),
                post.getWriter(),
                post.getContents()
        );

        return ResponseData.ok("post update", postResponseDto);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<PostRequestDto>>> findPosts() {
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

        return ResponseData.ok("post find", allPost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<PostResponseDto>> findPost(@PathVariable("id") Long id) {
        PostRequestDto post = postService.getPost(id);

        PostResponseDto postResponseDto =  new PostResponseDto(
                post.getWriter(),
                post.getTitle(),
                post.getContents()
        );

        return ResponseData.ok("id find", postResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return Response.ok("삭제 성공");
    }
}

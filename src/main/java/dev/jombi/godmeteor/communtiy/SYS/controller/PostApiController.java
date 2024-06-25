package dev.jombi.godmeteor.communtiy.SYS.controller;

import dev.jombi.godmeteor.communtiy.JJE.service.PostService;
import dev.jombi.godmeteor.communtiy.SYS.dto.PostRequestDto;
import dev.jombi.godmeteor.communtiy.SYS.dto.PostResponseDto;
import dev.jombi.godmeteor.global.response.Response;
import dev.jombi.godmeteor.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Response> savePost(@RequestBody PostRequestDto request) {
        postService.savePost(request);

        return Response.ok("post saved.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updatePost(@PathVariable("id") Long id, @RequestBody PostRequestDto request) {
        postService.update(id, request);

        return Response.ok("post updated.");
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<PostResponseDto>>> findPosts() {
        List<PostResponseDto> allPosts = postService.getPostList();

        return ResponseData.ok("post found", allPosts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<PostResponseDto>> findPost(@PathVariable("id") Long id) {
        PostResponseDto post = postService.getPost(id);

        return ResponseData.ok("one post found", post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return Response.ok("delete post succeed");
    }
}

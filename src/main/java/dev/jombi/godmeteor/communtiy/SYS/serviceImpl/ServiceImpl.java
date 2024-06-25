package dev.jombi.godmeteor.communtiy.SYS.serviceImpl;

import dev.jombi.godmeteor.communtiy.JJE.service.PostService;
import dev.jombi.godmeteor.communtiy.SYS.dto.PostDto;
import dev.jombi.godmeteor.communtiy.SYS.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Transactional
    @Override
    public void savePost(PostDto postDto) {
        postRepository.save(postDto.ToEntity());
    }

    @Transactional
    @Override
    public List<PostDto> getPostList(Integer pageNum) {
        
    }

    @Override
    public PostDto getPost(Long id) {
        return null;
    }

    @Override
    public void deletePost(Long id) {

    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }

    @Override
    public void update(Long id, PostDto postDto) {

    }
}

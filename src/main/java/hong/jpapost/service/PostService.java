package hong.jpapost.service;

import hong.jpapost.domain.Post;
import hong.jpapost.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long post(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public Post findOne(Long postId) {
        return postRepository.findOne(postId);
    }
}

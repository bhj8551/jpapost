package hong.jpapost.controller;

import hong.jpapost.domain.Member;
import hong.jpapost.domain.Post;
import hong.jpapost.dto.PostDetailDto;
import hong.jpapost.service.MemberService;
import hong.jpapost.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static hong.jpapost.domain.Post.createPost;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/posts/new")
    public String createPostForm(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        model.addAttribute("postForm", new PostForm());
        return "posts/createPostForm";
    }

    @PostMapping("/posts/new")
    public String create(Model model, @RequestParam("memberId") Long memberId, @Valid PostForm form, BindingResult result) {
        if (result.hasErrors()) {
            List<Member> members = memberService.findMembers();
            model.addAttribute("members", members);
            return "posts/createPostForm";
        }
        Post post = createPost(memberService.findOne(memberId), form);
        postService.post(post);
        return "redirect:/";
    }

    @GetMapping("/posts")
    public String list(Model model) {
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "posts/postList";
    }

    @GetMapping("/posts/{postId}")
    public String postDetail(@PathVariable("postId") Long postId, Model model) {
        log.info("post Detail");
        Post post = postService.hitUp(postId);
        PostDetailDto pdd = PostDetailDto.createPostDetailDto(post);
        model.addAttribute("pdd", pdd);
        return "posts/postDetail";

    }
}

package hong.jpapost.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class PostForm {

    @NotEmpty(message = "제목은 필수 입니다.")
    private String title;

    @NotEmpty(message = "본문은 필수 입니다.")
    private String body;

}

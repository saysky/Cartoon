package com.example.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 视频推荐度打分
 *
 * @author 言曌
 * @date 2021/2/5 12:22 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDegreeDTO implements Comparable<PostDegreeDTO> {

    private Long postId;

    private Double degree;

    @Override
    public int compareTo(PostDegreeDTO o) {
        if (Objects.equals(this.degree, o.degree)) {
            return 0;
        } else if (this.degree > o.degree) {
            return 1;
        } else {
            return -1;
        }
    }
}

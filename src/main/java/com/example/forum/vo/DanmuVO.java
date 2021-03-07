package com.example.forum.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 弹幕
 *
 * text——弹幕文本内容。
 * color——弹幕颜色。
 * position——弹幕位置 0为滚动 1 为顶部 2为底部
 * size——弹幕文字大小。 0为小字 1为大字
 * time——弹幕所出现的时间。 单位为分秒（十分之一秒）
 * isnew——当出现该属性时（属性值可为任意），会认为这是用户新发的弹幕，从而弹幕在显示的时候会有边框。
 * @author 言曌
 * @date 2021/3/7 12:14 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DanmuVO {

    private String text;
    private String color;
    private String position;
    private String size;
    private Integer time;
}

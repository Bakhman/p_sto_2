package com.javamentor.qa.platform.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OutcomeMessageDTO {
    private Long id;
    private String message;
    private String nickName;
    private Long userId;
    private String  image;
    private LocalDateTime persistDateTime;

}

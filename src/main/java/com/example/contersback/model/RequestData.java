package com.example.contersback.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class RequestData {

    List<Photo> photos;

    List<Photo> sentPhotos;

    String login;

}

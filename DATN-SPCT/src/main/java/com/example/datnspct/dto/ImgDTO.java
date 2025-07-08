package com.example.datnspct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImgDTO {
    private Integer idImg;
    private String link;
    private String nameImg;
    private String size;
    private Integer idSPCT;
}

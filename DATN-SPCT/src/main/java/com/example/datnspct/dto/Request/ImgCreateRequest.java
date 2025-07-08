package com.example.datnspct.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImgCreateRequest {

    private Integer idSPCT;       // Id sản phẩm chi tiết
    private String link;          // Đường dẫn ảnh
    private String nameImg;          // Tên file ảnh
    private String size;          // Kích thước file ảnh (ví dụ: "500KB", "1MB")
}

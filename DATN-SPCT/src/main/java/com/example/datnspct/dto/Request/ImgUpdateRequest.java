package com.example.datnspct.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImgUpdateRequest {
    private Integer idImg;        // ID ảnh cần cập nhật
    private Integer idSPCT;       // Id sản phẩm chi tiết liên kết (nếu có thay đổi)
    private String link;
    private String nameImg;
    private String size;
}

package com.example.datnspct.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaiKhoanCreateRequest {
    private String taiKhoan;
    private String matKhau;
    private String chucVu;
    private Boolean trangThai;
}

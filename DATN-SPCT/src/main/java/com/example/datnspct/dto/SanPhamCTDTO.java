package com.example.datnspct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamCTDTO {
    private Integer id;
    private String maSPCT;

    private Integer idSP;
    private String tenSanPham; // Nếu cần hiển thị tên sản phẩm thay vì chỉ id

    private Integer idChatLieu;
    private String tenChatLieu;

    private Integer idThuongHieu;
    private String tenThuongHieu;

    private Integer idSize;
    private String tenSize;

    private Integer idMauSac;
    private String tenMauSac;

    private Integer soLuong;
    private BigDecimal gia;

    private String moTa;
    private Boolean trangThai;


}

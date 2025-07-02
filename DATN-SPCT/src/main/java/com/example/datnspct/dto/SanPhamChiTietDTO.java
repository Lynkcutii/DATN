package com.example.datnspct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietDTO {
    private Integer id; // ID Sản Phẩm Chi Tiết
    private String maSPCT; // Mã Sản Phẩm Chi Tiết
    private Integer idSP; // ID Sản Phẩm
    private Integer idChatLieu; // ID Chất Liệu
    private Integer idThuongHieu; // ID Thương Hiệu
    private Integer idSize; // ID Kích Thước
    private Integer idMauSac; // ID Màu Sắc
    private Integer soLuong; // Số Lượng
    private BigDecimal gia; // Giá
    private String moTa; // Mô Tả
    private Boolean trangThai; // Trạng Thái


}

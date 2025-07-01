package com.example.datnspct.Model;

import com.example.datnspct.support.enums.TrangThai;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "SanPhamCT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SanPhamChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSPCT")
    private Integer id;

    @Column(name = "MaSPCT", length = 50)
    private String maSPCT;

    @Column(name = "IdSP")
    private Integer idSP;

    @Column(name = "IdChatLieu")
    private Integer idChatLieu;

    @Column(name = "IdThuongHieu")
    private Integer idThuongHieu;

    @Column(name = "IdSize")
    private Integer idSize;

    @Column(name = "IdMauSac")
    private Integer idMauSac;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "Gia", precision = 18, scale = 2)
    private BigDecimal gia;

    @Column(name = "MoTa", columnDefinition = "NVARCHAR(MAX)")
    private String moTa;

    @Column(name = "TrangThai")
    private Boolean trangThai;


}

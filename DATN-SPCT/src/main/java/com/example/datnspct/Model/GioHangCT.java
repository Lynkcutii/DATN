package com.example.datnspct.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "GioHangCT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GioHangCT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdGHCT")
    private Integer idGHCT;

    @ManyToOne
    @JoinColumn(name = "IdGH")
    private GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "IdSPCT")
    private SanPhamChiTiet sanPhamChiTiet;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "DonGia", precision = 18, scale = 2)
    private BigDecimal donGia;
}
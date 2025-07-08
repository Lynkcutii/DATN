package org.example.module_banquanao.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "NhanVien")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNV;

    private String maNV;

    @Column(name = "TenNV", columnDefinition = "NVARCHAR(100)")
    private String tenNV;

    @Column(name = "GioiTinh", columnDefinition = "NVARCHAR(10)")
    private String gioiTinh;

    private Date ngaySinh;

    private String sdt;

    private String email;

    @Column(name = "DiaChi", columnDefinition = "NVARCHAR(255)")
    private String diaChi;

    private Boolean trangThai;

//    @ManyToOne
//    @JoinColumn(name = "IdTK")
//    private TaiKhoan taiKhoan;
}

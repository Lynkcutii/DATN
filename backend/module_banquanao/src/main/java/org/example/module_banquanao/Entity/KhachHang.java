package org.example.module_banquanao.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "KhachHang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idKH;

    private String maKH;

    @Column(name = "TenKH", columnDefinition = "NVARCHAR(100)")
    private String tenKH;

    @Column(name = "GioiTinh", columnDefinition = "NVARCHAR(10)")
    private String gioiTinh;

    private String sdt;

    @Column(name = "DiaChi", columnDefinition = "NVARCHAR(255)")
    private String diaChi;

    private Boolean trangThai;

//    @ManyToOne
//    @JoinColumn(name = "IdTK")
//    private TaiKhoan taiKhoan;
}


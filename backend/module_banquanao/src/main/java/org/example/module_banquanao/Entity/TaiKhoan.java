package org.example.module_banquanao.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.module_banquanao.Repository.JointAccount;

@Entity
@Table(name = "TaiKhoan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaiKhoan implements JointAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTK")
    private Integer id;

    @Column(name = "TaiKhoan", length = 100)
    private String taiKhoan;

    @Column(name = "MatKhau", length = 100)
    private String matKhau;

    @Column(name = "ChucVu", length = 50)
    private String chucVu;

    @Column(name = "TrangThai", length = 50)
    private Boolean trangThai;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return taiKhoan;
    }

    @Override
    public String getPassword() {
        return matKhau;
    }

    @Override
    public String getRole() {
        return chucVu;
    }

    @Override
    public String getFullName() {
        return null; // TaiKhoan không có FullName, trả về null
    }

    @Override
    public String getEmail() {
        return null; // TaiKhoan không có Email, trả về null
    }

    @Override
    public String getSdt() {
        return null; // TaiKhoan không có SDT, trả về null
    }

    @Override
    public Boolean getTrangThai() {
        return trangThai;
    }
}
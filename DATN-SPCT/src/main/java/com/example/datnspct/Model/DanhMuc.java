package com.example.datnspct.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "DanhMuc")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DanhMuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDM")
    private Integer idDM;

    @Column(name = "TenDM", length = 100)
    private String tenDM;

    @Column(name = "TrangThai")
    private Boolean trangThai;
}

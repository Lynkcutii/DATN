package com.example.datnspct.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Img")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Img {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdImg")
    private Integer idImg;

    @ManyToOne
    @JoinColumn(name = "IdSPCT", referencedColumnName = "IdSPCT")
    private SanPhamChiTiet sanPhamChiTiet;

    @Column(name = "link", columnDefinition = "NVARCHAR(MAX)")
    private String link;

    @Column(name = "name", length = 255)
    private String nameImg;

    @Column(name = "size", length = 50)
    private String size;
}

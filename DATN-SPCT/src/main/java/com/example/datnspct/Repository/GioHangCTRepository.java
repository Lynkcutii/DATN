package com.example.datnspct.Repository;

import com.example.datnspct.Model.GioHang;
import com.example.datnspct.Model.GioHangCT;
import com.example.datnspct.Model.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GioHangCTRepository extends JpaRepository<GioHangCT, String> {
    Optional<GioHangCT> findByGioHangAndSanPhamChiTiet(GioHang gioHang, SanPhamChiTiet sanPhamChiTiet);

}

package com.example.datnspct.Repository;

import com.example.datnspct.Model.GioHang;
import com.example.datnspct.Model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Integer> {

    Optional<GioHang> findByTaiKhoanIdTK(Integer idTK);
} 
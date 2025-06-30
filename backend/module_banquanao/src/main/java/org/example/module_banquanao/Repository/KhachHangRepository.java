package org.example.module_banquanao.Repository;

import org.example.module_banquanao.Entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {
    Optional<KhachHang> findByMaKH(String maKH);
}
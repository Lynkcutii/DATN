package com.example.datnspct.Repository;

import com.example.datnspct.Model.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonCTRepository extends JpaRepository<HoaDonChiTiet, String> {
}

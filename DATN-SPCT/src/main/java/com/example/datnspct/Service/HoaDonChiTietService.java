package com.example.datnspct.Service;

import com.example.datnspct.dto.HoaDonChiTietDTO;
import com.example.datnspct.Model.HoaDon;
import com.example.datnspct.Model.HoaDonChiTiet;
import com.example.datnspct.Model.SanPhamChiTiet;
import com.example.datnspct.Repository.HoaDonChiTietRepository;
import com.example.datnspct.Repository.HoaDonRepository;
import com.example.datnspct.Repository.SanPhamChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class HoaDonChiTietService {

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    private HoaDonChiTietDTO convertToDTO(HoaDonChiTiet entity) {
        HoaDonChiTietDTO dto = new HoaDonChiTietDTO();
        dto.setIdHDCT(entity.getIdHDCT());
        dto.setIdSP(entity.getSanPham().getId());
        dto.setIdHD(entity.getHoaDon().getIdHD());
        // Set other fields
        return dto;
    }

    private HoaDonChiTiet convertToEntity(HoaDonChiTietDTO dto) {
        HoaDonChiTiet entity = new HoaDonChiTiet();
        entity.setIdHDCT(dto.getIdHDCT());
        // Fetch related entities
        HoaDon hoaDon = hoaDonRepository.findById(dto.getIdHD())
                .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));
        SanPhamChiTiet sanPham = sanPhamChiTietRepository.findById(dto.getIdSP())
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));
        entity.setHoaDon(hoaDon);
        entity.setSanPham(sanPham);
        // Set other fields
        entity.setThanhTien(dto.getDonGia().multiply(BigDecimal.valueOf(dto.getSoLuong())));
        return entity;
    }

    public HoaDonChiTietDTO createHoaDonChiTiet(HoaDonChiTietDTO dto) {
        HoaDonChiTiet entity = convertToEntity(dto);
        entity.setNgayTao(LocalDateTime.now());
        entity.setNgaySua(LocalDateTime.now());
        HoaDonChiTiet saved = hoaDonChiTietRepository.save(entity);
        return convertToDTO(saved);
    }
}
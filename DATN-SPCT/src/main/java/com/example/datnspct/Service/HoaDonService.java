package com.example.datnspct.Service;

import com.example.datnspct.dto.HoaDonDTO;
import com.example.datnspct.Model.HoaDon;
import com.example.datnspct.Repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    // Manual mapping: Entity to DTO
    private HoaDonDTO convertToDTO(HoaDon hoaDon) {
        HoaDonDTO dto = new HoaDonDTO();
        dto.setIdHD(hoaDon.getIdHD());
        dto.setMaHD(hoaDon.getMaHD());
        dto.setIdKhachHang(hoaDon.getIdKhachHang());
        dto.setIdNhanVien(hoaDon.getIdNhanVien());
        dto.setNgayTao(hoaDon.getNgayTao());
        dto.setNgaySua(hoaDon.getNgaySua());
        dto.setTongTien(hoaDon.getTongTien());
        dto.setTrangThai(hoaDon.getTrangThai());
        return dto;
    }

    // Manual mapping: DTO to Entity
    private HoaDon convertToEntity(HoaDonDTO dto) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setIdHD(dto.getIdHD());
        hoaDon.setMaHD(dto.getMaHD());
        hoaDon.setIdKhachHang(dto.getIdKhachHang());
        hoaDon.setIdNhanVien(dto.getIdNhanVien());
        hoaDon.setNgayTao(dto.getNgayTao());
        hoaDon.setNgaySua(dto.getNgaySua());
        hoaDon.setTongTien(dto.getTongTien());
        hoaDon.setTrangThai(dto.getTrangThai());
        return hoaDon;
    }

    // Create
    public HoaDonDTO createHoaDon(HoaDonDTO dto) {
        HoaDon hoaDon = convertToEntity(dto);
        hoaDon.setNgayTao(LocalDateTime.now());
        hoaDon.setNgaySua(LocalDateTime.now());
        HoaDon savedHoaDon = hoaDonRepository.save(hoaDon);
        return convertToDTO(savedHoaDon);
    }

    // Read (Get by ID)
    public HoaDonDTO getHoaDonById(Integer id) {
        HoaDon hoaDon = hoaDonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));
        return convertToDTO(hoaDon);
    }

    // Read (Get all)
    public List<HoaDonDTO> getAllHoaDon() {
        return hoaDonRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Update
    public HoaDonDTO updateHoaDon(Integer id, HoaDonDTO dto) {
        HoaDon hoaDon = hoaDonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));
        hoaDon.setMaHD(dto.getMaHD());
        hoaDon.setIdKhachHang(dto.getIdKhachHang());
        hoaDon.setIdNhanVien(dto.getIdNhanVien());
        hoaDon.setTongTien(dto.getTongTien());
        hoaDon.setTrangThai(dto.getTrangThai());
        hoaDon.setNgaySua(LocalDateTime.now());
        HoaDon updatedHoaDon = hoaDonRepository.save(hoaDon);
        return convertToDTO(updatedHoaDon);
    }

    // Delete
    public void deleteHoaDon(Integer id) {
        HoaDon hoaDon = hoaDonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));
        hoaDonRepository.delete(hoaDon);
    }
}

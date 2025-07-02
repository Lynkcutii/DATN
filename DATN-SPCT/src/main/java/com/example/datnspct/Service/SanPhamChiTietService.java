package com.example.datnspct.Service;

import com.example.datnspct.dto.SanPhamChiTietDTO;
import com.example.datnspct.Model.SanPham;
import com.example.datnspct.Model.SanPhamChiTiet;
import com.example.datnspct.Repository.SanPhamChiTietRepository;
import com.example.datnspct.Repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SanPhamChiTietService {

    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    // Ánh xạ thủ công: Từ Entity sang DTO
    private SanPhamChiTietDTO chuyenSangDTO(SanPhamChiTiet sanPhamChiTiet) {
        SanPhamChiTietDTO dto = new SanPhamChiTietDTO();
        dto.setId(sanPhamChiTiet.getId());
        dto.setMaSPCT(sanPhamChiTiet.getMaSPCT());
        dto.setIdSP(sanPhamChiTiet.getIdSP());
        dto.setIdChatLieu(sanPhamChiTiet.getIdChatLieu());
        dto.setIdThuongHieu(sanPhamChiTiet.getIdThuongHieu());
        dto.setIdSize(sanPhamChiTiet.getIdSize());
        dto.setIdMauSac(sanPhamChiTiet.getIdMauSac());
        dto.setSoLuong(sanPhamChiTiet.getSoLuong());
        dto.setGia(sanPhamChiTiet.getGia());
        dto.setMoTa(sanPhamChiTiet.getMoTa());
        dto.setTrangThai(sanPhamChiTiet.getTrangThai());
        return dto;
    }

    // Ánh xạ thủ công: Từ DTO sang Entity
    private SanPhamChiTiet chuyenSangEntity(SanPhamChiTietDTO dto) {
        SanPhamChiTiet sanPhamChiTiet = new SanPhamChiTiet();
        sanPhamChiTiet.setId(dto.getId());
        sanPhamChiTiet.setMaSPCT(dto.getMaSPCT());
        sanPhamChiTiet.setIdSP(dto.getIdSP());
        sanPhamChiTiet.setIdChatLieu(dto.getIdChatLieu());
        sanPhamChiTiet.setIdThuongHieu(dto.getIdThuongHieu());
        sanPhamChiTiet.setIdSize(dto.getIdSize());
        sanPhamChiTiet.setIdMauSac(dto.getIdMauSac());
        sanPhamChiTiet.setSoLuong(dto.getSoLuong());
        sanPhamChiTiet.setGia(dto.getGia());
        sanPhamChiTiet.setMoTa(dto.getMoTa());
        sanPhamChiTiet.setTrangThai(dto.getTrangThai());
        return sanPhamChiTiet;
    }

    // Tạo mới
    public SanPhamChiTietDTO taoSanPhamChiTiet(SanPhamChiTietDTO dto) {
        // Kiểm tra sản phẩm tồn tại
        sanPhamRepository.findById(dto.getIdSP())
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));
        SanPhamChiTiet sanPhamChiTiet = chuyenSangEntity(dto);
        SanPhamChiTiet sanPhamChiTietDaLuu = sanPhamChiTietRepository.save(sanPhamChiTiet);
        return chuyenSangDTO(sanPhamChiTietDaLuu);
    }

    // Lấy theo ID
    public SanPhamChiTietDTO laySanPhamChiTietTheoId(Integer id) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sản phẩm chi tiết không tồn tại"));
        return chuyenSangDTO(sanPhamChiTiet);
    }

    // Lấy tất cả
    public List<SanPhamChiTietDTO> layTatCaSanPhamChiTiet() {
        return sanPhamChiTietRepository.findAll().stream()
                .map(this::chuyenSangDTO)
                .collect(Collectors.toList());
    }

    // Cập nhật
    public SanPhamChiTietDTO capNhatSanPhamChiTiet(Integer id, SanPhamChiTietDTO dto) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sản phẩm chi tiết không tồn tại"));
        sanPhamChiTiet.setMaSPCT(dto.getMaSPCT());
        sanPhamChiTiet.setIdSP(dto.getIdSP());
        sanPhamChiTiet.setIdChatLieu(dto.getIdChatLieu());
        sanPhamChiTiet.setIdThuongHieu(dto.getIdThuongHieu());
        sanPhamChiTiet.setIdSize(dto.getIdSize());
        sanPhamChiTiet.setIdMauSac(dto.getIdMauSac());
        sanPhamChiTiet.setSoLuong(dto.getSoLuong());
        sanPhamChiTiet.setGia(dto.getGia());
        sanPhamChiTiet.setMoTa(dto.getMoTa());
        sanPhamChiTiet.setTrangThai(dto.getTrangThai());
        // Kiểm tra sản phẩm tồn tại
        sanPhamRepository.findById(dto.getIdSP())
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));
        SanPhamChiTiet sanPhamChiTietDaCapNhat = sanPhamChiTietRepository.save(sanPhamChiTiet);
        return chuyenSangDTO(sanPhamChiTietDaCapNhat);
    }

    // Xóa
    public void xoaSanPhamChiTiet(Integer id) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sản phẩm chi tiết không tồn tại"));
        sanPhamChiTietRepository.delete(sanPhamChiTiet);
    }
}
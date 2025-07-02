package com.example.datnspct.Service;

import com.example.datnspct.dto.PhuongThucThanhToanDTO;
import com.example.datnspct.Model.PhuongThucThanhToan;
import com.example.datnspct.Repository.PhuongThucThanhToanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhuongThucThanhToanService {
    @Autowired
    private PhuongThucThanhToanRepository repository;

    // Entity -> DTO
    private PhuongThucThanhToanDTO toDTO(PhuongThucThanhToan entity) {
        PhuongThucThanhToanDTO dto = new PhuongThucThanhToanDTO();
        dto.setIdPTT(entity.getIdPTT());
        dto.setTenPTT(entity.getTenPTT());
        return dto;
    }

    // DTO -> Entity
    private PhuongThucThanhToan toEntity(PhuongThucThanhToanDTO dto) {
        PhuongThucThanhToan entity = new PhuongThucThanhToan();
        entity.setIdPTT(dto.getIdPTT());
        entity.setTenPTT(dto.getTenPTT());
        return entity;
    }

    // Tạo mới
    public PhuongThucThanhToanDTO create(PhuongThucThanhToanDTO dto) {
        PhuongThucThanhToan entity = toEntity(dto);
        PhuongThucThanhToan saved = repository.save(entity);
        return toDTO(saved);
    }

    // Lấy theo ID
    public PhuongThucThanhToanDTO getById(Integer id) {
        PhuongThucThanhToan entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phương thức thanh toán"));
        return toDTO(entity);
    }

    // Lấy tất cả
    public List<PhuongThucThanhToanDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    // Cập nhật
    public PhuongThucThanhToanDTO update(Integer id, PhuongThucThanhToanDTO dto) {
        PhuongThucThanhToan entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phương thức thanh toán"));
        entity.setTenPTT(dto.getTenPTT());
        PhuongThucThanhToan updated = repository.save(entity);
        return toDTO(updated);
    }

    // Xóa
    public void delete(Integer id) {
        PhuongThucThanhToan entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phương thức thanh toán"));
        repository.delete(entity);
    }
} 
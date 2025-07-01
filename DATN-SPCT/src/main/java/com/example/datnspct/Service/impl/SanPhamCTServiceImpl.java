package com.example.datnspct.Service.impl;

import com.example.datnspct.Model.SanPhamChiTiet;
import com.example.datnspct.Repository.SanPhamCTRepository;
import com.example.datnspct.Service.SanPhamCTService;
import com.example.datnspct.dto.Request.SanPhamCTCreateRequest;
import com.example.datnspct.dto.Request.SanPhamCTUpdateRequest;
import com.example.datnspct.dto.SanPhamCTDTO;
import com.example.datnspct.support.enums.TrangThai;
import com.example.datnspct.support.exception.BadRequestError;
import com.mbamc.common.exception.ResponseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class SanPhamCTServiceImpl implements SanPhamCTService {
    public final ModelMapper modelMapper;
    public final SanPhamCTRepository sanPhamCTRepository;

    @Override
    @Transactional
    public void create(SanPhamCTCreateRequest request) {
        SanPhamChiTiet entity = new SanPhamChiTiet();
        entity.setMaSPCT(request.getMaSPCT());
        entity.setIdSP(request.getIdSP());
        entity.setIdChatLieu(request.getIdChatLieu());
        entity.setIdThuongHieu(request.getIdThuongHieu());
        entity.setIdSize(request.getIdSize());
        entity.setIdMauSac(request.getIdMauSac());
        entity.setSoLuong(request.getSoLuong());
        entity.setGia(request.getGia());
        entity.setMoTa(request.getMoTa());
        entity.setTrangThai(request.getTrangThai() != null ? request.getTrangThai() : true);

        sanPhamCTRepository.save(entity);
    }


    @Override
    @Transactional
    public void inactiveSanPhamCT(String sanPhamCTId) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamCTRepository.findById(sanPhamCTId).orElse(null);
        if (sanPhamChiTiet != null) {
            sanPhamChiTiet.setTrangThai(Boolean.valueOf(TrangThai.INACTIVE.name()));
            sanPhamCTRepository.save(sanPhamChiTiet);
        }
    }

    @Override
    @Transactional
    public void update(SanPhamCTUpdateRequest request) {
        SanPhamChiTiet entity = sanPhamCTRepository.findById(String.valueOf(request.getId()))
                .orElseThrow(() -> new ResponseException(BadRequestError.PRODUCT_NOT_EXISTED));

        // Cập nhật các trường
        entity.setMaSPCT(request.getMaSPCT());
        entity.setIdSP(request.getIdSP());
        entity.setIdChatLieu(request.getIdChatLieu());
        entity.setIdThuongHieu(request.getIdThuongHieu());
        entity.setIdSize(request.getIdSize());
        entity.setIdMauSac(request.getIdMauSac());
        entity.setSoLuong(request.getSoLuong());
        entity.setGia(request.getGia());
        entity.setMoTa(request.getMoTa());


//        Boolean trangThai = request.getTrangThai();
//        if (trangThai != null) {
//           entity.setTrangThai(trangThai ? 1 : 0);
//        }

        sanPhamCTRepository.save(entity);
    }

    @Override
    public SanPhamCTDTO getById(String sanPhamCTId) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamCTRepository.findById(sanPhamCTId).orElse(null);
        if (sanPhamChiTiet != null) {
            return modelMapper.map(sanPhamChiTiet, SanPhamCTDTO.class);
        }
        return null;
    }

    @Override
    public List<SanPhamCTDTO> getAll() {
        List<SanPhamChiTiet> entities = sanPhamCTRepository.findAll();

        return entities.stream().map(entity -> {
            SanPhamCTDTO dto = new SanPhamCTDTO();
            dto.setId(entity.getId());
            dto.setMaSPCT(entity.getMaSPCT());
            dto.setIdSP(entity.getIdSP());
            dto.setIdChatLieu(entity.getIdChatLieu());
            dto.setIdThuongHieu(entity.getIdThuongHieu());
            dto.setIdSize(entity.getIdSize());
            dto.setIdMauSac(entity.getIdMauSac());
            dto.setSoLuong(entity.getSoLuong());
            dto.setGia(entity.getGia());
            dto.setMoTa(entity.getMoTa());
            dto.setTrangThai(entity.getTrangThai()); // "ACTIVE" hoặc "INACTIVE"
            return dto;
        }).toList();
    }
}

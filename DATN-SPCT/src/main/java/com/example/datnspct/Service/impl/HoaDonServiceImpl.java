package com.example.datnspct.Service.impl;

import com.example.datnspct.Model.HoaDon;
import com.example.datnspct.Repository.HoaDonRepository;
import com.example.datnspct.Service.HoaDonService;
import com.example.datnspct.dto.HoaDonDTO;
import com.example.datnspct.dto.Request.HoaDonCreateRequest;
import com.example.datnspct.dto.Request.HoaDonUpdateRequest;
import com.example.datnspct.support.exception.BadRequestError;
import com.mbamc.common.exception.ResponseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class HoaDonServiceImpl implements HoaDonService {
    public final ModelMapper modelMapper;
    public final HoaDonRepository hoaDonRepository;

    @Override
    @Transactional
    public void create(HoaDonCreateRequest request) {
        HoaDon hoaDon = modelMapper.map(request, HoaDon.class);
        hoaDon.setTrangThai(true);
        hoaDon.setNgayTao(LocalDateTime.now());
        hoaDon.setNgaySua(LocalDateTime.now());
        hoaDonRepository.save(hoaDon);
    }

    @Override
    @Transactional
    public void inactiveHoaDon(String HoaDonId) {
        HoaDon hoaDon = hoaDonRepository.findById(HoaDonId).orElse(null);
        if (hoaDon != null) {
            hoaDon.setTrangThai(false);
            hoaDonRepository.save(hoaDon);
        }
    }

    @Override
    @Transactional
    public void update(HoaDonUpdateRequest request) {
        HoaDon hoaDon = hoaDonRepository.findById(String.valueOf(request.getIdHD())).orElse(null);
        if (Objects.isNull(hoaDon)) {
            throw new ResponseException(BadRequestError.PRODUCT_NOT_EXISTED);
        }
        LocalDateTime oldNgayTao = hoaDon.getNgayTao();
        modelMapper.map(request, hoaDon);
        hoaDon.setNgayTao(oldNgayTao); // Giữ lại ngayTao cũ
        hoaDon.setNgaySua(LocalDateTime.now()); // Cập nhật thời gian sửa
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDonDTO getById(String hoaDonId) {
        HoaDon hoaDon = hoaDonRepository.findById(hoaDonId)
                .orElseThrow(() -> new ResponseException(BadRequestError.PRODUCT_NOT_EXISTED));

        return modelMapper.map(hoaDon, HoaDonDTO.class);
    }

    @Override
    public List<HoaDonDTO> getAll() {
        List<HoaDon> hoaDonList = hoaDonRepository.findAll();
        return hoaDonList.stream()
                .map(hoaDon -> modelMapper.map(hoaDon, HoaDonDTO.class))
                .toList();
    }
}

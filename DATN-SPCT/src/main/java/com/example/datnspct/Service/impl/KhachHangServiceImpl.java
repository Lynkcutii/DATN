package com.example.datnspct.Service.impl;

import com.example.datnspct.Model.KhachHang;
import com.example.datnspct.Repository.KhachHangRepository;
import com.example.datnspct.Service.KhachHangService;
import com.example.datnspct.dto.KhachHangDTO;
import com.example.datnspct.dto.Request.KhachHangCreateRequest;
import com.example.datnspct.dto.Request.KhachHangUpdateRequest;
import com.example.datnspct.support.exception.BadRequestError;
import com.mbamc.common.exception.ResponseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class KhachHangServiceImpl implements KhachHangService {
    public final ModelMapper modelMapper;
    public final KhachHangRepository khachHangRepository;

    @Override
    @Transactional
    public void create(KhachHangCreateRequest request) {
        KhachHang khachHang = modelMapper.map(request, KhachHang.class);
        khachHang.setTrangThai(true);
        khachHangRepository.save(khachHang);
    }

    @Override
    @Transactional
    public void inactiveKhachHang(String khachHangId) {
        KhachHang khachHang = khachHangRepository.findById(khachHangId).orElse(null);
        if (khachHang != null) {
            khachHang.setTrangThai(false);
            khachHangRepository.save(khachHang);
        }
    }

    @Override
    @Transactional
    public void update(KhachHangUpdateRequest request) {
        KhachHang khachHang = khachHangRepository.findById(String.valueOf(request.getIdKH())).orElse(null);
        if (Objects.isNull(khachHang)) {
            throw new ResponseException(BadRequestError.CLIENT_NOT_EXISTED);
        }
        modelMapper.map(request, khachHang);
        khachHangRepository.save(khachHang);
    }

    @Override
    public KhachHangDTO getById(String khachHangId) {
        KhachHang khachHang = khachHangRepository.findById(khachHangId)
                .orElseThrow(() -> new ResponseException(BadRequestError.CLIENT_NOT_EXISTED));

        return modelMapper.map(khachHang, KhachHangDTO.class);
    }

    @Override
    public List<KhachHangDTO> getAll() {
        List<KhachHang> khachHangList = khachHangRepository.findAll();
        return khachHangList.stream()
                .map(khachHang -> modelMapper.map(khachHang, KhachHangDTO.class))
                .toList();
    }
}

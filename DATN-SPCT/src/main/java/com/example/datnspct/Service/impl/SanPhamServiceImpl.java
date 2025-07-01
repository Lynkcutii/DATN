package com.example.datnspct.Service.impl;

import com.example.datnspct.Model.SanPham;
import com.example.datnspct.Repository.SanPhamRepository;
import com.example.datnspct.Service.SanPhamService;
import com.example.datnspct.dto.Request.SanPhamCTCreateRequest;
import com.example.datnspct.dto.Request.SanPhamCTUpdateRequest;
import com.example.datnspct.dto.Request.SanPhamCreateRequest;
import com.example.datnspct.dto.Request.SanPhamUpdateRequest;
import com.example.datnspct.dto.SanPhamDTO;
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
public class SanPhamServiceImpl implements SanPhamService {
    public final ModelMapper modelMapper;
    public final SanPhamRepository sanPhamRepository;

    @Override
    @Transactional
    public void create(SanPhamCreateRequest request) {
        SanPham sanPham = modelMapper.map(request, SanPham.class);
        sanPham.setTrangThai(true);
        sanPhamRepository.save(sanPham);
    }

    @Override
    @Transactional
    public void inactiveSanPham(String sanPhamId) {
        SanPham sanPham = sanPhamRepository.findById(sanPhamId).orElse(null);
        if (sanPham != null) {
            sanPham.setTrangThai(false);
            sanPhamRepository.save(sanPham);
        }
    }

    @Override
    @Transactional
    public void update(SanPhamUpdateRequest request) {
        SanPham sanPham = sanPhamRepository.findById(String.valueOf(request.getIdSP())).orElse(null);
        if (Objects.isNull(sanPham)) {
            throw new ResponseException(BadRequestError.PRODUCT_NOT_EXISTED);
        }
        modelMapper.map(request, sanPham);
        sanPhamRepository.save(sanPham);
    }

    @Override
    public SanPhamDTO getById(String sanPhamId) {
        SanPham sanPham = sanPhamRepository.findById(sanPhamId)
                .orElseThrow(() -> new ResponseException(BadRequestError.PRODUCT_NOT_EXISTED));

        return modelMapper.map(sanPham, SanPhamDTO.class);
    }

    @Override
    public List<SanPhamDTO> getAll() {
        List<SanPham> sanPhamList = sanPhamRepository.findAll();
        return sanPhamList.stream()
                .map(sanPham -> modelMapper.map(sanPham, SanPhamDTO.class))
                .toList();
    }
}

package com.example.datnspct.Service.impl;

import com.example.datnspct.Model.ThuongHieu;
import com.example.datnspct.Repository.ThuongHieuRepository;
import com.example.datnspct.Service.ThuongHieuService;
import com.example.datnspct.dto.Request.ThuongHieuCreateRequest;
import com.example.datnspct.dto.Request.ThuongHieuUpdateRequest;
import com.example.datnspct.dto.ThuongHieuDTO;
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
public class ThuongHieuServiceImpl implements ThuongHieuService {
    public final ModelMapper modelMapper;
    public final ThuongHieuRepository thuongHieuRepository;

    @Override
    @Transactional
    public void create(ThuongHieuCreateRequest request) {
        ThuongHieu thuongHieu = modelMapper.map(request, ThuongHieu.class);
        thuongHieu.setTrangThai(true);
        thuongHieuRepository.save(thuongHieu);
    }

    @Override
    @Transactional
    public void inactiveThuongHieu(String thuongHieuId) {
        ThuongHieu thuongHieu = thuongHieuRepository.findById(thuongHieuId).orElse(null);
        if (thuongHieu != null) {
            thuongHieu.setTrangThai(false);
            thuongHieuRepository.save(thuongHieu);
        }
    }

    @Override
    @Transactional
    public void update(ThuongHieuUpdateRequest request) {
        ThuongHieu thuongHieu = thuongHieuRepository.findById(String.valueOf(request.getIdThuongHieu())).orElse(null);
        if (Objects.isNull(thuongHieu)) {
            throw new ResponseException(BadRequestError.PRODUCT_NOT_EXISTED);
        }
        modelMapper.map(request, thuongHieu);
        thuongHieuRepository.save(thuongHieu);
    }

    @Override
    public ThuongHieuDTO getById(String thuongHieuId) {
        ThuongHieu thuongHieu = thuongHieuRepository.findById(thuongHieuId)
                .orElseThrow(() -> new ResponseException(BadRequestError.PRODUCT_NOT_EXISTED));

        return modelMapper.map(thuongHieu, ThuongHieuDTO.class);
    }

    @Override
    public List<ThuongHieuDTO> getAll() {
        List<ThuongHieu> thuongHieuList = thuongHieuRepository.findAll();
        return thuongHieuList.stream()
                .map(thuongHieu -> modelMapper.map(thuongHieu, ThuongHieuDTO.class))
                .toList();
    }
}

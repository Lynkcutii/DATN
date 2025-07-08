package com.example.datnspct.Service.impl;

import com.example.datnspct.Model.DanhMuc;
import com.example.datnspct.Repository.DanhMucRepository;
import com.example.datnspct.Service.DanhMucService;
import com.example.datnspct.dto.DanhMucDTO;
import com.example.datnspct.dto.Request.DanhMucCreateRequest;
import com.example.datnspct.dto.Request.DanhMucUpdateRequest;
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
public class DanhMucServiceImpl implements DanhMucService {
    public final ModelMapper modelMapper;
    public final DanhMucRepository danhMucRepository;

    @Override
    @Transactional
    public void create(DanhMucCreateRequest request) {
        DanhMuc danhMuc = modelMapper.map(request, DanhMuc.class);
        danhMuc.setTrangThai(true);
        danhMucRepository.save(danhMuc);
    }

    @Override
    @Transactional
    public void inactiveDanhMuc(String danhMucId) {
        DanhMuc danhMuc = danhMucRepository.findById(danhMucId).orElse(null);
        if (danhMuc != null) {
            danhMuc.setTrangThai(false);
            danhMucRepository.save(danhMuc);
        }
    }

    @Override
    @Transactional
    public void update(DanhMucUpdateRequest request) {
        DanhMuc danhMuc = danhMucRepository.findById(String.valueOf(request.getIdDM())).orElse(null);
        if (Objects.isNull(danhMuc)) {
            throw new ResponseException(BadRequestError.PRODUCT_NOT_EXISTED);
        }
        modelMapper.map(request, danhMuc);
        danhMucRepository.save(danhMuc);
    }

    @Override
    public DanhMucDTO getById(String danhMucId) {
        DanhMuc danhMuc = danhMucRepository.findById(danhMucId)
                .orElseThrow(() -> new ResponseException(BadRequestError.PRODUCT_NOT_EXISTED));

        return modelMapper.map(danhMuc, DanhMucDTO.class);
    }

    @Override
    public List<DanhMucDTO> getAll() {
        List<DanhMuc> danhMucList = danhMucRepository.findAll();
        return danhMucList.stream()
                .map(danhMuc -> modelMapper.map(danhMuc, DanhMucDTO.class))
                .toList();
    }
}

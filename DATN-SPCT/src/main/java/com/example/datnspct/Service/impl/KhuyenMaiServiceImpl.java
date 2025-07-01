package com.example.datnspct.Service.impl;

import com.example.datnspct.Model.KhuyenMai;
import com.example.datnspct.Repository.KhuyenMaiRepository;
import com.example.datnspct.Service.KhuyenMaiService;
import com.example.datnspct.dto.KhuyenMaiDTO;
import com.example.datnspct.dto.Request.KhuyenMaiCreateRequest;
import com.example.datnspct.dto.Request.KhuyenMaiUpdateRequest;
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
public class KhuyenMaiServiceImpl implements KhuyenMaiService {
    public final ModelMapper modelMapper;
    public final KhuyenMaiRepository khuyenMaiRepository;

    @Override
    @Transactional
    public void create(KhuyenMaiCreateRequest request) {
        KhuyenMai khuyenMai = modelMapper.map(request, KhuyenMai.class);
        khuyenMai.setTrangThai(true);
        khuyenMai.setNgayBatDau(LocalDateTime.now());
        khuyenMai.setNgayKetThuc(LocalDateTime.now());
        khuyenMaiRepository.save(khuyenMai);
    }

    @Override
    @Transactional
    public void inactiveKhuyenMai(String khuyenMaiId) {
        KhuyenMai khuyenMai = khuyenMaiRepository.findById(khuyenMaiId).orElse(null);
        if (khuyenMai != null) {
            khuyenMai.setTrangThai(false);
            khuyenMaiRepository.save(khuyenMai);
        }
    }

    @Override
    @Transactional
    public void update(KhuyenMaiUpdateRequest request) {
        KhuyenMai khuyenMai = khuyenMaiRepository.findById(String.valueOf(request.getIdKM())).orElse(null);

        if (Objects.isNull(khuyenMai)) {
            throw new ResponseException(BadRequestError.PRODUCT_NOT_EXISTED);
        }

        // Validate thời gian
        if (request.getNgayBatDau() == null || request.getNgayKetThuc() == null) {
            throw new ResponseException("NGÀY BẮT ĐẦU VÀ NGÀY KẾT THÚC KHÔNG ĐƯỢC ĐỂ TRỐNG", BadRequestError.INVALID_DATE);
        }

        if (request.getNgayBatDau().isAfter(request.getNgayKetThuc())) {
            throw new ResponseException("NGÀY BẮT ĐẦU PHẢI TRƯỚC NGÀY KẾT THÚC", BadRequestError.INVALID_DATE);
        }
        modelMapper.map(request, khuyenMai);

        khuyenMai.setNgayBatDau(request.getNgayBatDau());
        khuyenMai.setNgayKetThuc(request.getNgayKetThuc());

        khuyenMaiRepository.save(khuyenMai);
    }

    @Override
    public KhuyenMaiDTO getById(String khuyenMaiId) {
        KhuyenMai khuyenMai = khuyenMaiRepository.findById(khuyenMaiId)
                .orElseThrow(() -> new ResponseException(BadRequestError.PRODUCT_NOT_EXISTED));

        return modelMapper.map(khuyenMai, KhuyenMaiDTO.class);
    }

    @Override
    public List<KhuyenMaiDTO> getAll() {
        List<KhuyenMai> khuyenMaiList = khuyenMaiRepository.findAll();
        return khuyenMaiList.stream()
                .map(khuyenMai -> modelMapper.map(khuyenMai, KhuyenMaiDTO.class))
                .toList();
    }
}

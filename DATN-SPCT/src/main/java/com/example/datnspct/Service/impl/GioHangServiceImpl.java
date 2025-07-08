package com.example.datnspct.Service.impl;

import com.example.datnspct.Model.GioHang;
import com.example.datnspct.Model.KhachHang;
import com.example.datnspct.Repository.GioHangRepository;
import com.example.datnspct.Repository.KhachHangRepository;
import com.example.datnspct.Service.GioHangService;
import com.example.datnspct.dto.GioHangDTO;
import com.example.datnspct.dto.Request.GioHangCreateRequest;
import com.example.datnspct.dto.Request.GioHangUpdateRequest;
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
public class GioHangServiceImpl implements GioHangService {
    public final ModelMapper modelMapper;
    public final GioHangRepository gioHangRepository;
    public final KhachHangRepository khachHangRepository;

    @Override
    @Transactional
    public void create(GioHangCreateRequest request) {
        // Lấy khách hàng liên kết từ DB
        KhachHang khachHang = khachHangRepository.findById(String.valueOf(request.getIdKH()))
                .orElseThrow(() -> new ResponseException("KHÁCH HÀNG KHÔNG TỒN TẠI", BadRequestError.CLIENT_NOT_EXISTED));


        GioHang gioHang = modelMapper.map(request, GioHang.class);
        gioHang.setTrangThai(true);
        gioHang.setNgayTao(LocalDateTime.now());
        gioHang.setNgaySua(LocalDateTime.now());
        gioHang.setKhachHang(khachHang);
        gioHangRepository.save(gioHang);
    }

    @Override
    @Transactional
    public void inactiveGioHang(String gioHangId) {
        GioHang gioHang = gioHangRepository.findById(gioHangId).orElse(null);
        if (gioHang != null) {
            gioHang.setTrangThai(false);
            gioHangRepository.save(gioHang);
        }
    }

    @Override
    @Transactional
    public void update(GioHangUpdateRequest request) {
        GioHang gioHang = gioHangRepository.findById(String.valueOf(request.getIdGH())).orElse(null);

        if (Objects.isNull(gioHang)) {
            throw new ResponseException(BadRequestError.PRODUCT_NOT_EXISTED);
        }

        // Validate thời gian
        if (request.getNgayTao() == null || request.getNgaySua() == null) {
            throw new ResponseException("NGÀY TẠO VÀ NGÀY SỬA KHÔNG ĐƯỢC ĐỂ TRỐNG", BadRequestError.INVALID_DATE);
        }

        if (request.getNgayTao().isAfter(request.getNgaySua())) {
            throw new ResponseException("NGÀY TẠO PHẢI TRƯỚC NGÀY SỬA", BadRequestError.INVALID_DATE);
        }
        modelMapper.map(request, gioHang);

        gioHang.setNgayTao(request.getNgayTao());
        gioHang.setNgaySua(request.getNgaySua());

        gioHangRepository.save(gioHang);
    }

    @Override
    public GioHangDTO getById(String gioHangId) {
        GioHang gioHang = gioHangRepository.findById(gioHangId)
                .orElseThrow(() -> new ResponseException(BadRequestError.CART_NOT_EXISTED));

        return modelMapper.map(gioHang, GioHangDTO.class);
    }

    @Override
    public List<GioHangDTO> getAll() {
        List<GioHang> gioHangList = gioHangRepository.findAll();
        return gioHangList.stream()
                .map(gioHang -> modelMapper.map(gioHang, GioHangDTO.class))
                .toList();
    }
}

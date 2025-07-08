package com.example.datnspct.Service.impl;

import com.example.datnspct.Model.NhanVien;
import com.example.datnspct.Model.TaiKhoan;
import com.example.datnspct.Repository.NhanVienRepository;
import com.example.datnspct.Repository.TaiKhoanRepository;
import com.example.datnspct.Service.NhanVienService;
import com.example.datnspct.dto.NhanVienDTO;
import com.example.datnspct.dto.Request.NhanVienCreateRequest;
import com.example.datnspct.dto.Request.NhanVienUpdateRequest;
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
public class NhanVienServiceImpl implements NhanVienService {
    public final ModelMapper modelMapper;
    public final NhanVienRepository nhanVienRepository;
    public final TaiKhoanRepository taiKhoanRepository;

    @Override
    @Transactional
    public void create(NhanVienCreateRequest request) {
        // Lấy tài khoản liên kết từ DB
        TaiKhoan taiKhoan = taiKhoanRepository.findById(String.valueOf(request.getIdTK()))
                .orElseThrow(() -> new ResponseException("Tài khoản không tồn tại", BadRequestError.ACCOUNT_NOT_EXISTED));

        // Map dữ liệu
        NhanVien nhanVien = modelMapper.map(request, NhanVien.class);
        nhanVien.setTaiKhoan(taiKhoan); // Gán quan hệ
        nhanVien.setTrangThai(true); // Mặc định active

        nhanVienRepository.save(nhanVien);
    }

    @Override
    @Transactional
    public void inactiveNhanVien(String nhanVienId) {
        NhanVien nhanVien = nhanVienRepository.findById(nhanVienId).orElse(null);
        if (nhanVien != null) {
            nhanVien.setTrangThai(false);
            nhanVienRepository.save(nhanVien);
        }
    }

    @Override
    @Transactional
    public void update(NhanVienUpdateRequest request) {
        NhanVien nhanVien = nhanVienRepository.findById(String.valueOf(request.getIdNV())).orElse(null);
        if (Objects.isNull(nhanVien)) {
            throw new ResponseException(BadRequestError.STAFF_NOT_EXISTED);
        }
        modelMapper.map(request, nhanVien);
        nhanVienRepository.save(nhanVien);
    }

    @Override
    public NhanVienDTO getById(String nhanVienId) {
        NhanVien nhanVien = nhanVienRepository.findById(nhanVienId)
                .orElseThrow(() -> new ResponseException(BadRequestError.STAFF_NOT_EXISTED));

        return modelMapper.map(nhanVien, NhanVienDTO.class);
    }

    @Override
    public List<NhanVienDTO> getAll() {
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();
        return nhanVienList.stream()
                .map(nhanVien -> modelMapper.map(nhanVien, NhanVienDTO.class))
                .toList();
    }
}

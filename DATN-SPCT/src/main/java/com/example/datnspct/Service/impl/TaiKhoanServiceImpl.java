package com.example.datnspct.Service.impl;

import com.example.datnspct.Model.KhachHang;
import com.example.datnspct.Model.TaiKhoan;
import com.example.datnspct.Repository.KhachHangRepository;
import com.example.datnspct.Repository.TaiKhoanRepository;
import com.example.datnspct.Service.KhachHangService;
import com.example.datnspct.Service.TaiKhoanService;
import com.example.datnspct.dto.KhachHangDTO;
import com.example.datnspct.dto.Request.KhachHangCreateRequest;
import com.example.datnspct.dto.Request.KhachHangUpdateRequest;
import com.example.datnspct.dto.Request.TaiKhoanCreateRequest;
import com.example.datnspct.dto.Request.TaiKhoanUpdateRequest;
import com.example.datnspct.dto.TaiKhoanDTO;
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
public class TaiKhoanServiceImpl implements TaiKhoanService {
    public final ModelMapper modelMapper;
    public final TaiKhoanRepository taiKhoanRepository;

    @Override
    @Transactional
    public void create(TaiKhoanCreateRequest request) {
        TaiKhoan taiKhoan = modelMapper.map(request, TaiKhoan.class);
        taiKhoan.setTrangThai(true);
        taiKhoanRepository.save(taiKhoan);
    }

    @Override
    public void inactiveTaiKhoan(String taiKhoanId) {
        TaiKhoan taiKhoan = taiKhoanRepository.findById(taiKhoanId).orElse(null);
        if (taiKhoan != null) {
            taiKhoan.setTrangThai(false);
            taiKhoanRepository.save(taiKhoan);
        }
    }

    @Override
    public void update(TaiKhoanUpdateRequest request) {
        TaiKhoan taiKhoan = taiKhoanRepository.findById(String.valueOf(request.getIdTK())).orElse(null);
        if (Objects.isNull(taiKhoan)) {
            throw new ResponseException(BadRequestError.ACCOUNT_NOT_EXISTED);
        }
        modelMapper.map(request, taiKhoan);
        taiKhoanRepository.save(taiKhoan);
    }

    @Override
    public TaiKhoanDTO getById(String taiKhoanId) {
        TaiKhoan taiKhoan = taiKhoanRepository.findById(taiKhoanId)
                .orElseThrow(() -> new ResponseException(BadRequestError.ACCOUNT_NOT_EXISTED));

        return modelMapper.map(taiKhoan, TaiKhoanDTO.class);
    }

    @Override
    public List<TaiKhoanDTO> getAll() {
        List<TaiKhoan> taiKhoanList = taiKhoanRepository.findAll();
        return taiKhoanList.stream()
                .map(taiKhoan -> modelMapper.map(taiKhoan, TaiKhoanDTO.class))
                .toList();
    }
}

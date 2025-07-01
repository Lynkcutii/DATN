package com.example.datnspct.Service.impl;


import com.example.datnspct.Model.PhuongThucThanhToan;
import com.example.datnspct.Repository.PhuongThucThanhToanRepository;
import com.example.datnspct.Service.PhuongThucThanhToanService;
import com.example.datnspct.dto.PhuongThucThanhToanDTO;
import com.example.datnspct.dto.Request.PhuongThucThanhToanCreateRequest;
import com.example.datnspct.dto.Request.PhuongThucThanhToanUpdateRequest;
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
public class PhuongThucThanhToanServiceImpl implements PhuongThucThanhToanService {
    public final ModelMapper modelMapper;
    public final PhuongThucThanhToanRepository phuongThucThanhToanRepository;

    @Override
    @Transactional
    public void create(PhuongThucThanhToanCreateRequest request) {
        PhuongThucThanhToan phuongThucThanhToan = modelMapper.map(request, PhuongThucThanhToan.class);
        phuongThucThanhToanRepository.save(phuongThucThanhToan);
    }

    @Override
    @Transactional
    public void delete(String phuongThucThanhToanId) {
        PhuongThucThanhToan pttt = phuongThucThanhToanRepository.findById(phuongThucThanhToanId)
                .orElseThrow(() -> new ResponseException("PHƯƠNG THỨC THANH TOÁN KHÔNG TỒN TẠI",
                        BadRequestError.PAYMENT_NOT_EXISTED));

        phuongThucThanhToanRepository.delete(pttt);
    }

    @Override
    @Transactional
    public void update(PhuongThucThanhToanUpdateRequest request) {
        PhuongThucThanhToan phuongThucThanhToan = phuongThucThanhToanRepository.findById(String.valueOf(request.getIdPTT())).orElse(null);
        if (Objects.isNull(phuongThucThanhToan)) {
            throw new ResponseException(BadRequestError.PAYMENT_NOT_EXISTED);
        }
        modelMapper.map(request, phuongThucThanhToan);
        phuongThucThanhToanRepository.save(phuongThucThanhToan);
    }

    @Override
    public PhuongThucThanhToanDTO getById(String phuongThucThanhToanId) {
        PhuongThucThanhToan phuongThucThanhToan = phuongThucThanhToanRepository.findById(phuongThucThanhToanId)
                .orElseThrow(() -> new ResponseException(BadRequestError.PAYMENT_NOT_EXISTED));

        return modelMapper.map(phuongThucThanhToan, PhuongThucThanhToanDTO.class);
    }

    @Override
    public List<PhuongThucThanhToanDTO> getAll() {
        List<PhuongThucThanhToan> phuongThucThanhToanList = phuongThucThanhToanRepository.findAll();
        return phuongThucThanhToanList.stream()
                .map(phuongThucThanhToan -> modelMapper.map(phuongThucThanhToan, PhuongThucThanhToanDTO.class))
                .toList();
    }
}

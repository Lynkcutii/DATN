package com.example.datnspct.Service.impl;

import com.example.datnspct.Model.*;
import com.example.datnspct.Repository.*;
import com.example.datnspct.Service.HoaDonCTService;
import com.example.datnspct.dto.HoaDonChiTietDTO;
import com.example.datnspct.dto.Request.HoaDonCTCreateRequest;
import com.example.datnspct.dto.Request.HoaDonCTUpdateRequest;
import com.example.datnspct.support.exception.BadRequestError;
import com.mbamc.common.exception.ResponseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HoaDonCTServiceImpl implements HoaDonCTService {
    private final HoaDonCTRepository hoaDonCTRepository;
    private final SanPhamCTRepository sanPhamCTRepository;
    private final HoaDonRepository hoaDonRepository;
    private final KhuyenMaiRepository khuyenMaiRepository;
    private final PhuongThucThanhToanRepository ptttRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void create(HoaDonCTCreateRequest request) {
        HoaDonChiTiet entity = new HoaDonChiTiet();

        // Lấy các entity liên quan
        SanPhamChiTiet sp = sanPhamCTRepository.findById(String.valueOf(request.getIdSP()))
                .orElseThrow(() -> new ResponseException(BadRequestError.PRODUCT_NOT_EXISTED));

        HoaDon hoaDon = hoaDonRepository.findById(String.valueOf(request.getIdHD()))
                .orElseThrow(() -> new ResponseException("HÓA ĐƠN KHÔNG TỒN TẠI", BadRequestError.INVOICE_NOT_EXISTED));

        KhuyenMai km = null;
        if (request.getIdKM() != null) {
            km = khuyenMaiRepository.findById(String.valueOf(request.getIdKM()))
                    .orElseThrow(() -> new ResponseException("KHUYẾN MÃI KHÔNG TỒN TẠI", BadRequestError.PROMOTION_NOT_EXISTED));
        }

        PhuongThucThanhToan pttt = ptttRepository.findById(String.valueOf(request.getIdPTT()))
                .orElseThrow(() -> new ResponseException("PHƯƠNG THỨC THANH TOÁN KHÔNG TỒN TẠI", BadRequestError.PAYMENT_NOT_EXISTED));

        // Map dữ liệu
        entity.setSanPham(sp);
        entity.setHoaDon(hoaDon);
        entity.setKhuyenMai(km);
        entity.setPhuongThucThanhToan(pttt);
        entity.setSoLuong(request.getSoLuong());
        entity.setDonGia(request.getDonGia());
        entity.setThanhTien(request.getThanhTien());
        entity.setTrangThai(request.getTrangThai() != null ? request.getTrangThai() : true);
        entity.setNgayTao(LocalDateTime.now());
        entity.setNgaySua(LocalDateTime.now());

        hoaDonCTRepository.save(entity);
    }

    @Override
    @Transactional
    public void inactiveHoaDonCT(String hoaDonCTId) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonCTRepository.findById(hoaDonCTId).orElse(null);
        if (hoaDonChiTiet != null) {
            hoaDonChiTiet.setTrangThai(false);
            hoaDonCTRepository.save(hoaDonChiTiet);
        }
    }

    @Override
    @Transactional
    public void update(HoaDonCTUpdateRequest request) {
        HoaDonChiTiet entity = hoaDonCTRepository.findById(String.valueOf(request.getIdHDCT()))
                .orElseThrow(() -> new ResponseException("HÓA ĐƠN CHI TIẾT KHÔNG TỒN TẠI", BadRequestError.INVOICE_NOT_EXISTED));

        SanPhamChiTiet sp = sanPhamCTRepository.findById(String.valueOf(request.getIdSP()))
                .orElseThrow(() -> new ResponseException(BadRequestError.PRODUCT_NOT_EXISTED));

        HoaDon hoaDon = hoaDonRepository.findById(String.valueOf(request.getIdHD()))
                .orElseThrow(() -> new ResponseException("HÓA ĐƠN KHÔNG TỒN TẠI", BadRequestError.INVOICE_NOT_EXISTED));

        KhuyenMai km = null;
        if (request.getIdKM() != null) {
            km = khuyenMaiRepository.findById(String.valueOf(request.getIdKM()))
                    .orElseThrow(() -> new ResponseException("KHUYẾN MÃI KHÔNG TỒN TẠI", BadRequestError.PROMOTION_NOT_EXISTED));
        }

        PhuongThucThanhToan pttt = ptttRepository.findById(String.valueOf(request.getIdPTT()))
                .orElseThrow(() -> new ResponseException("PHƯƠNG THỨC THANH TOÁN KHÔNG TỒN TẠI", BadRequestError.PAYMENT_NOT_EXISTED));

        // Map lại dữ liệu
        entity.setSanPham(sp);
        entity.setHoaDon(hoaDon);
        entity.setKhuyenMai(km);
        entity.setPhuongThucThanhToan(pttt);
        entity.setSoLuong(request.getSoLuong());
        entity.setDonGia(request.getDonGia());
        entity.setThanhTien(request.getThanhTien());
        entity.setTrangThai(request.getTrangThai());
        entity.setNgaySua(LocalDateTime.now());

        hoaDonCTRepository.save(entity);
    }

    @Override
    public HoaDonChiTietDTO getById(String hoaDonCTId) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonCTRepository.findById(hoaDonCTId)
                        .orElseThrow(() -> new ResponseException(BadRequestError.INVOICE_NOT_EXISTED));
            return modelMapper.map(hoaDonChiTiet, HoaDonChiTietDTO.class);
    }

    @Override
    public List<HoaDonChiTietDTO> getAll() {
        return hoaDonCTRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, HoaDonChiTietDTO.class))
                .toList();
    }
}

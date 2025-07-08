package com.example.datnspct.Service.impl;

import com.example.datnspct.Model.GioHang;
import com.example.datnspct.Model.GioHangCT;
import com.example.datnspct.Model.SanPhamChiTiet;
import com.example.datnspct.Repository.GioHangCTRepository;
import com.example.datnspct.Repository.GioHangRepository;
import com.example.datnspct.Repository.SanPhamCTRepository;
import com.example.datnspct.Service.GioHangCTService;
import com.example.datnspct.dto.GioHangCTDTO;
import com.example.datnspct.dto.Request.GioHangCTCreateRequest;
import com.example.datnspct.dto.Request.GioHangCTUpdateRequest;
import com.example.datnspct.support.exception.BadRequestError;
import com.mbamc.common.exception.ResponseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GioHangCTServiceImpl implements GioHangCTService {
    public final ModelMapper modelMapper;
    public final GioHangRepository gioHangRepository;
    public final SanPhamCTRepository sanPhamCTRepository;
    public final GioHangCTRepository gioHangCTRepository;

    @Override
    @Transactional
    public void create(GioHangCTCreateRequest request) {
        // Lấy giỏ hàng và sản phẩm chi tiết
        GioHang gioHang = gioHangRepository.findById(String.valueOf(request.getIdGH()))
                .orElseThrow(() -> new ResponseException("GIỎ HÀNG KHÔNG TỒN TẠI", BadRequestError.CART_NOT_EXISTED));

        SanPhamChiTiet sanPhamChiTiet = sanPhamCTRepository.findById(String.valueOf(request.getIdSPCT()))
                .orElseThrow(() -> new ResponseException("SẢN PHẨM KHÔNG TỒN TẠI", BadRequestError.PRODUCT_NOT_EXISTED));

        // Kiểm tra sản phẩm đã tồn tại trong giỏ chưa
        GioHangCT existingItem = gioHangCTRepository
                .findByGioHangAndSanPhamChiTiet(gioHang, sanPhamChiTiet)
                .orElse(null);

        if (existingItem != null) {
            // Đã tồn tại → cộng dồn số lượng & cập nhật đơn giá
            int newQuantity = existingItem.getSoLuong() + request.getSoLuong();
            existingItem.setSoLuong(newQuantity);
            existingItem.setDonGia(request.getDonGia()); // hoặc giữ giá cũ nếu muốn
            gioHangCTRepository.save(existingItem);
        } else {
            // Chưa có → tạo mới
            GioHangCT gioHangCT = modelMapper.map(request, GioHangCT.class);
            gioHangCT.setGioHang(gioHang);
            gioHangCT.setSanPhamChiTiet(sanPhamChiTiet);
            gioHangCTRepository.save(gioHangCT);
        }
    }

    @Override
    public void delete(String gioHangCTId) {
        GioHangCT gioHangCT = gioHangCTRepository.findById(gioHangCTId)
                .orElseThrow(() -> new ResponseException("CHI TIẾT GIỎ HÀNG KHÔNG TỒN TẠI", BadRequestError.CART_NOT_EXISTED));

        gioHangCTRepository.delete(gioHangCT);
    }

    @Override
    public void update(GioHangCTUpdateRequest request) {
        GioHangCT gioHangCT = gioHangCTRepository.findById(String.valueOf(request.getIdGHCT()))
                .orElseThrow(() -> new ResponseException("CHI TIẾT GIỎ HÀNG KHÔNG TỒN TẠI", BadRequestError.CART_NOT_EXISTED));

        gioHangCT.setSoLuong(request.getSoLuong());
        gioHangCT.setDonGia(request.getDonGia());
        gioHangCTRepository.save(gioHangCT);
    }

    @Override
    public GioHangCTDTO getById(String gioHangCTId) {
        GioHangCT gioHangCT = gioHangCTRepository.findById(gioHangCTId)
                .orElseThrow(() -> new ResponseException(BadRequestError.CART_NOT_EXISTED));

        return modelMapper.map(gioHangCT, GioHangCTDTO.class);
    }

    @Override
    public List<GioHangCTDTO> getAll() {
        List<GioHangCT> gioHangCTList = gioHangCTRepository.findAll();
        return gioHangCTList.stream()
                .map(gioHangCT -> modelMapper.map(gioHangCT, GioHangCTDTO.class))
                .toList();
    }
}

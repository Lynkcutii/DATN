package com.example.datnspct.Service.impl;

import com.example.datnspct.Model.Img;
import com.example.datnspct.Model.SanPhamChiTiet;
import com.example.datnspct.Repository.ImgRepository;
import com.example.datnspct.Repository.SanPhamCTRepository;
import com.example.datnspct.Service.ImgService;
import com.example.datnspct.dto.ImgDTO;
import com.example.datnspct.dto.Request.ImgCreateRequest;
import com.example.datnspct.dto.Request.ImgUpdateRequest;
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
public class ImgServiceImpl implements ImgService {
    private final SanPhamCTRepository sanPhamCTRepository;
    private final ModelMapper modelMapper;
    private final ImgRepository imgRepository;

    @Override
    @Transactional
    public void create(ImgCreateRequest request) {
        Img entity = new Img();

        SanPhamChiTiet spct = sanPhamCTRepository.findById(String.valueOf(request.getIdSPCT()))
                .orElseThrow(() -> new ResponseException("PRODUCT DETAILS DO NOT EXIST", BadRequestError.PRODUCT_NOT_EXISTED));

        entity.setSanPhamChiTiet(spct);
        entity.setLink(request.getLink());
        entity.setNameImg(request.getNameImg());
        entity.setSize(request.getSize());

        imgRepository.save(entity);
    }

    @Override
    public void delete(String ImgId) {
        Img entity = imgRepository.findById(String.valueOf(Integer.parseInt(ImgId)))
                .orElseThrow(() -> new ResponseException("IMAGE DOES NOT EXIST", BadRequestError.IMG_NOT_EXISTED));
        imgRepository.delete(entity);
    }

    @Override
    @Transactional
    public void update(ImgUpdateRequest request) {
        Img entity = imgRepository.findById(String.valueOf(request.getIdImg()))
                .orElseThrow(() -> new ResponseException("IMAGE DOES NOT EXIST", BadRequestError.IMG_NOT_EXISTED));

        SanPhamChiTiet spct = sanPhamCTRepository.findById(String.valueOf(request.getIdSPCT()))
                .orElseThrow(() -> new ResponseException("PRODUCT DETAILS DO NOT EXIST", BadRequestError.PRODUCT_NOT_EXISTED));

        entity.setSanPhamChiTiet(spct);
        entity.setLink(request.getLink());
        entity.setNameImg(request.getNameImg());
        entity.setSize(request.getSize());

        imgRepository.save(entity);
    }

    @Override
    public ImgDTO getById(String imgId) {
        Img entity = imgRepository.findById(String.valueOf(Integer.parseInt(imgId)))
                .orElseThrow(() -> new ResponseException(BadRequestError.IMG_NOT_EXISTED));

        return modelMapper.map(entity, ImgDTO.class);
    }

    @Override
    public List<ImgDTO> getAll() {
        return imgRepository.findAll().stream()
                .map(img -> modelMapper.map(img, ImgDTO.class))
                .toList();
    }
}

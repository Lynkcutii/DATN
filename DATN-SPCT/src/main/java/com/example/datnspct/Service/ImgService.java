package com.example.datnspct.Service;


import com.example.datnspct.dto.ImgDTO;
import com.example.datnspct.dto.Request.ImgCreateRequest;
import com.example.datnspct.dto.Request.ImgUpdateRequest;

import java.util.List;

public interface ImgService {
    void create ( ImgCreateRequest request);
    void delete (String ImgId);
    void update (ImgUpdateRequest request);

    ImgDTO getById (String ImgId);
    List<ImgDTO> getAll();

}

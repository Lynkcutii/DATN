package com.example.datnspct.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DanhMucUpdateRequest {
    private Integer idDM;

    private String tenDM;

    private Boolean trangThai;
}

package com.example.datnspct.Controller.clinet;

import com.example.datnspct.dto.VoucherDTO;
import com.example.datnspct.Service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client/api/voucher")
public class VoucherClientController {

    @Autowired
    private VoucherService voucherService;

    // API lấy danh sách voucher áp dụng được cho danh sách IdSPCT
    @PostMapping("/applicable")
    public ResponseEntity<List<VoucherDTO>> getApplicableVouchers(@RequestBody List<Integer> idSPCTs) {
        try {
            List<VoucherDTO> vouchers = voucherService.getApplicableVouchersForProducts(idSPCTs);
            return ResponseEntity.ok(vouchers);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
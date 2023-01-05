package com.ivac.app.ivacbillcollection.controller;

import com.ivac.app.ivacbillcollection.model.*;
import com.ivac.app.ivacbillcollection.service.IvacBillService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/bill-collection/v1/")
public class IvacController {

    @Autowired
    IvacBillService ivacBillService;

    @GetMapping("/test")
    public TestResponse test() {
        return ivacBillService.test();
    }

    @PostMapping("/bill-info")
    public IvacBillInfoResponse getBillToken(@RequestBody IvacBillInfoRequest tokenResponse) {
        return ivacBillService.getBillToken(tokenResponse);
    }

    @PostMapping("/bill-payment")
    public IvacBillPaymntResponse getBillExeResponse(@RequestBody IvacBillInfoRequest ivacBillInfoRequest) {
        return ivacBillService.getBillExeResponse(ivacBillInfoRequest);
    }

    @PostMapping("/ivac-center")
    public IvacCenterResponse getIvacCnter(@RequestBody IvacBillInfoRequest ivacBillInfoRequest) {
        return ivacBillService.getIvacCnter(ivacBillInfoRequest);
    }


}

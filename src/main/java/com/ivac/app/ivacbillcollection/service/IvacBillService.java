package com.ivac.app.ivacbillcollection.service;

import com.ivac.app.ivacbillcollection.model.*;
import org.json.JSONException;


public interface IvacBillService {

    public IvacBillInfoResponse getBillToken(IvacBillInfoRequest tokenrequest);

    public IvacBillInfoResponse getBillInfo(IvacBillInfoRequest ivacBillInfoRequest);

    public IvacBillPaymntResponse getBillExeResponse(IvacBillInfoRequest ivacBillInfoRequest);

    public IvacCenterResponse getIvacCnter(IvacBillInfoRequest ivacBillInfoRequest);

    public TestResponse test();


}

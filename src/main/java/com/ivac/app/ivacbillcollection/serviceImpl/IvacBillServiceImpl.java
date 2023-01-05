package com.ivac.app.ivacbillcollection.serviceImpl;

import com.ivac.app.ivacbillcollection.model.*;
import com.ivac.app.ivacbillcollection.service.IvacBillService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class IvacBillServiceImpl implements IvacBillService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @Override
    public IvacBillInfoResponse getBillToken(IvacBillInfoRequest request) {
        System.out.println("AUTH-KEY : " + request.getAuthKey());
        System.out.println("STK-CODE : " + request.getStkCode());
        System.out.println("transactinId : " + request.getTransactinId());
        System.out.println("ivacId : " + request.getIvacId());
        System.out.println("webfileId : " + request.getWebfileId());
        System.out.println("passportNo : " + request.getPassportNo());
        System.out.println("appointType : " + request.getAppointType());
        System.out.println("appointDate : " + request.getAppointDate());
        System.out.println("mobileNo : " + request.getMobileNo());
        System.out.println("email : " + request.getEmail());


        IvacBillInfoResponse outModel = new IvacBillInfoResponse();
        IvacBillInfoRequest outModelbill = new IvacBillInfoRequest();
        String jsonString = "";
        String Status = "";
        String code = "";
        ResponseEntity<String> response = null;
        //BrebPreRechargeResponse billInfo = null;
        try {

            String path = env.getProperty("ivac-base-url");
            System.out.println("path = " + path);
            String url = path + "service-list";

            HttpHeaders headers = new HttpHeaders();
            headers.add("AUTH-KEY", request.getAuthKey());
            headers.add("STK-CODE", request.getStkCode());


            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            response = restTemplate.exchange(
                    url, HttpMethod.GET, requestEntity, String.class);

            System.out.println("entity.getBody() = " + response.getBody());
            code = String.valueOf(response.getStatusCodeValue());
            outModel.setCode(code);
            if (response.getStatusCodeValue() == 200) {
                System.out.println("Request Successful.");
                System.out.println(response.getBody());
                jsonString = response.getBody();
                System.out.println("jsonString = " + jsonString);
                JSONObject obj = new JSONObject(jsonString);


                if (obj.has("status") && !obj.isNull("status")) {
                    Status = obj.getString("status");
                    System.out.println("Status = " + Status);
                    outModel.setStatus(Status);
                }

                if (Status.toLowerCase().equals("success")) {
                    if (obj.has("data") && !obj.isNull("data")) {
                        JSONArray dataArry = obj.getJSONArray("data");
                        // System.out.println("dataArry = " + dataArry);

                        for (int i = 0; i < dataArry.length(); i++) {
                            JSONObject catnameObj = dataArry.getJSONObject(i);
                            if (catnameObj.has("category_name") && !catnameObj.isNull("category_name")) {

                            }
                            if ("Visa fees".equals(catnameObj.getString("category_name"))) {
                                // outModel.setOutCode("0");
                                outModel.setOutMessage(catnameObj.getString("category_name"));
                                JSONArray catNamedataArray = catnameObj.getJSONArray("data");
                                //System.out.println("catNamedataArray = " + catNamedataArray);

                                for (int l = 0; l < catNamedataArray.length(); l++) {
                                    JSONObject catnameObjVal = catNamedataArray.getJSONObject(l);
                                    if ("IVAC".equals(catnameObjVal.getString("utility_code"))) {
                                        JSONArray ivacdataArray = catnameObjVal.getJSONArray("data");
                                        // System.out.println("ivacdataArray = " + ivacdataArray);

                                        for (int k = 0; k < ivacdataArray.length(); k++) {
                                            JSONObject ivacdataArrayVal = ivacdataArray.getJSONObject(k);
                                            System.out.println("ivacdataArrayVal = " + ivacdataArrayVal);
                                            System.out.println("utility_auth_key = " + ivacdataArrayVal.getString("utility_auth_key"));
                                            System.out.println("utility_secret_key = " + ivacdataArrayVal.getString("utility_secret_key"));
                                            outModel.setUtilityAuthKey(ivacdataArrayVal.getString("utility_auth_key"));
                                            outModel.setUtilitySecretKey(ivacdataArrayVal.getString("utility_secret_key"));
                                            outModelbill.setUtilityAuthKey(ivacdataArrayVal.getString("utility_auth_key"));
                                            outModelbill.setUtilitySecretKey(ivacdataArrayVal.getString("utility_secret_key"));

                                            outModelbill.setAuthKey(request.getAuthKey());
                                            outModelbill.setStkCode(request.getStkCode());
                                            outModelbill.setTransactinId(request.getTransactinId());
                                            outModelbill.setIvacId(request.getIvacId());
                                            outModelbill.setWebfileId(request.getWebfileId());
                                            outModelbill.setPassportNo(request.getPassportNo());
                                            outModelbill.setAppointType(request.getAppointType());
                                            outModelbill.setAppointDate(request.getAppointDate());
                                            outModelbill.setMobileNo(request.getMobileNo());
                                            outModelbill.setEmail(request.getEmail());

                                            IvacBillInfoResponse billInfo = getBillInfo(outModelbill);
                                            // System.out.println("billInfo.getOutCode() = " + billInfo.getOutCode());
                                            outModel.setOutCode(billInfo.getOutCode());
                                            outModel.setOutMessage(billInfo.getOutMessage());
                                            outModel.setTransactionId(billInfo.getTransactionId());
                                            outModel.setLid(billInfo.getLid());
                                            outModel.setStatus(billInfo.getStatus());
                                            outModel.setCode(billInfo.getCode());
                                            outModel.setIvac(billInfo.getIvac());
                                            outModel.setBillAmount(billInfo.getBillAmount());
                                            outModel.setTotalAmount(billInfo.getTotalAmount());

                                        }
                                    }

                                }


                            }
                        }

                    }


                } else {
                    outModel.setOutCode("1");
                    outModel.setOutMessage("Ivac API Problem :- " + response.getStatusCode());


                }

            } else {
                outModel.setOutCode("1");
                outModel.setOutMessage("Ivac API Problem :- " + response.getStatusCode());

            }

        } catch (Exception e) {
            e.printStackTrace();
            outModel.setOutCode("1");
            outModel.setOutMessage(e.getMessage() + jsonString);
            return outModel;
        }


        return outModel;
    }

    @Override
    public IvacBillInfoResponse getBillInfo(IvacBillInfoRequest ivacBillInfoRequest) {

        IvacBillInfoResponse outModel = new IvacBillInfoResponse();

        String jsonString = "";
        String status = "";
        String status_code = "";
        String message = "";
        String status_title = "";
        String ivac = "";
        String bill_amount = "";
        String total_amount = "";
        String lid = "";
        String transactionId = "";

        ResponseEntity<String> response = null;
        try {
            // create headers
            HttpHeaders headers = new HttpHeaders();
            // set `content-type` header
            headers.setContentType(MediaType.APPLICATION_JSON);
            // set `accept` header
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            // headers.setBearerAuth(nescoBillInfoRequest.getAccessToken().trim());
            headers.set("AUTH-KEY", ivacBillInfoRequest.getAuthKey().trim());
            headers.set("STK-CODE", ivacBillInfoRequest.getStkCode().trim());


            RequestModel requestModel = new RequestModel();

            requestModel.setTransaction_id(ivacBillInfoRequest.getTransactinId().trim());
            requestModel.setIvac_id(ivacBillInfoRequest.getIvacId().trim());
            requestModel.setWebfile_id(ivacBillInfoRequest.getWebfileId().trim());
            requestModel.setPassport_no(ivacBillInfoRequest.getPassportNo().trim());
            requestModel.setAppoint_type(ivacBillInfoRequest.getAppointType().trim());
            requestModel.setAppoint_date(ivacBillInfoRequest.getAppointDate().trim());
            requestModel.setMobile_no(ivacBillInfoRequest.getMobileNo().trim());
            requestModel.setEmail_address(ivacBillInfoRequest.getEmail().trim());
            requestModel.setUtility_auth_key(ivacBillInfoRequest.getUtilityAuthKey().trim());
            requestModel.setUtility_secret_key(ivacBillInfoRequest.getUtilitySecretKey().trim());


            // build the request
            HttpEntity<RequestModel> request = new HttpEntity<RequestModel>(requestModel, headers);

            // send POST request

            String path = env.getProperty("ivac-base-url");
            System.out.println("path = " + path);
            String url = path + "bill-info";


            response = restTemplate.postForEntity(url, request, String.class, 1);


            System.out.println("response = " + response + "\n");

            // check response

            if (response.getStatusCodeValue() == 200) {
                System.out.println("Request Successful.");
                System.out.println(response.getBody());
                jsonString = response.getBody();
                System.out.println("jsonString = " + jsonString);
                outModel.setOutCode("0");
                JSONObject obj = new JSONObject(jsonString);

                if (obj.has("status") && !obj.isNull("status")) {
                    status = obj.getString("status");
                    System.out.println("status = " + status);
                    outModel.setStatus(status);
                }
                if (obj.has("status_code") && !obj.isNull("status_code")) {
                    status_code = obj.getString("status_code");
                    System.out.println("status_code = " + status_code);
                    outModel.setCode(status_code);
                }


                if (status_code.equals("000")) {
                    outModel.setOutCode("0");
                    if (obj.has("status_title") && !obj.isNull("status_title")) {
                        status_title = obj.getString("status_title");
                        System.out.println("status_title = " + status_title);
                        outModel.setOutMessage(status_title);
                    }

                    if (obj.has("data") && !obj.isNull("data")) {
                        JSONObject dataobj = obj.getJSONObject("data");
                        //  dataobj = obj.getJSONObject("data");
                        if (dataobj.has("ivac") && !dataobj.isNull("ivac")) {
                            ivac = dataobj.getString("ivac");
                            System.out.println("ivac = " + ivac);
                            outModel.setIvac(ivac);
                        }

                        if (dataobj.has("bill_amount") && !dataobj.isNull("bill_amount")) {
                            bill_amount = dataobj.getString("bill_amount");
                            System.out.println("bill_amount = " + bill_amount);
                            outModel.setBillAmount(bill_amount);
                        }

                        if (dataobj.has("total_amount") && !dataobj.isNull("total_amount")) {
                            total_amount = dataobj.getString("total_amount");
                            System.out.println("total_amount = " + total_amount);
                            outModel.setTotalAmount(total_amount);
                        }
                    }


                    if (obj.has("lid") && !obj.isNull("lid")) {
                        lid = obj.getString("lid");
                        System.out.println("lid = " + lid);
                        outModel.setLid(lid);
                    }
                    if (obj.has("transaction_id") && !obj.isNull("transaction_id")) {
                        transactionId = obj.getString("transaction_id");
                        System.out.println("transactionId = " + transactionId);
                        outModel.setTransactionId(transactionId);
                    }


                } else {
                    outModel.setOutCode("1");
                    if (obj.has("message") && !obj.isNull("message")) {
                        message = obj.getString("message");
                        System.out.println("message = " + message);
                        outModel.setOutMessage(message);
                    }
                }


                return outModel;
            } else {
                outModel.setOutCode("1");
                outModel.setOutMessage(jsonString);

                return outModel;
            }


        } catch (Exception e) {
            e.printStackTrace();
            outModel.setOutCode("1");
            outModel.setOutMessage(e.getMessage() + jsonString);


            return outModel;

        }
    }

    @Override
    public IvacBillPaymntResponse getBillExeResponse(IvacBillInfoRequest ivacBillInfoRequest) {
        IvacBillPaymntResponse outModel = new IvacBillPaymntResponse();
        String jsonString = "";
        String status = "";
        String status_code = "";
        String message = "";
        String status_title = "";
        String vr_guid = "";
        String recharge_status = "";
        String total_amount = "";
        String lid = "";
        String transactionId = "";

        ResponseEntity<String> response = null;
        try {
            // create headers
            HttpHeaders headers = new HttpHeaders();
            // set `content-type` header
            headers.setContentType(MediaType.APPLICATION_JSON);
            // set `accept` header
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("AUTH-KEY", ivacBillInfoRequest.getAuthKey().trim());
            headers.set("STK-CODE", ivacBillInfoRequest.getStkCode().trim());


            IvacBillPaymentRequest requestModel = new IvacBillPaymentRequest();
            requestModel.setTransaction_id(ivacBillInfoRequest.getTransactinId().trim());
            requestModel.setUtility_auth_key(ivacBillInfoRequest.getUtilityAuthKey().trim());
            requestModel.setUtility_secret_key(ivacBillInfoRequest.getUtilitySecretKey().trim());

            // build the request
            HttpEntity<IvacBillPaymentRequest> request = new HttpEntity<IvacBillPaymentRequest>(requestModel, headers);

            // send POST request

            String path = env.getProperty("ivac-base-url");
            System.out.println("path = " + path);
            String url = path + "bill-payment";

            response = restTemplate.postForEntity(url, request, String.class, 1);

            System.out.println("response = " + response);
            // check response

            if (response.getStatusCodeValue() == 200) {
                System.out.println("Request Successful.");
                System.out.println(response.getBody());
                jsonString = response.getBody();
                System.out.println("jsonString = " + jsonString);
                outModel.setOutCode("0");
                JSONObject obj = new JSONObject(jsonString);

                if (obj.has("status") && !obj.isNull("status")) {
                    status = obj.getString("status");
                    System.out.println("status = " + status);
                    outModel.setStatus(status);
                }
                if (obj.has("status_code") && !obj.isNull("status_code")) {
                    status_code = obj.getString("status_code");
                    System.out.println("status_code = " + status_code);
                    outModel.setCode(status_code);
                }


                if (status_code.equals("111")) {
                    outModel.setOutCode("0");
                    if (obj.has("status_title") && !obj.isNull("status_title")) {
                        status_title = obj.getString("status_title");
                        System.out.println("status_title = " + status_title);
                        outModel.setStatusTitle(status_title);
                    }

                    if (obj.has("data") && !obj.isNull("data")) {
                        JSONObject dataobj = obj.getJSONObject("data");
                        //  dataobj = obj.getJSONObject("data");
                        if (dataobj.has("vr_guid") && !dataobj.isNull("vr_guid")) {
                            vr_guid = dataobj.getString("vr_guid");
                            System.out.println("vr_guid = " + vr_guid);
                            outModel.setVrGuid(vr_guid);
                        }

                        if (dataobj.has("recharge_status") && !dataobj.isNull("recharge_status")) {
                            recharge_status = dataobj.getString("recharge_status");
                            System.out.println("recharge_status = " + recharge_status);
                            outModel.setRechargeStatus(recharge_status);
                        }

                        if (dataobj.has("message") && !dataobj.isNull("message")) {
                            message = dataobj.getString("message");
                            System.out.println("message = " + message);
                            outModel.setMessage(message);
                        }

                        outModel.setOutMessage(status_title + ". " + message);
                    }


                    if (obj.has("lid") && !obj.isNull("lid")) {
                        lid = obj.getString("lid");
                        System.out.println("lid = " + lid);
                        outModel.setLid(lid);
                    }


                } else {
                    outModel.setOutCode("1");
                    if (obj.has("message") && !obj.isNull("message")) {
                        message = obj.getString("message");
                        System.out.println("message = " + message);
                        outModel.setOutMessage(message);
                    }
                }


                return outModel;
            } else {
                outModel.setOutCode("1");
                outModel.setOutMessage(jsonString);

                return outModel;
            }


        } catch (Exception e) {

            outModel.setOutCode("1");
            outModel.setOutMessage(e.getMessage() + jsonString);

            return outModel;
        }
    }

    @Override
    public IvacCenterResponse getIvacCnter(IvacBillInfoRequest request) {

        System.out.println("AUTH-KEY : " + request.getAuthKey());
        System.out.println("STK-CODE : " + request.getStkCode());


        IvacCenterResponse outModel = new IvacCenterResponse();
        String jsonString = "";
        String Status = "";
        String code = "";
        ResponseEntity<String> response = null;
        //BrebPreRechargeResponse billInfo = null;
        try {

            String path = env.getProperty("ivac-base-url");
            System.out.println("path = " + path);
            String url = path + "service-list";

            HttpHeaders headers = new HttpHeaders();
            headers.add("AUTH-KEY", request.getAuthKey());
            headers.add("STK-CODE", request.getStkCode());


            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            response = restTemplate.exchange(
                    url, HttpMethod.GET, requestEntity, String.class);

            System.out.println("entity.getBody() = " + response.getBody());
            code = String.valueOf(response.getStatusCodeValue());
            outModel.setCode(code);
            if (response.getStatusCodeValue() == 200) {
                System.out.println("Request Successful.");
                System.out.println(response.getBody());
                jsonString = response.getBody();
                System.out.println("jsonString = " + jsonString);
                JSONObject obj = new JSONObject(jsonString);


                if (obj.has("status") && !obj.isNull("status")) {
                    Status = obj.getString("status");
                    System.out.println("Status = " + Status);
                    outModel.setStatus(Status);
                }

                if (Status.toLowerCase().equals("success")) {
                    if (obj.has("data") && !obj.isNull("data")) {
                        JSONArray dataArry = obj.getJSONArray("data");
                        // System.out.println("dataArry = " + dataArry);

                        for (int i = 0; i < dataArry.length(); i++) {
                            JSONObject catnameObj = dataArry.getJSONObject(i);
                            if (catnameObj.has("category_name") && !catnameObj.isNull("category_name")) {
                                if ("Visa fees".equals(catnameObj.getString("category_name"))) {
                                    outModel.setOutMessage(catnameObj.getString("category_name"));
                                    if (catnameObj.has("data") && !catnameObj.isNull("data")) {
                                        JSONArray catNamedataArray = catnameObj.getJSONArray("data");
                                        for (int l = 0; l < catNamedataArray.length(); l++) {
                                            JSONObject catnameObjVal = catNamedataArray.getJSONObject(l);
                                            if (catnameObjVal.has("utility_code") && !catnameObjVal.isNull("utility_code")) {
                                                if ("IVAC".equals(catnameObjVal.getString("utility_code"))) {
                                                    if (catnameObjVal.has("data") && !catnameObjVal.isNull("data")) {
                                                        JSONArray ivacdataArray = catnameObjVal.getJSONArray("data");
                                                        for (int k = 0; k < ivacdataArray.length(); k++) {
                                                            JSONObject ivacdataArrayVal = ivacdataArray.getJSONObject(k);
                                                            // System.out.println("utility_auth_key = " + ivacdataArrayVal.getString("utility_auth_key"));
                                                            // System.out.println("utility_secret_key = " + ivacdataArrayVal.getString("utility_secret_key"));
                                                            if (ivacdataArrayVal.has("bill_info") && !ivacdataArrayVal.isNull("bill_info")) {
                                                                String bill_info = ivacdataArrayVal.getString("bill_info");
                                                                JSONObject billInfoObj = new JSONObject(bill_info);
                                                                if (billInfoObj.has("request_parameter") && !billInfoObj.isNull("request_parameter")) {
                                                                    JSONArray paramArray = billInfoObj.getJSONArray("request_parameter");
                                                                    for (int m = 0; m < paramArray.length(); m++) {
                                                                        JSONObject nameObj = paramArray.getJSONObject(m);
                                                                        if (nameObj.has("name") && !nameObj.isNull("name")) {
                                                                            if ("ivac_id".equals(nameObj.getString("name"))) {
                                                                                JSONArray optionsArray = nameObj.getJSONArray("options");
                                                                                ArrayList<VisaCenter> centerList = new ArrayList<>();
                                                                                for (int n = 0; n < optionsArray.length(); n++) {
                                                                                    VisaCenter outVisaCenter = new VisaCenter();
                                                                                    JSONObject visacenterobj = optionsArray.getJSONObject(n);
                                                                                    String name = visacenterobj.getString("name");
                                                                                    String value = visacenterobj.getString("value");
                                                                                    outVisaCenter.setName(name);
                                                                                    outVisaCenter.setValue(value);
                                                                                    centerList.add(outVisaCenter);
                                                                                    outModel.setCenterList(centerList);
                                                                                }

                                                                            } else if ("appoint_type".equals(nameObj.getString("name"))) {

                                                                                JSONArray optionsArray = nameObj.getJSONArray("options");
                                                                                ArrayList<AppointmentType> appointmentTypesList = new ArrayList<>();
                                                                                for (int o = 0; o < optionsArray.length(); o++) {
                                                                                    AppointmentType outAppointmentType = new AppointmentType();
                                                                                    JSONObject appointmentTypeobj = optionsArray.getJSONObject(o);
                                                                                    String name = appointmentTypeobj.getString("name");
                                                                                    String value = appointmentTypeobj.getString("value");
                                                                                    outAppointmentType.setName(name);
                                                                                    outAppointmentType.setValue(value);
                                                                                    appointmentTypesList.add(outAppointmentType);
                                                                                    outModel.setAppointmentTypesList(appointmentTypesList);
                                                                                }

                                                                            }
                                                                        }


                                                                    }
                                                                }

                                                            }


                                                        }

                                                    }
                                                }

                                            }

                                        }

                                    }


                                }

                            }
                        }

                    }


                } else {
                    outModel.setOutCode("1");
                    outModel.setOutMessage("Ivac API Problem :- " + response.getStatusCode());


                }

            } else {
                outModel.setOutCode("1");
                outModel.setOutMessage("Ivac API Problem :- " + response.getStatusCode());

            }

        } catch (Exception e) {
            e.printStackTrace();
            outModel.setOutCode("1");
            outModel.setOutMessage(e.getMessage() + jsonString);
            return outModel;
        }


        return outModel;
    }


    @Override
    public TestResponse test() {
        TestResponse outModel = new TestResponse();
        outModel.setOutCode("0");
        outModel.setOutMessage("IVAC Middleware API is OK");
        System.out.println("outModel.getOutCode() = " + outModel.getOutCode());
        return outModel;
    }


}

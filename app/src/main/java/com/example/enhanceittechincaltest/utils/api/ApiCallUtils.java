package com.example.enhanceittechincaltest.utils.api;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//
//import org.json.JSONObject;
//
//
//import inc.credible.contractortracking.data.datastores.server.ModelRequestBase;
//import inc.credible.contractortracking.data.datastores.server.login.ModelRequestLogin;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
///**
// * This class is an attempt to bring order to all the api calls ...
// * i needed a comman interface to intercept and make changes to all api calls handling ...
// * this class will make sure that all api calls are routed through this one class so making changes
// * to all api calls in the future should be relatively easier ....!!!
// *
// */
//public class ApiCallUtils {
//
//    public static enum ApiCall{
//      LOGIN,POST_CREATE,
//    };
//
//    private static Call fetchApiCallBasedOnEnum(ApiCall mApicall,ModelRequestBase ModelRequestBase){
//        try {
//            if(mApicall==null){
//                return null;
//            }
//            switch (mApicall){
////                case TODAY_NOTIF:
////                    return ApiClientProvider.getApiClient().fetchTodayNotifications((ModelRequestTodayNotification) ModelRequestBase);
////                case INTERSTED_OR_NOT:
////                    return ApiClientProvider.getApiClient().submitInterestOrNot((ModelRequestInterestOrNot) ModelRequestBase);
////                case VERIFY_OTP:
////                    return ApiClientProvider.getApiClient().postVerifyCreditOtp((ModelRequestVerifyCreditOtp) ModelRequestBase);
////                case RESEND_OTP:
////                    return ApiClientProvider.getApiClient().postResendOtp((ModelRequestResendOtp) ModelRequestBase);
////                case LOGOUT:
////                    return ApiClientProvider.getApiClient().logout((ModelRequestLogout) ModelRequestBase);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//
////    public static Call callApiTodayNotificaiton( ModelRequestTodayNotification mModelRequest, final ApiCallBack mApiCallBack) throws Exception {
////        return callApi(  ApiCall.TODAY_NOTIF, mModelRequest, mApiCallBack);
////    }
////
////    public static Call callApiInterstedOrNot( ModelRequestInterestOrNot mModelRequest, final ApiCallBack mApiCallBack) throws Exception {
////        return callApi(  ApiCall.INTERSTED_OR_NOT, mModelRequest, mApiCallBack);
////    }
////
////    public static Call callApiSendOtp( ModelRequestSendCreditOtp mModelRequest, final ApiCallBack mApiCallBack) throws Exception {
////        return callApi(  ApiCall.SEND_OTP, mModelRequest, mApiCallBack);
////    }
////
////    public static Call callApiReSendOtp( ModelRequestResendOtp mModelRequest, final ApiCallBack mApiCallBack) throws Exception {
////        return callApi(  ApiCall.RESEND_OTP, mModelRequest, mApiCallBack);
////    }
////
////    public static Call callApiVerifyOtp( ModelRequestVerifyCreditOtp mModelRequest, final ApiCallBack mApiCallBack) throws Exception {
////        return callApi(  ApiCall.VERIFY_OTP, mModelRequest, mApiCallBack);
////    }
//
//    public static Call callApiLogin( ModelRequestLogin mModelRequest, final ApiCallBack mApiCallBack) throws Exception {
//        return callApi(  ApiCall.LOGIN, mModelRequest, mApiCallBack);
//    }
//
////    public static Call callApiLogout( ModelRequestLogout mModelRequest, final ApiCallBack mApiCallBack) throws Exception {
////        return callApi(  ApiCall.LOGOUT, mModelRequest, mApiCallBack);
////    }
//
//
//    /**
//     *
//     * Main function that calls the apis and makes sure that ..... response is handled in a comman mannner , how ever ...
//     * this can be done by overriding the  response interceptor as well ... in other projects make sure to use response interceptors
//     *
//     *
//     * @param mApicall
//     * @param mModelRequest
//     * @param mApiCallBack
//     * @return
//     * @throws Exception
//     */
//    private static Call callApi( ApiCall mApicall , ModelRequestBase mModelRequest, final ApiCallBack mApiCallBack) throws Exception {
//
//
//        Call mCall =  fetchApiCallBasedOnEnum(mApicall,mModelRequest);
//
//        if(mCall==null){
//            throw new Exception("Wrong Enum Api Call passed ..");
//        }
//
//        mCall.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                try {
//
//                    JSONObject mJsonObjectResponse =null;
//
//                    ResponseBody mBody = (ResponseBody) response.body();
//
//                    if(response.isSuccessful()){
//                        mJsonObjectResponse = new JSONObject(mBody.string());
//                        mApiCallBack.handleApiCallback(mJsonObjectResponse ,true);
//
//                    }else{
//                        mJsonObjectResponse = new JSONObject(response.errorBody().string());
//                        if(response.code()==401){
//                            mApiCallBack.handleApiAuthFailure(mJsonObjectResponse,false);
//                        }else{
//
//                            mApiCallBack.handleApiFailure(mJsonObjectResponse,false);
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    mApiCallBack.handleApiCallback(null,false);
//                }
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                mApiCallBack.handleApiCallback(null,false);
//            }
//        });
//        return mCall;
//    }
//
//
//    public interface ApiCallBack{
//        public void handleApiCallback(JSONObject mJSONObjResponse, Boolean success);
//
//        public void handleApiAuthFailure(JSONObject mJSONObjResponse, Boolean success);
//
//        public void handleApiFailure(JSONObject mJSONObjResponse, Boolean success);
//    }
//}
//
//

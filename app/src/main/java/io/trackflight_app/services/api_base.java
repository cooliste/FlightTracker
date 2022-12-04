package io.trackflight_app.services;


 class APIBase{
    public static String baseURL(){
        return "http://opensky-network.org/api";

    }
}
 class OpenSkyApi {

    private static final String STATES_URI = APIBase.baseURL() + "/states/all";
    private static final String MY_STATES_URI =  APIBase.baseURL()+ "/states/own";

    private enum REQUEST_TYPE {
        GET_STATES,
        GET_MY_STATES
    }}


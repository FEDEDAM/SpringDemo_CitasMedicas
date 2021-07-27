package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers;

public class UrlsStatic 
{
    /*===============================================================================*/
    /*============================= 1 URL BASIC METHODS =============================*/
    /*===============================================================================*/
    public static final String URL_BASIC_OPTION_LIST        = "/list";
    public static final String URL_BASIC_OPTION_DELETE      = "/delete-{id}";
    public static final String URL_BASIC_OPTION_GET         = "/get-{id}";
    
    /*===============================================================================*/
    /*============================= 3 URL APPOINTMENTS ==============================*/
    /*===============================================================================*/
    public static final String URL_BASE_APPOINTMENTS        = "/appointments";
    public static final String URL_BASE_APPOINTMENT_STATUS  = "/appointment-statuses";

    /*===============================================================================*/
    /*============================== 4 URL BLOOD TYPES ==============================*/
    /*===============================================================================*/
    public static final String URL_BASE_BLOOD_TYPES         = "/blood-types";

    /*===============================================================================*/
    /*================================= 5 URL DOCTOR ================================*/
    /*===============================================================================*/
    public static final String URL_BASE_DOCTORS             = "/doctors";
    public static final String URL_BASE_DOCTOR_STATUS       = "doctor-statuses";

    /*===============================================================================*/
    /*================================ 6 URL PATIENTS ===============================*/
    /*===============================================================================*/
    public static final String URL_BASE_PATIENTS            = "/patients";
    public static final String URL_BASE_PATIENT_STATUSES    = "/patient-statuses";

    /*===============================================================================*/
    /*================================== 8 URL USER =================================*/
    /*===============================================================================*/
    public static final String URL_BASE_USERS               = "/users";
    public static final String URL_BASE_USER_STATUSES       = "/user-statuses";
    public static final String URL_BASE_USER_TYPES          = "/user-types";

    public static final String URL_BASE_SPECIALITIES = "/specialities";
    
}

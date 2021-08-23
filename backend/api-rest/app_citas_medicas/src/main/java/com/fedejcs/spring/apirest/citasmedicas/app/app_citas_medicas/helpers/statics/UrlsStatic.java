package com.fedejcs.spring.apirest.citasmedicas.app.app_citas_medicas.helpers.statics;

public class UrlsStatic 
{
    private UrlsStatic() {
        throw new IllegalStateException("Utility class");
    }

    /*===============================================================================*/
    /*=========================== 0 RESOURCES AND GENERAL ===========================*/
    /*===============================================================================*/
    public static final String URL_RESOURCES_CSS            = "/css/**";
    public static final String URL_RESOURCES_JS             = "/js/**";
    public static final String URL_RESOURCES_IMG            = "/img/**";
    public static final String PATTERN_DIRS_AND_RES         = "/**";
    public static final String URL_BASE_AUTH                = "/auth";
    public static final String URL_AUTH_LOGIN               = "/login";
    public static final String URL_AUTH_REGISTER            = "/register";

    /*===============================================================================*/
    /*============================= 1 URL BASIC METHODS =============================*/
    /*===============================================================================*/
    public static final String URL_BASIC_OPTION_LIST        = "/list";
    public static final String URL_BASIC_OPTION_DELETE      = "/delete-{id}";
    public static final String URL_BASIC_OPTION_GET         = "/get-{id}";
    public static final String URL_BASIC_OPTION_CREATE      = "/create";
    public static final String URL_BASIC_OPTION_UPDATE      = "/edit-{id}";
    
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
    /*============================== 7 URL SPECIALITIES =============================*/
    /*===============================================================================*/
    public static final String URL_BASE_SPECIALITIES        = "/specialities";

    /*===============================================================================*/
    /*================================== 8 URL USER =================================*/
    /*===============================================================================*/
    public static final String URL_BASE_USERS               = "/users";
    public static final String URL_BASE_USER_STATUSES       = "/user-statuses";
    public static final String URL_BASE_USER_TYPES          = "/user-types";

    /*===============================================================================*/
    /*================================== 9 SECURITY =================================*/
    /*===============================================================================*/
    public static final String[] permitAll = {
        "/"
        ,URL_RESOURCES_CSS
        ,URL_RESOURCES_IMG
        ,URL_RESOURCES_JS
        ,URL_BASE_AUTH.concat( PATTERN_DIRS_AND_RES )
    };

    public static final String[] permitUsers = {

    };

    public static final String [] permitDoctors = {

    };
    
    public static final String [] permitPatients = {

    };
}

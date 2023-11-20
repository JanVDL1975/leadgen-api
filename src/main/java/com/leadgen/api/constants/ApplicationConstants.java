package com.leadgen.api.constants;

public interface ApplicationConstants {
    // Controllers
    public static final String LEAD_CONTROLLER_ERROR = "Error creating lead: ";
    public static final String LEAD_CONTROLLER_FORMATTING_ERROR = "Invalid itemId or leadId format";
    public static final String LEAD_CONTROLLER_DELETE_SUCCESS = "Lead deleted successfully";
    public static final String LEAD_CONTROLLER_NO_LEAD_TO_DELETE = "Lead not available to delete";

    public static final String LEAD_SERVICE_CREATE_SUCCESS = "Lead created successfully";
    public static final String LEAD_SERVICE_LEAD_NOT_FOUND = "Lead not found. No such lead present";
    public static final String LEAD_SERVICE_ITEM_NOT_FOUND = "Item not found. No such item present";
}

package com.fresh.market.core.util;

/**
 * @author MR Adisorn.jo 
 */
public interface Constants {

    Integer INTERVAL_POLL = 8;   // second
    
    /*
    USER ADMIN
     */
    Integer USER_STATUS_DELETE = 0;
    Integer USER_STATUS_NORMAL = 1;
    Integer USER_STATUS_INACTIVE = 2;

   //Billing status
    Integer BILLING_ORDER=1;
    Integer BILLING_BILL=2;
    Integer BILLING_ORDER_COMPLETE=3;
    
    String SEQUNCE_BILLING="BILLING";
}


package com.fresh.market.jsf.common;

import java.io.Serializable;

/**
 * @author MR Adisorn.jo  
 */
public abstract class BaseController implements Serializable {

    public abstract void init();

    public void create() {
    }

    public void view() {
    }

    public void edit() {
    }

    public void delete() {
    }

    public void export() {
    }

    public void load() {
    }

    public void prepareEdit() {
    }

    public void prepareCreate() {

    }

    public void prepareView() {
    }

    public void prepareUpload() {
    }

    public void handleFileUpload() {
    }

}

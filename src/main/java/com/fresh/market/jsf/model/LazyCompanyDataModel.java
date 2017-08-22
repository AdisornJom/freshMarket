package com.fresh.market.jsf.model;

import com.fresh.market.core.ejb.entity.SysCompany;
import com.fresh.market.ejb.facade.CustomFacade;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class LazyCompanyDataModel extends LazyDataModel<SysCompany> implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(LazyCompanyDataModel.class);
    private List<SysCompany> datasource;
    private int pageSize;
    private int rowIndex;
    private int rowCount;

    private CustomFacade customFacade;
    private String companyNameTh;
    private String status;

    public LazyCompanyDataModel(CustomFacade customFacade, String companyNameTh, String status) {
        this.customFacade = customFacade;
        this.companyNameTh = companyNameTh;
        this.status = status;
    }

    @Override
    public List<SysCompany> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        try {
            int[] range = {first, first + pageSize};
            datasource = customFacade.findSysCompanyByCriteria(companyNameTh, status, range);
            if (sortField != null) {
                Collections.sort(datasource, new LazySorter(sortField, sortOrder));
            }
            setRowCount(customFacade.countSysCompanyByCriteria(companyNameTh, status));
            return datasource;
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * Checks if the row is available
     *
     * @return boolean
     */
    @Override
    public boolean isRowAvailable() {
        if (datasource == null) {
            return false;
        }
        int index = rowIndex % pageSize;
        return index >= 0 && index < datasource.size();
    }

    /**
     * Gets the user object's primary key
     *
     * @param user
     * @return Object
     */
    @Override
    public Object getRowKey(SysCompany sysCompany) {
        return sysCompany.getCompanyId();
    }

    /**
     * Returns the user object at the specified position in datasource.
     *
     * @return
     */
    @Override
    public SysCompany getRowData() {
        if (datasource == null) {
            return null;
        }
        int index = rowIndex % pageSize;
        if (index > datasource.size()) {
            return null;
        }
        return datasource.get(index);
    }

    /**
     * Returns the user object that has the row key.
     *
     * @param rowKey
     * @return
     */
    @Override
    public SysCompany getRowData(String rowKey) {
        if (datasource == null) {
            return null;
        }

        for (SysCompany sysCompany : datasource) {
            if (sysCompany.getCompanyId().equals(rowKey)) {
                return sysCompany;
            }
        }
        return null;
    }

    /*
     * ===== Getters and Setters of LazyUserDataModel fields
     */
    /**
     *
     * @param pageSize
     */
    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Returns page size
     *
     * @return int
     */
    @Override
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Returns current row index
     *
     * @return int
     */
    @Override
    public int getRowIndex() {
        return this.rowIndex;
    }

    /**
     * Sets row index
     *
     * @param rowIndex
     */
    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    /**
     * Sets row count
     *
     * @param rowCount
     */
    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * Returns row count
     *
     * @return int
     */
    @Override
    public int getRowCount() {
        return this.rowCount;
    }

    /**
     * Sets wrapped data
     *
     * @param list
     */
    @Override
    public void setWrappedData(Object list) {
        this.datasource = (List<SysCompany>) list;
    }

    /**
     * Returns wrapped data
     *
     * @return
     */
    @Override
    public Object getWrappedData() {
        return datasource;
    }

}

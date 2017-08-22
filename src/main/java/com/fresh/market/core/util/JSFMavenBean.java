package com.fresh.market.core.util;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * @author MR Adisorn.jo 
 */
@ManagedBean(name = "maven")
@ApplicationScoped
public class JSFMavenBean implements Serializable {

    private final Map<String, String> version = new LazyVersionMap(new ArtifactVersionResolver());

    public Map<String, String> getVersion() {
        return version;
    }

}

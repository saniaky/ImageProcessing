package org.imageProcessing.model;

import java.util.Map;

/**
 * @author saniaky
 * @since 9/7/14
 */
public class ImageFilter {

    String name;
    Map<String, String> params;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

}

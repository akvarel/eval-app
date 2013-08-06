package lv.toposoft.client.api;

import lv.toposoft.client.common.ApiException;
import lv.toposoft.client.common.ApiInvoker;
import lv.toposoft.client.model.Script;

import java.util.*;

public class EvalApi {
  String basePath = "http://localhost:8002/api";
  ApiInvoker apiInvoker = ApiInvoker.getInstance();

  public ApiInvoker getInvoker() {
    return apiInvoker;
  }
  
  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }
  
  public String getBasePath() {
    return basePath;
  }

  public Script createScript (Script body) throws ApiException {
    // create path and map variables
    String path = "/eval/".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    // verify required params are set
    if(body == null ) {
       throw new ApiException(400, "missing required params");
    }
    try {
      String response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, body, headerParams);
      if(response != null){
        return (Script) ApiInvoker.deserialize(response, "", Script.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      if(ex.getCode() == 404) {
      	return null;
      }
      else {
        throw ex;
      }
    }
  }
  public Script getScriptById (String scriptId) throws ApiException {
    // create path and map variables
    String path = "/eval/{scriptId}".replaceAll("\\{format\\}","json").replaceAll("\\{" + "scriptId" + "\\}", apiInvoker.escapeString(scriptId.toString()));

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();

    // verify required params are set
    if(scriptId == null ) {
       throw new ApiException(400, "missing required params");
    }
    try {
      String response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, null, headerParams);
      if(response != null){
        return (Script) ApiInvoker.deserialize(response, "", Script.class);
      }
      else {
        return null;
      }
    } catch (ApiException ex) {
      if(ex.getCode() == 404) {
      	return null;
      }
      else {
        throw ex;
      }
    }
  }
  }


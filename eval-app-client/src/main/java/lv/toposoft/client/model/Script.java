package lv.toposoft.client.model;

public class Script {
  private String evalResult = null;
  private String id = null;
  private String scriptType = null;
  private String scriptBody = null;
  private String output = null;
  public String getEvalResult() {
    return evalResult;
  }
  public void setEvalResult(String evalResult) {
    this.evalResult = evalResult;
  }

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getScriptType() {
    return scriptType;
  }
  public void setScriptType(String scriptType) {
    this.scriptType = scriptType;
  }

  public String getScriptBody() {
    return scriptBody;
  }
  public void setScriptBody(String scriptBody) {
    this.scriptBody = scriptBody;
  }

  public String getOutput() {
    return output;
  }
  public void setOutput(String output) {
    this.output = output;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Script {\n");
    sb.append("  evalResult: ").append(evalResult).append("\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  scriptType: ").append(scriptType).append("\n");
    sb.append("  scriptBody: ").append(scriptBody).append("\n");
    sb.append("  output: ").append(output).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


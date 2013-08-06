package lv.toposoft.client.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "script")
public class Script {
  @Id
  private String id = null;
  private String scriptBody = null;
  /* Script type */
  private String scriptType = null;
  private String evalResult = null;
  private String output = null;
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getScriptBody() {
    return scriptBody;
  }
  public void setScriptBody(String scriptBody) {
    this.scriptBody = scriptBody;
  }

  public String getScriptType() {
    return scriptType;
  }
  public void setScriptType(String scriptType) {
    this.scriptType = scriptType;
  }

  public String getEvalResult() {
    return evalResult;
  }
  public void setEvalResult(String evalResult) {
    this.evalResult = evalResult;
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
    sb.append("  id: ").append(id).append("\n");
    sb.append("  scriptBody: ").append(scriptBody).append("\n");
    sb.append("  scriptType: ").append(scriptType).append("\n");
    sb.append("  evalResult: ").append(evalResult).append("\n");
    sb.append("  output: ").append(output).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


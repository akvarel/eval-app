package lv.toposoft.client.model;

import org.springframework.data.annotation.Id;

public class ScriptResponse {
  @Id
  private String id = null;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScriptResponse {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
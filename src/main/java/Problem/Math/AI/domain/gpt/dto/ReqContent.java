package Problem.Math.AI.domain.gpt.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ReqTextContent.class, name = "text"),
        @JsonSubTypes.Type(value = ReqImageContent.class, name = "image_url")
})
public abstract class ReqContent {
    public abstract MessageType getType();
    public abstract String getValue();

    @Override
    public String toString(){
        return new StringBuilder().append("{\n type : ").append(getType()).append('\n')
                .append("content : ").append(getValue()).append("\n }")
                .toString();
    }
}
package organization.structural.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageModel {
    private String message;
    private Boolean status;
    private Object data;
}

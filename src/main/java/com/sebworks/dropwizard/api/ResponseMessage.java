package com.sebworks.dropwizard.api;

import lombok.*;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
public class ResponseMessage {
    private String info;
    private String warning;
    private String error;
}

package com.tech.zootech.security.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AbstractDto implements Serializable {
    private static final String STANDARD_DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = STANDARD_DATE_FORMAT_PATTERN)
    LocalDateTime created;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = STANDARD_DATE_FORMAT_PATTERN)
    LocalDateTime updated;
}

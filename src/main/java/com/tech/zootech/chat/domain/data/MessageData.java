package com.tech.zootech.chat.domain.data;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageData implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
}
